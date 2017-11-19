package com._520it.wms.service.impl;

import com._520it.wms.dao.IChartDAO;
import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IChartService;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;
import lombok.Setter;

import java.util.List;

public class ChartServiceImpl implements IChartService {
    @Setter
    private IChartDAO chartDAO;

    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo) {

        return chartDAO.queryOrderChart(qo);
    }

    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        return chartDAO.querySaleChart(qo);
    }
}
