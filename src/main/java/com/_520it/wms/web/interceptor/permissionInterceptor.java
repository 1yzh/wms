package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.PermissionUtil;
import com._520it.wms.util.RequiredPermission;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;

public class permissionInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //获取session中的用户
        Employee currentUser = UserContext.getCurrentEmployee();
        //判断用户是否超级管理员
        if (currentUser.isAdmin()) {
            return actionInvocation.invoke();//放行
        }
        //通过actionInvocation获取代理---获取请求的方法，返回的是一个字符串
        String methodName = actionInvocation.getProxy().getMethod();

        //通过actionInvocation获取代理---获取当前请求的action---获取其字节码---根据上面的method字符串获取方法,
        Method method = actionInvocation.getProxy().getAction().getClass().getMethod(methodName);
        //获取请求方法上面的注解标签
        RequiredPermission rq = method.getAnnotation(RequiredPermission.class);
        //判断是否有权限注解标签，没有则放行，
        if (rq == null) {
            return actionInvocation.invoke();//放行
        }
        //获取请求方法的表达式如：xxx.xxx:xxx
        String expression = PermissionUtil.build(method);
        //获取session中的permission
        Set<String> expressionSet = UserContext.getCurrentPermission();
        //判断session中的permisison是否含有请求方法的表达式
        if (expressionSet.contains(expression)) {
            return actionInvocation.invoke();//放行
        }
        return "nopermission";
    }
}
