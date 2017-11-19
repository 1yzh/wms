package com._520it.wms.vo;

import lombok.Getter;

@Getter
public enum OrderGroupType {
    EMPLOYEE("obj.bill.inputUser.name","obj.bill.inputUser","订货人员"),
    PRODUCT("obj.product.name","obj.product","货品"),
    BRAND("obj.product.brand.name","obj.product.brand","品牌"),
    SUPPLIER("obj.bill.supplier.name","obj.bill.supplier","供应商"),
    M("date_format(obj.bill.vdate,'%Y-%m')","date_format(obj.bill.vdate,'%Y-%m')","按日期分类(月)"),
    D("date_format(obj.bill.vdate,'%Y-%m-%d')","date_format(obj.bill.vdate,'%Y-%m')","按日期分类(日)");
    private String groupValue;
    private String groupBy;
    private String groupName;

    OrderGroupType(String groupValue, String groupBy, String groupName) {
        this.groupValue = groupValue;
        this.groupName = groupName;
        this.groupBy = groupBy;
    }
}
