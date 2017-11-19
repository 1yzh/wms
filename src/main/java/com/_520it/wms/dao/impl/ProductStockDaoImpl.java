package com._520it.wms.dao.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.domain.ProductStock;
import org.hibernate.Session;

public class ProductStockDaoImpl extends GenericDaoImpl<ProductStock> implements IProductStockDAO {
   public ProductStock getProductStockByDepodIdAndProductId(Long productId, Long depotId) {
      Session session = sessionFactory.getCurrentSession();
      return (ProductStock) session.createQuery("SELECT ps FROM ProductStock ps WHERE ps.product.id=? AND ps.depot.id=?")//
      .setParameter(0,productId)//
      .setParameter(1,depotId)//
      .uniqueResult();
   }
}

