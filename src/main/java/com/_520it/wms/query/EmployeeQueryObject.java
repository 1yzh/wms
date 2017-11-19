package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
    protected String keyword;//页面上传递的搜索关键字
    protected Long deptId;//页面上传递来的部门关键词
    public void customizQuery() {
        if (StringUtils.isNotBlank(keyword)) {
            addQuery("(obj.name like ? or obj.email like ?)","%" + keyword + "%" ,"%" + keyword + "%");
        }
        if (deptId != null && deptId != -1) {
            addQuery("obj.dept.id=?", deptId);
        }
    }
}
