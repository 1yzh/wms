package com._520it.wms.service.impl;

import com._520it.wms.dao.IEmployeeDAO;
import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl implements IEmployeeService {
    @Setter
    private IEmployeeDAO employeeDAO;

    public void save(Employee entity) {
        entity.setPassword(MD5.encode(entity.getPassword()));
        employeeDAO.save(entity);
    }

    public void update(Employee entity) {
        employeeDAO.update(entity);
    }

    public void delete(Employee entity) {
        employeeDAO.delete(entity);
    }

    public Employee get(Long id) {
        return employeeDAO.get(id);
    }

    public List listAll() {
        return employeeDAO.listAll();
    }

    public PageResult query(EmployeeQueryObject qo) {
        return employeeDAO.query(qo);
    }

    public void checkLogin(String username, String password) {

        Employee currentUser = employeeDAO.queryForLogin(username, MD5.encode(password));
        if (currentUser == null) {
            //以异常的形式抛出错误提示
            throw new RuntimeException("用户账号或密码不正确");
        }
        UserContext.setCurrentEmployee(currentUser);

        Set<String> expressionSet = new HashSet<>();
        List<Role> roleList = currentUser.getRoles();
        for (Role role : roleList) {
            for (Permission permission : role.getPermissions()) {
                String expression = permission.getExpression();
                expressionSet.add(expression);
            }
        }
        UserContext.setCurrentPermission(expressionSet);
    }

    public void batchDelete(List<Long> ids) {
        employeeDAO.batchDelete(ids);
    }
}
