package com._520it.wms.dao;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;

import java.util.List;

public interface IChartDAO {
    List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);

    List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
}
