package com._520it.wms.service.impl;

import com._520it.wms.dao.ISupplierDAO;
import com._520it.wms.domain.Supplier;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SupplierServiceImpl implements ISupplierService {
    @Setter
    private ISupplierDAO supplierDAO;

    public void save(Supplier supplier) {
        supplierDAO.save(supplier);
    }

    public void update(Supplier supplier) {
        supplierDAO.update(supplier);
    }

    public void delete(Supplier supplier) {
        supplierDAO.delete(supplier);
    }

    public Supplier get(Long id) {
        return supplierDAO.get(id);
    }

    public List listAll() {
        return supplierDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return supplierDAO.query(qo);
    }
}
