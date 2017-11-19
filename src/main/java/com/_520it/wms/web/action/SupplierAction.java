package com._520it.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.Supplier;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SupplierQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class SupplierAction extends BaseAction {

    @Setter//Supplier页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    ISupplierService supplierService;
    @Setter
    IRoleService roleService;
    @Getter
    Supplier supplier = new Supplier();
    @Getter
    SupplierQueryObject qo = new SupplierQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("供应商列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            PageResult result = supplierService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("供应商编辑")
    public String input() throws Exception {
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
        return "input";
    }

    @RequiredPermission("供应商保存/更新")
    public String saveOrUpdate(){
        try {
            if (supplier.getId() != null) {
                supplierService.update(supplier);
                addActionMessage("更新成功");
            } else {
                supplierService.save(supplier);
                addActionMessage("添加成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("供应商删除")
    public String delete() throws Exception {
        supplierService.delete(supplier);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }



    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到Supplier信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
    }


}
