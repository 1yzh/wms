package com._520it.wms.service.impl;

import com._520it.wms.dao.IBrandDAO;
import com._520it.wms.domain.Brand;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrandServiceImpl implements IBrandService {
    @Setter
    private IBrandDAO brandDAO;

    public void save(Brand brand) {
        brandDAO.save(brand);
    }

    public void update(Brand brand) {
        brandDAO.update(brand);
    }

    public void delete(Brand brand) {
        brandDAO.delete(brand);
    }

    public Brand get(Long id) {
        return brandDAO.get(id);
    }

    public List listAll() {
        return brandDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return brandDAO.query(qo);
    }
}
