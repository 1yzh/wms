package com._520it.wms.service;

import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IPermissionService {

     void delete(Permission entity);

     List listAll();

     PageResult query(QueryObject qo);

     void reload();
}
