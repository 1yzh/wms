package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockOutcomeBillQueryObject extends QueryObject {
        private Date beginTime;//业务开始时间
        private Date endTime;//业务结束时间
        private Long depotId = -1L;//仓库ID
        private Long clientId = -1L;//销售人员ID
        private int status = -1;//审核状态

        public void customizQuery() {
                if(beginTime!=null){
                        addQuery("obj.vdate>=?", DateUtil.getBeginTime(beginTime));
                }
                if(endTime!=null){
                        addQuery("obj.vdate<=?",DateUtil.getEndTime(endTime));
                }
                if(clientId>0){
                        addQuery("obj.client.id=?",clientId);
                }
                if(status>=0){
                        addQuery("obj.status=?",status);
                }
                if(depotId>=0){
                        addQuery("obj.depot.id=?",depotId);
                }
        }
}
