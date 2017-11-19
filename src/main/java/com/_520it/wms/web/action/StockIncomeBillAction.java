package com._520it.wms.web.action;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.*;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class StockIncomeBillAction extends BaseAction {

    @Setter//StockIncomeBill页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    IStockIncomeBillService stockIncomeBillService;
    @Setter
    IRoleService roleService;
    @Setter
    IDepotService depotService;
    @Getter
    StockIncomeBill stockIncomeBill = new StockIncomeBill();
    @Getter
    StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("入库单列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            putContext("depots",depotService.listAll());
            PageResult result = stockIncomeBillService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("入库单编辑")
    public String input() throws Exception {
        putContext("depots",depotService.listAll());
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return "input";
    }

    @RequiredPermission("入库单保存/更新")
    public String saveOrUpdate() {
        try {
            if (stockIncomeBill.getId() != null) {
                stockIncomeBillService.update(stockIncomeBill);
                addActionMessage("更新成功");
            } else {
                stockIncomeBillService.save(stockIncomeBill);
                addActionMessage("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("入库单删除")
    public String delete() throws Exception {
        stockIncomeBillService.delete(stockIncomeBill);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    @RequiredPermission("入库单审核")
    public String audit() throws Exception {
        if(stockIncomeBill.getId()!=null) {
            stockIncomeBillService.audit(stockIncomeBill.getId());
            addActionMessage("审核成功");
        }
        return SUCCESS;
    }

    //已审核订单的查看页面
    public String view() throws Exception {
        putContext("depots",depotService.listAll());
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
        }
        return "view";
    }
    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到StockIncomeBill信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
            stockIncomeBill.setDepot(null);
        }
        stockIncomeBill.getItems().clear();
    }
}
