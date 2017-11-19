package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
    @Setter
public class RoleQueryObject extends QueryObject {
    protected String keyword;//页面上传递的搜索关键字
    protected Long deptId=-1L;//页面上传递来的部门关键词
    private String key="%" + keyword + "%";
    public void customizQuery() {
    }
}
