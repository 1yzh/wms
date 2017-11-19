package com._520it.wms.vo;

import lombok.Getter;

import java.math.BigDecimal;

//查询报表一行的结果
@Getter
public class OrderChartVO {
    private String groupType;//分组类型
    private BigDecimal totalNumber;//订货总数量
    private BigDecimal totalAmount;//订货总金额

    public OrderChartVO(String groupType, BigDecimal totalNumber, BigDecimal totalAmount) {
        this.groupType = groupType;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
    }
}
