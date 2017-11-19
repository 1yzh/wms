package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Department extends BaseDomain {
    private String name;
    private String sn;
}
