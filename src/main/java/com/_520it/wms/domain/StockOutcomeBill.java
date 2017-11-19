package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter //销售出库单
public class StockOutcomeBill extends BaseDomain {
    public static final int NORMAL = 0;//未审核状态
    public static final int AUDIT = 1;//已审核状态
    private String sn;//销售出库单编号
    private Date vdate;//销售出库时间
    private Depot depot;//仓库
    private int status;//审核状态
    private BigDecimal totalAmount;//销售单总金额
    private BigDecimal totalNumber;//总数量
    private Date auditTime;//审核时间
    private Date inputTime;//录入时间
    private Employee inputUser;//录入人
    private Employee auditor;//审核人
    private Client client;//销售人员

    private List<StockOutcomeBillItem> items = new ArrayList<>();//单据明细
}
