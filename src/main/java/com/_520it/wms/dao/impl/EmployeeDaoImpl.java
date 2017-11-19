package com._520it.wms.dao.impl;

import com._520it.wms.dao.IEmployeeDAO;
import com._520it.wms.domain.Employee;
import org.hibernate.Query;
import org.hibernate.Session;

public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements IEmployeeDAO {
    public Employee queryForLogin(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select obj from Employee obj where obj.name=? and obj.password=? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, username);
        query.setParameter(1, password);
        //根据页面传过来的用户名和密码查询出匹配的数据,封装成employee对象
        return (Employee) query.uniqueResult();
    }
}

