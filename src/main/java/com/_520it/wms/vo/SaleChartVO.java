package com._520it.wms.vo;

import lombok.Getter;

import java.math.BigDecimal;

//查询报表一行的结果
@Getter
public class SaleChartVO {
    private String groupType;//分组类型
    private BigDecimal totalNumber;//销售总数量
    private BigDecimal totalAmount;//销售总金额
    private BigDecimal grossProfit;//销售总金额

    public SaleChartVO(String groupType, BigDecimal totalNumber, BigDecimal totalAmount,BigDecimal grossProfit) {
        this.groupType = groupType;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
        this.grossProfit = grossProfit;
    }
}
