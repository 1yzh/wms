package com._520it.wms.service.impl;

import com._520it.wms.dao.IRoleDAO;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;
import lombok.Setter;

import java.util.List;

public class RoleServiceImpl implements IRoleService {
    @Setter
    private IRoleDAO roleDAO;

    public void save(Role entity) {
        roleDAO.save(entity);
    }

    public void update(Role entity) {
        roleDAO.update(entity);
    }

    public void delete(Role entity) {
        roleDAO.delete(entity);
    }

    public Role get(Long id) {
        return roleDAO.get(id);
    }

    public List listAll() {
        return roleDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return roleDAO.query(qo);
    }
}
