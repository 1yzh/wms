package com._520it.wms.service.impl;

import com._520it.wms.dao.ISaleAccountDAO;
import com._520it.wms.dao.IStockOutcomeBillDAO;
import com._520it.wms.domain.SaleAccount;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Setter
    private IStockOutcomeBillDAO stockOutcomeBillDAO;
    @Setter
    private IProductStockService productStockService;
    @Setter
    private ISaleAccountDAO saleAccountDAO;

    //抽象初始化明细订单并保存新明细订单的方法
    private void parseStockOutcome(StockOutcomeBill bill) {
        //初始化总金额和总数量
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        //遍历明细订单
        for (StockOutcomeBillItem item : bill.getItems()) {
            //设置one方的关联关系,让many方可以找到one方
            item.setBill(bill);//因为one放放弃了关系维护,所有让many方来维护
            //设置明细单小计金额
            item.setAmount(item.getSalePrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP));
            //主订单的数量累积,和总金额累积
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
        }
    }

    public void save(StockOutcomeBill bill) {
        //设置制单人和制单时间
        bill.setInputUser(UserContext.getCurrentEmployee());
        bill.setInputTime(new Date());
        //设置审核状态为未审核
        bill.setStatus(StockOutcomeBill.NORMAL);
        parseStockOutcome(bill);
        //保存订单
        stockOutcomeBillDAO.save(bill);
    }

    public void update(StockOutcomeBill bill) {
        parseStockOutcome(bill);
        stockOutcomeBillDAO.update(bill);
    }

    public void audit(Long billId) {
        //获取销售出库单对象
        StockOutcomeBill bill = stockOutcomeBillDAO.get(billId);
        //判断销售出库单审核状态
        if (bill.getStatus() == StockOutcomeBill.NORMAL) {
            //改变审核状态
            bill.setStatus(StockOutcomeBill.AUDIT);
            //设置审核人信息
            bill.setAuditor(UserContext.getCurrentEmployee());
            bill.setAuditTime(new Date());
            //更新销售出库单
            stockOutcomeBillDAO.update(bill);
            //处理库存信息
            //遍历出库单明细,根据出库单货品信息获取库存信息
            for (StockOutcomeBillItem item : bill.getItems()) {
                //处理库存
                BigDecimal costPrice = productStockService.outcome(item.getProduct(), bill.getDepot(), item.getNumber());
                //生成销售账
                SaleAccount sa = new SaleAccount();
                sa.setVdate(item.getBill().getAuditTime());
                sa.setNumber(item.getNumber());
                sa.setSalePrice(item.getSalePrice());
                sa.setSaleAmount(item.getAmount());
                sa.setProduct(item.getProduct());
                sa.setClient(item.getBill().getClient());
                sa.setSaleman(item.getBill().getInputUser());
                sa.setCostPrice(costPrice);
                sa.setCostAmount(costPrice.multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
                saleAccountDAO.save(sa);
            }
        }
    }

    public void delete(StockOutcomeBill stockOutcomeBill) {
        stockOutcomeBill = stockOutcomeBillDAO.get(stockOutcomeBill.getId());
        if (stockOutcomeBill.getStatus() == StockOutcomeBill.NORMAL) {
            stockOutcomeBillDAO.delete(stockOutcomeBill);
        }
    }


    public StockOutcomeBill get(Long id) {
        return stockOutcomeBillDAO.get(id);
    }

    public List listAll() {
        return stockOutcomeBillDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return stockOutcomeBillDAO.query(qo);
    }
}
