package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Setter@Getter//销售报表查询对象
public class SaleChartQueryObject extends QueryObject {
    private Date beginTime;//业务开始时间
    private Date endTime;//业务结束时间
    private String keyword;//货品名称或编码
    private Long clientId=-1L;//客户
    private Long brandId=-1L;//品牌
    private String groupType="saleman";//分组类型
    public void customizQuery() {
        if(beginTime!=null){
            addQuery("obj.vdate>=?", DateUtil.getBeginTime(beginTime));
        }
        if(endTime!=null){
            addQuery("obj.vdate=?",DateUtil.getEndTime(endTime));
        }
        if(StringUtils.isNotBlank(keyword)){
            String key="%"+keyword+"%";
            addQuery("(obj.product.name like ? or obj.product.sn like ?)",key,key);
        }
        if(clientId>0){
            addQuery("obj.client.id=?",clientId);
        }
        if(brandId>0){
            addQuery("obj.product.brand.id=?",brandId);
        }
    }
}
