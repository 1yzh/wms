package com._520it.wms.service.impl;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.Employee;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IEmployeeService employeeService;

    @Test
    public void save() throws Exception {
        Department department=new Department();
        department.setName("财务部");
        department.setSn("CWB");
        departmentService.save(department);
    }
    @Test
    public void saveEmployee() throws Exception {
        Employee employee=new Employee();
        for(int i=0;i<30;i++){
            employee.setName("aaa"+i);
            employee.setAge(22);
            employee.setPassword("1111");
            employee.setEmail("ee@ee.ee");
            employeeService.save(employee);
        }
    }
    @Test
    public void batchDelete()throws Exception{
        employeeService.batchDelete(Arrays.asList(20L,21L,22L));
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void listAll() throws Exception {
    }

    @Test
    public void setDepartmentDAO() throws Exception {
    }

}