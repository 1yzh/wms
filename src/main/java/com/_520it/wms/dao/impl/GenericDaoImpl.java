package com._520it.wms.dao.impl;

import com._520it.wms.dao.IGenericDAO;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

public class GenericDaoImpl<T> implements IGenericDAO<T> {
    @Setter
    public SessionFactory sessionFactory;
    Class<T> clazz;

    public GenericDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    public T get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(clazz, id);
    }

    public List listAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT obj FROM " + clazz.getSimpleName() + " obj").list();
    }

    public PageResult query(QueryObject qo) {
        Session session = sessionFactory.getCurrentSession();
        //获取结果总数
        String hql = "select count(obj) from " + clazz.getSimpleName() + " obj " + qo.getQuery();
        //设置查询语句
        Query query = session.createQuery(hql);
        setParams(query, qo.getParams());

        Long count = (Long) query.uniqueResult();
        if (count == 0) {

            return new PageResult(0, Collections.EMPTY_LIST, 1, qo.getPageSize());
        }


        //获取查询结果集
        hql = "select obj from " + clazz.getSimpleName() + " obj " + qo.getQuery();
        query = session.createQuery(hql);
        setParams(query, qo.getParams());

        query.setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize()).setMaxResults(qo.getPageSize());

        return new PageResult(count.intValue(), query.list(), qo.getCurrentPage(), qo.getPageSize());
    }

    //抽象出设置参数方法

    public void setParams(Query query, List<Object> params) {
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
    }

    public void batchDelete(List<Long> ids) {
        Session session = sessionFactory.getCurrentSession();
        String hql="DELETE FROM "+ clazz.getSimpleName() + " obj WHERE obj.id IN(:ids)";
        session.createQuery(hql).setParameterList("ids",ids).executeUpdate();
    }
}
