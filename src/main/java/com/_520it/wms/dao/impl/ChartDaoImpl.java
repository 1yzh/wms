package com._520it.wms.dao.impl;

import com._520it.wms.dao.IChartDAO;
import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.OrderGroupType;
import com._520it.wms.vo.SaleChartVO;
import com._520it.wms.vo.SaleGroupType;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ChartDaoImpl implements IChartDAO {
    @Setter
    private SessionFactory sessionFactory;

    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo) {
        Session session = sessionFactory.getCurrentSession();


        OrderGroupType groupType = OrderGroupType.valueOf(qo.getGroupType().toUpperCase());
        /*String groupByType="";
        String groupBy="";
        switch (qo.getGroupType()) {
            case "employee":
                groupByType = "obj.bill.inputUser.name";
                groupBy ="obj.bill.inputUser";
                break;
            case "product":
                groupByType = "obj.product.name";
                groupBy ="obj.product";
                break;
            case "brand":
                groupByType = "obj.product.brand.name";
                groupBy ="obj.product.brand";
                break;
            case "supplier":
                groupByType = "obj.bill.supplier.name";
                groupBy ="obj.bill.supplier";
                break;
            case "m":
                groupByType = "date_format(obj.bill.vdate,'%Y-%m')";
                groupBy ="date_format(obj.bill.vdate,'%Y-%m')";
                break;
            case "d":
                groupByType = "date_format(obj.bill.vdate,'%Y-%m-%d')";
                groupBy ="date_format(obj.bill.vdate,'%Y-%m')";
                break;
        }*/
        StringBuilder hql = new StringBuilder(150);
        hql.append("select new OrderChartVO(");
        hql.append(groupType.getGroupValue());
        hql.append(",sum(obj.number),sum(obj.amount)) from OrderBillItem obj");
        hql.append(qo.getQuery());
        hql.append(" group by ");
        hql.append(groupType.getGroupBy());
        Query query = session.createQuery(hql.toString());
        setParams(query, qo.getParams());
        return query.list();
    }

    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        Session session = sessionFactory.getCurrentSession();
        //根据qo.getGroupType()方法获取的字符串获取枚举中对应的值
        SaleGroupType groupType = SaleGroupType.valueOf(qo.getGroupType().toUpperCase());
        /*String groupByType="";
        String groupBy="";
        switch (qo.getGroupType()) {
            case "saleman":
                groupByType = "obj.saleman.name";
                groupBy ="obj.saleman";
                break;
            case "product":
                groupByType = "obj.product.name";
                groupBy ="obj.product";
                break;
            case "brand":
                groupByType = "obj.product.brand.name";
                groupBy ="obj.product.brand";
                break;
            case "client":
                groupByType = "obj.client.name";
                groupBy ="obj.client";
                break;
            case "m":
                groupByType = "date_format(obj.vdate,'%Y-%m')";
                groupBy ="date_format(obj.vdate,'%Y-%m')";
                break;
            case "d":
                groupByType = "date_format(obj.vdate,'%Y-%m-%d')";
                groupBy ="date_format(obj.vdate,'%Y-%m')";
                break;
        }*/
        StringBuilder hql = new StringBuilder(150);
        hql.append("select new SaleChartVO(");
        hql.append(groupType.getGroupValue());
        hql.append(",sum(obj.number),sum(obj.saleAmount),sum(obj.saleAmount-obj.costAmount)) from SaleAccount obj");
        hql.append(qo.getQuery());
        hql.append(" group by ");
        hql.append(groupType.getGroupBy());
        Query query = session.createQuery(hql.toString());
        setParams(query, qo.getParams());
        return query.list();    }

    public void setParams(Query query, List<Object> params) {
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
    }
}
