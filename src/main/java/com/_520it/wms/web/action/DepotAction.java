package com._520it.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.Depot;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.DepotQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class DepotAction extends BaseAction {

    @Setter//Depot页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    IDepotService depotService;
    @Setter
    IRoleService roleService;
    @Getter
    Depot depot = new Depot();
    @Getter
    DepotQueryObject qo = new DepotQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("仓库列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            PageResult result = depotService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("仓库编辑")
    public String input() throws Exception {
        if (depot.getId() != null) {
            depot = depotService.get(depot.getId());
        }
        return "input";
    }

    @RequiredPermission("仓库保存/更新")
    public String saveOrUpdate(){
        try {
            if (depot.getId() != null) {
                depotService.update(depot);
                addActionMessage("更新成功");
            } else {
                depotService.save(depot);
                addActionMessage("添加成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("仓库删除")
    public String delete() throws Exception {
        depotService.delete(depot);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }



    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到Depot信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (depot.getId() != null) {
            depot = depotService.get(depot.getId());
        }
    }


}
