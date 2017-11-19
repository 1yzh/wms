package com._520it.wms.web.action;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.RequiredPermission;
import com._520it.wms.vo.SystemMenuVO;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class SystemMenuAction extends BaseAction {

    @Setter//SystemMenu页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    ISystemMenuService systemMenuService;
    @Setter
    IRoleService roleService;
    @Getter
    SystemMenu systemMenu = new SystemMenu();
    @Getter
    SystemMenuQueryObject qo = new SystemMenuQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("系统菜单列表")
    @InputConfig(methodName = "input")
    public String execute() {
        try {
            List<SystemMenuVO> menus = systemMenuService.queryParentMenus(qo.getParentId());
            for (SystemMenuVO menu : menus) {
                System.out.println("============================" + menu.getName());
            }
            putContext("menus", menus);
            PageResult result = systemMenuService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("系统菜单编辑")
    public String input() throws Exception {
        if (qo.getParentId() == -1L) {//如果为-1,则表示跟菜单,将"跟菜单"的字符串传过去
            putContext("parentName", "跟菜单");
        } else {
            //否则,查询出这个父菜单信息,将父菜单名称传过去
            SystemMenu parent = systemMenuService.get(qo.getParentId());
            putContext("parentName", parent.getName());
        }
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
        return "input";
    }

    @RequiredPermission("系统菜单保存/更新")
    public String saveOrUpdate() {
        try {
            if (qo.getParentId() == -1L) {
                systemMenu.setParent(null);
            } else {
                SystemMenu parent = systemMenuService.get(qo.getParentId());
                systemMenu.setParent(parent);
            }
            if (systemMenu.getId() != null) {
                systemMenuService.update(systemMenu);
                addActionMessage("更新成功");
            } else {
                systemMenuService.save(systemMenu);
                addActionMessage("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("系统菜单删除")
    public String delete() throws Exception {
        systemMenuService.delete(systemMenu);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }


    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到SystemMenu信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
    }

    public String queryMenusByParentSn() throws Exception {
        List<SystemMenu> menus = systemMenuService.queryMenusByParentSn(qo.getParentSn());
        List<Object> jsonList = new ArrayList<>();
        for (SystemMenu menu : menus) {
            jsonList.add(menu.toJson());
        }
        String jsonString = JSON.toJSONString(jsonList);
         ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
         ServletActionContext.getResponse().getWriter().print(jsonString);
        return NONE;
    }

}
