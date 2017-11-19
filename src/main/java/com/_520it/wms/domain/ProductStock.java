package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
public class ProductStock extends BaseDomain {
    private BigDecimal price;//库存价格
    private BigDecimal storeNumber;//库存数量
    private BigDecimal amount;//库存金额
    private Product product;//库存货品
    private Depot depot;//仓库
}
