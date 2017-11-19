package com._520it.wms.service;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IStockOutcomeBillService {
    void save(StockOutcomeBill stockOutcomeBill);

    void update(StockOutcomeBill stockOutcomeBill);

    void delete(StockOutcomeBill stockOutcomeBill);

    StockOutcomeBill get(Long id);

    List listAll();

    PageResult query(QueryObject qo);

    void audit(Long billId);
}
