package com._520it.wms.query;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Setter@Getter//订货报表查询对象
public class OrderChartQueryObject extends QueryObject {
    private Date beginTime;//业务开始时间
    private Date endTime;//业务结束时间
    private String keyword;//货品名称或编码
    private Long supplierId=-1L;//供应商
    private Long brandId=-1L;//品牌
    private String groupType="employee";//分组类型
    public void customizQuery() {
        if(beginTime!=null){
            addQuery("obj.bill.vdate>=?", DateUtil.getBeginTime(beginTime));
        }
        if(endTime!=null){
            addQuery("obj.bill.vdate=?",DateUtil.getEndTime(endTime));
        }
        if(StringUtils.isNotBlank(keyword)){
            String key="%"+keyword+"%";
            addQuery("(obj.product.name like ? or obj.product.sn like ?)",key,key);
        }
        if(supplierId>0){
            addQuery("obj.bill.supplier.id=?",supplierId);
        }
        if(brandId>0){
            addQuery("obj.product.brand.id=?",brandId);
        }
        addQuery("obj.bill.status=?", OrderBill.AUDIT);
    }
}
