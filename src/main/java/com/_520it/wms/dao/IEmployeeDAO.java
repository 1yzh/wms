package com._520it.wms.dao;

import com._520it.wms.domain.Employee;

public interface IEmployeeDAO extends IGenericDAO<Employee> {
     Employee queryForLogin(String username, String password);
}
