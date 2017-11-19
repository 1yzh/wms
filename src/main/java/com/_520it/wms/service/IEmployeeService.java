package com._520it.wms.service;

import com._520it.wms.domain.Employee;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
     void save(Employee entity);

     void update(Employee entity);

     void delete(Employee entity);

     Employee get(Long id);

     List listAll();

     PageResult query(EmployeeQueryObject qo);

     void checkLogin(String username, String password);

    void batchDelete(List<Long> ids);
}
