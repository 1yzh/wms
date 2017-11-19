package com._520it.wms.service.impl;

import com._520it.wms.dao.IDepotDAO;
import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepotServiceImpl implements IDepotService {
    @Setter
    private IDepotDAO depotDAO;

    public void save(Depot depot) {
        depotDAO.save(depot);
    }

    public void update(Depot depot) {
        depotDAO.update(depot);
    }

    public void delete(Depot depot) {
        depotDAO.delete(depot);
    }

    public Depot get(Long id) {
        return depotDAO.get(id);
    }

    public List listAll() {
        return depotDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return depotDAO.query(qo);
    }
}
