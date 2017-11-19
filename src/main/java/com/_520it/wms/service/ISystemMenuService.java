package com._520it.wms.service;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.vo.SystemMenuVO;

import java.util.List;

public interface ISystemMenuService {
     void save(SystemMenu systemMenu);

     void update(SystemMenu systemMenu);

     void delete(SystemMenu systemMenu);

     SystemMenu get(Long id);

     List listAll();

     PageResult query(QueryObject qo);

    List<SystemMenuVO> queryParentMenus(Long parentId);

     List listMenus();

    List<SystemMenu> queryMenusByParentSn(String parentSn);
}
