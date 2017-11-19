package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter@Getter
public class SaleAccount extends BaseDomain {
    private Date        vdate;      //记账时间
    private BigDecimal  number;     //销售数量
    private BigDecimal  costPrice;  //成本价格
    private BigDecimal  costAmount; //成本金额
    private BigDecimal  salePrice;  //销售价格
    private BigDecimal  saleAmount; //销售金额
    private Product     product;    //销售货品
    private Client      client;     //客户
    private Employee    saleman;    //销售人员
}
