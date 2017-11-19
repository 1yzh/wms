package com._520it.wms.service;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStockService {
    void save(ProductStock productStock);

    void update(ProductStock productStock);

    void delete(ProductStock productStock);

    ProductStock get(Long id);

    List listAll();

    PageResult query(QueryObject qo);

    /*
    * product 货品信息
    * depot   入库仓库
    * number  入库数量
    * costPrice 库存价格(移动加权平均价格)
    * */
    void income(Product product, Depot depot, BigDecimal number, BigDecimal costPrice);

    /*
    * product 货品信息
    * depot   出库仓库
    * number  销售数量
    * */
    BigDecimal outcome(Product product, Depot depot, BigDecimal number);
}
