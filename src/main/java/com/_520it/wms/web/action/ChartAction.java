package com._520it.wms.web.action;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.OrderGroupType;
import com._520it.wms.vo.SaleChartVO;
import com._520it.wms.vo.SaleGroupType;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChartAction extends BaseAction {
    @Setter
    IChartService chartService;
    @Setter
    ISupplierService supplierService;
    @Setter
    IClientService clientService;
    @Setter
    IBrandService brandService;
    @Getter
    OrderChartQueryObject oqo = new OrderChartQueryObject();
    @Getter
    SaleChartQueryObject sqo = new SaleChartQueryObject();

    public String orderChart() {

        putContext("suppliers", supplierService.listAll());
        putContext("brands", brandService.listAll());
        putContext("orderChart", chartService.queryOrderChart(oqo));
        putContext("groupTypes", OrderGroupType.values());
        return "orderChart";
    }

    public String orderChartByLine() {
        putContext("suppliers", supplierService.listAll());
        putContext("brands", brandService.listAll());
        List<OrderChartVO> list = chartService.queryOrderChart(oqo);
        List<String> groupTypes = new ArrayList<>();
        List<BigDecimal> totalAmounts = new ArrayList<>();
        for (OrderChartVO vo : list) {
            groupTypes.add(vo.getGroupType());
            totalAmounts.add(vo.getTotalAmount());
        }
        putContext("types", JSON.toJSONString(groupTypes));
        putContext("totalAmounts", JSON.toJSONString(totalAmounts));
        putContext("groupName", OrderGroupType.valueOf(oqo.getGroupType().toUpperCase()).getGroupName());
        return "orderChartByLine";
    }

    public String orderChartByPie() {
        putContext("suppliers", supplierService.listAll());
        putContext("brands", brandService.listAll());
        List<OrderChartVO> list = chartService.queryOrderChart(oqo);
        List<Object[]> groupTypes = new ArrayList<>();
        for (OrderChartVO vo : list) {
            groupTypes.add(new Object[]{vo.getGroupType(), vo.getTotalAmount()});
        }
        putContext("groupTypes", JSON.toJSONString(groupTypes));
        putContext("groupName", OrderGroupType.valueOf(oqo.getGroupType().toUpperCase()).getGroupName());
        return "orderChartByPie";


    }

    public String saleChart() {

        putContext("clients", clientService.listAll());
        putContext("brands", brandService.listAll());
        putContext("saleChart", chartService.querySaleChart(sqo));
        putContext("groupTypes", SaleGroupType.values());//枚举的values()方法,可以获取所有枚举中的值,组成一个集合
        return "saleChart";
    }

    public String saleChartByLine() {

        putContext("clients", clientService.listAll());
        putContext("brands", brandService.listAll());
        List<SaleChartVO> vo = chartService.querySaleChart(sqo);
        List<String> groups = new ArrayList<>();
        List<BigDecimal> amounts = new ArrayList<>();
        for (SaleChartVO types : vo) {
            groups.add(types.getGroupType());
            amounts.add(types.getTotalAmount());
        }
        putContext("types", JSON.toJSONString(groups));
        putContext("totalAmounts", JSON.toJSONString(amounts));
        putContext("groupName", SaleGroupType.valueOf(sqo.getGroupType().toUpperCase()).getGroupName());
        return "saleChartByLine";
    }

    public String saleChartByPie() {

        putContext("clients", clientService.listAll());
        putContext("brands", brandService.listAll());
        List<SaleChartVO> vo = chartService.querySaleChart(sqo);

        List<Object[]> groups = new ArrayList<>();
        for (SaleChartVO type : vo) {
            groups.add(new Object[]{type.getGroupType(),type.getTotalAmount()});
        }
        putContext("types", JSON.toJSONString(groups));
        putContext("groupName", SaleGroupType.valueOf(sqo.getGroupType().toUpperCase()).getGroupName());
        return "saleChartByPie";
    }
}
