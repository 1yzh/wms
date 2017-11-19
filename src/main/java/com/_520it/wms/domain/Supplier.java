package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Supplier extends BaseDomain{
    private String name;//供应商名称
    private String phone;//供应商联系电话
    private String address;//供应商地址
}
