package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ProductStockServiceImpl implements IProductStockService {
    @Setter
    private IProductStockDAO productStockDAO;

    public void save(ProductStock productStock) {
        productStockDAO.save(productStock);
    }

    public void update(ProductStock productStock) {
        productStockDAO.update(productStock);
    }

    public void delete(ProductStock productStock) {
        productStockDAO.delete(productStock);
    }

    public ProductStock get(Long id) {
        return productStockDAO.get(id);
    }

    public List listAll() {
        return productStockDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return productStockDAO.query(qo);
    }

    public void income(Product product, Depot depot, BigDecimal number, BigDecimal costPrice) {
        ProductStock ps = productStockDAO//先从数据库中查出库存,根据仓库和货品信息
                .getProductStockByDepodIdAndProductId(product.getId(), depot.getId());//根据货品和仓库查询库存
        if (ps == null) {//判断货品是否有库存没有库存就新建
            ps = new ProductStock();
            ps.setProduct(product);
            ps.setDepot(depot);
            ps.setPrice(costPrice);
            ps.setStoreNumber(number);
            ps.setAmount(number.multiply(costPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
            productStockDAO.save(ps);
        } else {//有库存就更新库存价格,数量,金额等信息
            ps.setStoreNumber(ps.getStoreNumber().add(number));
            ps.setAmount(ps.getAmount().add(number.multiply(costPrice).setScale(2, BigDecimal.ROUND_HALF_UP)));
            ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(), 2, RoundingMode.HALF_UP));
            productStockDAO.update(ps);
        }
    }

    public BigDecimal outcome(Product product, Depot depot, BigDecimal number) {
        //获取出库单中货品对应的库存信息
        ProductStock ps = productStockDAO.getProductStockByDepodIdAndProductId(product.getId(), depot.getId());
        if (ps== null) {
            throw new RuntimeException(product.getName() + "的库存为0");
        }
        if (number.compareTo(ps.getStoreNumber()) > 0) {
            throw new RuntimeException(product.getName() + "的库存不足,实际库存:" + ps.getStoreNumber());
        }
        ps.setStoreNumber(ps.getStoreNumber().subtract(number));
        ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
        productStockDAO.update(ps);
        return ps.getPrice();
    }
}
