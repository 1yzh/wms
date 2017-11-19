package com._520it.wms.service.impl;

import com._520it.wms.dao.IClientDAO;
import com._520it.wms.domain.Client;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientServiceImpl implements IClientService {
    @Setter
    private IClientDAO clientDAO;

    public void save(Client client) {
        clientDAO.save(client);
    }

    public void update(Client client) {
        clientDAO.update(client);
    }

    public void delete(Client client) {
        clientDAO.delete(client);
    }

    public Client get(Long id) {
        return clientDAO.get(id);
    }

    public List listAll() {
        return clientDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return clientDAO.query(qo);
    }
}
