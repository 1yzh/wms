package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockIncomeBillQueryObject extends QueryObject {
    private Date beginTime;//业务开始时间
    private Date endTime;//业务结束时间
    private Long depotId = -1L;//仓库ID
    private int status = -1;//审核状态

    public void customizQuery() {
        if(beginTime!=null){
            addQuery("obj.vdate>=?", DateUtil.getBeginTime(beginTime));
        }
        if(endTime!=null){
            addQuery("obj.vdate<=?",DateUtil.getEndTime(endTime));
        }
        if(depotId>0){
            addQuery("obj.depot.id=?",depotId);
        }
        if(status>=0){
            addQuery("obj.status=?",status);
        }
    }
}
