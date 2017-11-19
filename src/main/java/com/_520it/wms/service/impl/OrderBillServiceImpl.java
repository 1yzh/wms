package com._520it.wms.service.impl;

import com._520it.wms.dao.IOrderBillDAO;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class OrderBillServiceImpl implements IOrderBillService {
    @Setter
    private IOrderBillDAO orderBillDAO;

    //抽象初始化明细订单并保存新明细订单的方法
    private void parseOrder(OrderBill bill) {
        //初始化总金额和总数量
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        //遍历明细订单
        for (OrderBillItem item : bill.getItems()) {
            //设置one方的关联关系,让many方可以找到one方
            item.setBill(bill);//因为one放放弃了关系维护,所有让many方来维护
            //设置明细单小计金额
            item.setAmount(item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP));
            //主订单的数量累积,和总金额累积
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
        }
    }

    public void save(OrderBill bill) {
        //设置制单人和制单时间
        bill.setInputUser(UserContext.getCurrentEmployee());
        bill.setInputTime(new Date());
        //设置审核状态为未审核
        bill.setStatus(OrderBill.NORMAL);
        parseOrder(bill);

        //保存订单
        orderBillDAO.save(bill);
    }

    public void update(OrderBill bill) {
        parseOrder(bill);
        orderBillDAO.update(bill);
    }

    public void audit(OrderBill orderBill) {
        if (orderBill.getStatus() == OrderBill.NORMAL) {
            orderBill.setStatus(OrderBill.AUDIT);
            orderBill.setAuditor(UserContext.getCurrentEmployee());
            orderBill.setAuditTime(new Date());
        }
        orderBillDAO.update(orderBill);
    }

    public void delete(OrderBill orderBill) {
        orderBill = orderBillDAO.get(orderBill.getId());
        if (orderBill.getStatus() == OrderBill.NORMAL) {
            orderBillDAO.delete(orderBill);
        }
    }


    public OrderBill get(Long id) {
        return orderBillDAO.get(id);
    }

    public List listAll() {
        return orderBillDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return orderBillDAO.query(qo);
    }
}
