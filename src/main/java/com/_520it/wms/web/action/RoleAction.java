package com._520it.wms.web.action;

import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.RoleQueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

public class RoleAction extends BaseAction {
    @Setter
    IPermissionService permissionService;
    @Setter
    IRoleService roleService;
    @Setter
    ISystemMenuService systemMenuService;
    @Getter
    Role role = new Role();
    @Getter
    RoleQueryObject qo = new RoleQueryObject();

    @Override
    @RequiredPermission("角色列表")
    @InputConfig(methodName = "input")
    public String execute(){
        try {
            PageResult result = roleService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("角色编辑")
    public String input() throws Exception {
        putContext("permissions", permissionService.listAll());
        putContext("menus", systemMenuService.listMenus());
        if (role.getId() != null) {
            role = roleService.get(role.getId());
        }
        return "input";
    }

    @RequiredPermission("角色保存/更新")
    public String saveOrUpdate(){
        try{
        if (role.getId() != null) {
            roleService.update(role);
            addActionMessage("更新成功");
        }else {
            roleService.save(role);
            addActionMessage("保存成功");
        }}catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("角色删除")
    public String delete() throws Exception {
        roleService.delete(role);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到员工信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (role.getId() != null) {
            role = roleService.get(role.getId());
        }
        role.getPermissions().clear();//防止二次加载权限信息，此处在第二次加载前清除一下
        role.getMenus().clear();//防止二次加载权限信息，此处在第二次加载前清除一下
    }


}
