package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductDAO;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductServiceImpl implements IProductService {
    @Setter
    private IProductDAO productDAO;

    public void save(Product product) {
        productDAO.save(product);
    }

    public void update(Product product) {
        productDAO.update(product);
    }

    public void delete(Product product) {
        productDAO.delete(product);
    }

    public Product get(Long id) {
        return productDAO.get(id);
    }

    public List listAll() {
        return productDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return productDAO.query(qo);
    }
}
