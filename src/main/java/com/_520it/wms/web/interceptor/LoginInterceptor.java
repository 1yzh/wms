package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //通过获取actionInvocation的上下文，然后获取session，再通过USER—IN—SESSION获取对象
        Employee user_in_session = UserContext.getCurrentEmployee();
        if(user_in_session!=null){//如果session中对象不为空，说明登录成功，否则不成功返回 登录页面
            actionInvocation.invoke();
        }
        return "login";
    }
}
