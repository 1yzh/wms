package com._520it.wms.web.action;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.*;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class StockOutcomeBillAction extends BaseAction {

    @Setter//StockOutcomeBill页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    IStockOutcomeBillService stockOutcomeBillService;
    @Setter
    IDepotService depotService;
    @Setter
    IClientService clientService;
    @Getter
    StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();
    @Getter
    StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("销售出库单列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            putContext("depots",depotService.listAll());
            putContext("clients",clientService.listAll());
            PageResult result = stockOutcomeBillService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("销售出库单编辑")
    public String input() throws Exception {
        putContext("depots",depotService.listAll());
        putContext("clients",clientService.listAll());
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
        }
        return "input";
    }

    @RequiredPermission("销售出库单保存/更新")
    public String saveOrUpdate() {
        try {
            if (stockOutcomeBill.getId() != null) {
                stockOutcomeBillService.update(stockOutcomeBill);
                addActionMessage("更新成功");
            } else {
                stockOutcomeBillService.save(stockOutcomeBill);
                addActionMessage("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("销售出库单删除")
    public String delete() throws Exception {
        stockOutcomeBillService.delete(stockOutcomeBill);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    @RequiredPermission("销售出库单审核")
    public String audit(){
        try{
        if(stockOutcomeBill.getId()!=null) {
            stockOutcomeBillService.audit(stockOutcomeBill.getId());
            addActionMessage("审核成功");
        }}catch (RuntimeException e){
            e.printStackTrace();
            addActionMessage(e.getMessage());
        }
        return SUCCESS;
    }

    //已审核订单的查看页面
    public String view() throws Exception {
        putContext("depots",depotService.listAll());
        putContext("clients",clientService.listAll());
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
        }
        return "view";
    }
    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到StockOutcomeBill信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
            stockOutcomeBill.setDepot(null);
            stockOutcomeBill.setClient(null);
        }
        stockOutcomeBill.getItems().clear();
    }
}
