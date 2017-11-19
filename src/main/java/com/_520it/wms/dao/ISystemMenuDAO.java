package com._520it.wms.dao;

import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;

import java.util.List;

public interface ISystemMenuDAO extends IGenericDAO<SystemMenu> {
    List listMenus();

    List<SystemMenu> queryMenusByParentSn(String parentSn);

    List<SystemMenu> queryMenusByParentSn(String parentSn, List<Role> roles);
}
