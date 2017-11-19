package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Client extends BaseDomain{//客户信息
    private String name;//客户名称
    private String sn;//客户编号
    private String phone;//联系电话
}
