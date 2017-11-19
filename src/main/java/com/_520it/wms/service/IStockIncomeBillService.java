package com._520it.wms.service;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IStockIncomeBillService {
    void save(StockIncomeBill bill);

    void update(StockIncomeBill bill);

    void delete(StockIncomeBill bill);

    StockIncomeBill get(Long id);

    List listAll();

    PageResult query(QueryObject qo);

    void audit(Long billId);
}
