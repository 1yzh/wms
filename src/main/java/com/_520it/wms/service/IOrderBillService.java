package com._520it.wms.service;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IOrderBillService {
     void save(OrderBill orderBill);

     void update(OrderBill orderBill);

     void delete(OrderBill orderBill);

     OrderBill get(Long id);

     List listAll();

     PageResult query(QueryObject qo);

    void audit(OrderBill orderBill);
}
