package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter//单据明细
public class OrderBillItem extends BaseDomain {
    private BigDecimal costPrice;//成本价格
    private BigDecimal number;//货品数量
    private BigDecimal amount;//明细小计
    private String remark;//备注
    private Product product;//货品
    private OrderBill bill;
}
