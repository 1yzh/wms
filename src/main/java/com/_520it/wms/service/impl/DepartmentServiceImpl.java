package com._520it.wms.service.impl;

import com._520it.wms.dao.IDepartmentDAO;
import com._520it.wms.domain.Department;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    @Setter
    private IDepartmentDAO departmentDAO;

    public void save(Department entity) {
        departmentDAO.save(entity);
    }

    public void update(Department entity) {
        departmentDAO.update(entity);
    }

    public void delete(Department entity) {
        departmentDAO.delete(entity);
    }

    public Department get(Long id) {
        return departmentDAO.get(id);
    }

    public PageResult query(QueryObject qo) {
        return departmentDAO.query(qo);
    }

    public List listAll() {
        return departmentDAO.listAll();
    }
}
