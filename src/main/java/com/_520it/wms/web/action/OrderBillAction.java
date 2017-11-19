package com._520it.wms.web.action;

import com._520it.wms.service.ISupplierService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class OrderBillAction extends BaseAction {

    @Setter//OrderBill页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    IOrderBillService orderBillService;
    @Setter
    IRoleService roleService;
    @Setter
    ISupplierService supplierService;
    @Getter
    OrderBill orderBill = new OrderBill();
    @Getter
    OrderBillQueryObject qo = new OrderBillQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("采购订单列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            putContext("suppliers",supplierService.listAll());
            PageResult result = orderBillService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("采购订单编辑")
    public String input() throws Exception {
        putContext("suppliers",supplierService.listAll());
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
        }
        return "input";
    }

    @RequiredPermission("采购订单保存/更新")
    public String saveOrUpdate() {
        try {
            if (orderBill.getId() != null) {
                orderBillService.update(orderBill);
                addActionMessage("更新成功");
            } else {
                orderBillService.save(orderBill);
                addActionMessage("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("采购订单删除")
    public String delete() throws Exception {
        orderBillService.delete(orderBill);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    @RequiredPermission("采购订单审核")
    public String audit() throws Exception {
        if(orderBill.getId()!=null) {
            orderBill = orderBillService.get(orderBill.getId());
            orderBillService.audit(orderBill);
            addActionMessage("审核成功");
        }
        return SUCCESS;
    }

    //已审核订单的查看页面
    public String view() throws Exception {
        putContext("suppliers",supplierService.listAll());
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
        }
        return "view";
    }
    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到OrderBill信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
            orderBill.setSupplier(null);
        }
        orderBill.getItems().clear();
    }
}
