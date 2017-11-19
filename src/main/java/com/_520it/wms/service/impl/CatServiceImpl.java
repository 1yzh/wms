package com._520it.wms.service.impl;

import com._520it.wms.dao.ICatDAO;
import com._520it.wms.domain.Cat;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ICatService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatServiceImpl implements ICatService {
    @Setter
    private ICatDAO catDAO;

    public void save(Cat cat) {
        catDAO.save(cat);
    }

    public void update(Cat cat) {
        catDAO.update(cat);
    }

    public void delete(Cat cat) {
        catDAO.delete(cat);
    }

    public Cat get(Long id) {
        return catDAO.get(id);
    }

    public List listAll() {
        return catDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return catDAO.query(qo);
    }
}
