package com._520it.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable {
    protected static final String LIST = "list";
    //将后台departmentService注入到action中，需要使用setter方法
    //实现ActionSupport接口的方法，可以把所有方法预先传入到这个方法里，此处只预先处理saveOrUpdate
    public void prepare() throws Exception {
    }
    public void putContext(String name,Object value){
        ActionContext.getContext().put(name,value);//高级查询需要的部门信息
    }
}
