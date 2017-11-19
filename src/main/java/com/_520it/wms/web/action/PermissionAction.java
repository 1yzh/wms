package com._520it.wms.web.action;

import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

public class PermissionAction extends BaseAction {
    @Setter
    IPermissionService permissionService;
    @Getter
    Permission permission = new Permission();
    @Getter
    QueryObject qo = new QueryObject();

    @RequiredPermission("权限列表")
    public String execute(){
        try {
            PageResult result = permissionService.query(qo);
            putContext("pageResult", result);
        }catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("权限删除")
    public String delete(){
        try {
            permissionService.delete(permission);
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().print("删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    public String reload(){
        try {
        permissionService.reload();
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().print("加载成功");
        }catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }
}
