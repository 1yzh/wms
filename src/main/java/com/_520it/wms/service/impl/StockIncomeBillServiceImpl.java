package com._520it.wms.service.impl;

import com._520it.wms.dao.IStockIncomeBillDAO;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @Setter
    private IStockIncomeBillDAO stockIncomeBillDAO;
    @Setter
    private IProductStockService productStockService;

    //抽象初始化明细订单并保存新明细订单的方法
    private void parseStockIncome(StockIncomeBill bill) {
        //初始化总金额和总数量
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        //遍历明细订单
        for (StockIncomeBillItem item : bill.getItems()) {
            //设置one方的关联关系,让many方可以找到one方
            item.setBill(bill);//因为one放放弃了关系维护,所有让many方来维护
            //设置明细单小计金额
            item.setAmount(item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP));
            //主订单的数量累积,和总金额累积
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
        }
    }

    public void save(StockIncomeBill bill) {
        //设置制单人和制单时间
        bill.setInputUser(UserContext.getCurrentEmployee());
        bill.setInputTime(new Date());
        //设置审核状态为未审核
        bill.setStatus(StockIncomeBill.NORMAL);
        parseStockIncome(bill);

        //保存订单
        stockIncomeBillDAO.save(bill);
    }

    public void update(StockIncomeBill bill) {
        parseStockIncome(bill);
        stockIncomeBillDAO.update(bill);
    }

    public void audit(Long billId) {
        //获取采购入库单对象
        StockIncomeBill stockIncomeBill = stockIncomeBillDAO.get(billId);
        //判断入库单审核状态
        if (stockIncomeBill.getStatus() == StockIncomeBill.NORMAL) {
            //改变审核状态
            stockIncomeBill.setStatus(StockIncomeBill.AUDIT);
            //设置审核人信息
            stockIncomeBill.setAuditor(UserContext.getCurrentEmployee());
            stockIncomeBill.setAuditTime(new Date());
            //更新入库单
            stockIncomeBillDAO.update(stockIncomeBill);
            //处理库存信息
            for (StockIncomeBillItem item : stockIncomeBill.getItems()) {
                productStockService.income(item.getProduct(),stockIncomeBill.getDepot(),item.getNumber(),item.getCostPrice());
            }
        }
    }

    public void delete(StockIncomeBill stockIncomeBill) {
        stockIncomeBill = stockIncomeBillDAO.get(stockIncomeBill.getId());
        if (stockIncomeBill.getStatus() == StockIncomeBill.NORMAL) {
            stockIncomeBillDAO.delete(stockIncomeBill);
        }
    }


    public StockIncomeBill get(Long id) {
        return stockIncomeBillDAO.get(id);
    }

    public List listAll() {
        return stockIncomeBillDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return stockIncomeBillDAO.query(qo);
    }
}
