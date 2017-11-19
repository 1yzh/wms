package com._520it.wms.vo;

import lombok.Getter;

@Getter
public enum SaleGroupType {
    SALEMAN("obj.saleman.name","obj.saleman","销售人员"),
    PRODUCT("obj.product.name","obj.product","货品"),
    BRAND("obj.product.brand.name","obj.product.brand","品牌"),
    CLIENT("obj.client.name","obj.client","客户"),
    M("date_format(obj.vdate,'%Y-%m')","date_format(obj.vdate,'%Y-%m')","按日期分类(月)"),
    D("date_format(obj.vdate,'%Y-%m-%d')","date_format(obj.vdate,'%Y-%m')","按日期分类(日)");
    private String groupValue;
    private String groupName;
    private String groupBy;

    SaleGroupType(String groupValue, String groupBy,String groupName) {
        this.groupValue = groupValue;
        this.groupName = groupName;
        this.groupBy = groupBy;
    }
}
