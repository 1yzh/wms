package com._520it.wms.service;

import com._520it.wms.domain.Department;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IDepartmentService {
     void save(Department entity);

     void update(Department entity);

     void delete(Department entity);

     Department get(Long id);

     PageResult query(QueryObject qo);

     List listAll();
}
