package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductStockQueryObject extends QueryObject {
    private String keyword;//货品名称或者编号
    private BigDecimal storeLimit;//库存阈值
    private Long depotId = -1L;//仓库
    private Long brandId = -1L;//品牌

    public void customizQuery() {
        if (StringUtils.isNotBlank(keyword)) {
            addQuery("(obj.product.name like ? or obj.product.sn like ?)", "%"+keyword+"%", "%"+keyword+"%");
        }
        if (depotId > 0) {
            addQuery("obj.depot.id = ?", depotId);
        }
        if (brandId > 0) {
            addQuery("obj.product.brand.id = ?", brandId);
        }
        if (storeLimit != null) {
            addQuery("obj.storeNumber <= ?",storeLimit);
        }
        addQuery("obj.storeNumber > ?", BigDecimal.ZERO);
    }
}
