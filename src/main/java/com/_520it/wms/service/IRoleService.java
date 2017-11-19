package com._520it.wms.service;

import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IRoleService {
     void save(Role entity);

     void update(Role entity);

     void delete(Role entity);

     Role get(Long id);

     List listAll();

     PageResult query(QueryObject qo);
}
