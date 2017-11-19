package com._520it.wms.service.impl;

import com._520it.wms.dao.ISystemMenuDAO;
import com._520it.wms.domain.Employee;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.UserContext;
import com._520it.wms.vo.SystemMenuVO;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemMenuServiceImpl implements ISystemMenuService {
    @Setter
    private ISystemMenuDAO systemMenuDAO;

    public void save(SystemMenu systemMenu) {
        systemMenuDAO.save(systemMenu);
    }

    public void update(SystemMenu systemMenu) {
        systemMenuDAO.update(systemMenu);
    }

    public void delete(SystemMenu systemMenu) {
        systemMenuDAO.delete(systemMenu);
    }

    public SystemMenu get(Long id) {
        return systemMenuDAO.get(id);
    }

    public List listAll() {
        return systemMenuDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return systemMenuDAO.query(qo);
    }

    public List<SystemMenuVO> queryParentMenus(Long parentId) {
        SystemMenu currentMenu = systemMenuDAO.get(parentId);
        List<SystemMenuVO> menus = new ArrayList<>();
        listParent(menus, currentMenu);
        Collections.reverse(menus);
        return menus;
    }

    private void listParent(List<SystemMenuVO> menus, SystemMenu currentMenu) {
        if (currentMenu != null) {
            SystemMenuVO menuVO = new SystemMenuVO();
            menuVO.setId(currentMenu.getId());
            menuVO.setName(currentMenu.getName());
            menus.add(menuVO);
            listParent(menus, currentMenu.getParent());
        }
    }

    public List listMenus() {
        return systemMenuDAO.listMenus();
    }

    public List<SystemMenu> queryMenusByParentSn(String parentSn) {
        Employee currentEmployee = UserContext.getCurrentEmployee();
        if(currentEmployee.isAdmin()) {
            return systemMenuDAO.queryMenusByParentSn(parentSn);
        }else{
            return systemMenuDAO.queryMenusByParentSn(parentSn,currentEmployee.getRoles());
        }
    }
}
