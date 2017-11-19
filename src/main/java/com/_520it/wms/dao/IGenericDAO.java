package com._520it.wms.dao;

import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IGenericDAO<T> {
     void save(T entity);

     void update(T entity);

     void delete(T entity);

     T get(Long id);

     List listAll();

     PageResult query(QueryObject qo);

    void batchDelete(List<Long> ids);
}
