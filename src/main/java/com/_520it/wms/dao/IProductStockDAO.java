package com._520it.wms.dao;

import com._520it.wms.domain.ProductStock;

public interface IProductStockDAO extends IGenericDAO<ProductStock> {

    ProductStock getProductStockByDepodIdAndProductId(Long productId, Long depotId);

}
