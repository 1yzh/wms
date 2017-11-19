package com._520it.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.Client;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ClientQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class ClientAction extends BaseAction {

    @Setter//Client页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    IClientService clientService;
    @Setter
    IRoleService roleService;
    @Getter
    Client client = new Client();
    @Getter
    ClientQueryObject qo = new ClientQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("客户列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            PageResult result = clientService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("客户编辑")
    public String input() throws Exception {
        if (client.getId() != null) {
            client = clientService.get(client.getId());
        }
        return "input";
    }

    @RequiredPermission("客户保存/更新")
    public String saveOrUpdate(){
        try {
            if (client.getId() != null) {
                clientService.update(client);
                addActionMessage("更新成功");
            } else {
                clientService.save(client);
                addActionMessage("添加成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("客户删除")
    public String delete() throws Exception {
        clientService.delete(client);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }



    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到Client信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (client.getId() != null) {
            client = clientService.get(client.getId());
        }
    }


}
