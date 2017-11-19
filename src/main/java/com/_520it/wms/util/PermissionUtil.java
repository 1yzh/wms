package com._520it.wms.util;

import java.lang.reflect.Method;

public class PermissionUtil {
    public static String build(Method method) {
        String className=method.getDeclaringClass().getName();//获取方法的类的权限顶名称
        return className+":"+method.getName();//获取方法的名称
    }
}
