package com._520it.wms.util;

import com._520it.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

public class UserContext {
    public static final String USER_IN_SESSION = "USER_IN_SESSION";
    public static final String PERMISSION_IN_SESSION = "PERMISSION_IN_SESSION";

    public static void setCurrentEmployee(Employee currentUser) {
        if (currentUser != null) {
            ActionContext.getContext().getSession().put(USER_IN_SESSION, currentUser);
        } else {
            ActionContext.getContext().getSession().clear();//如果用户为空,则注销session
        }
    }

    public static Employee getCurrentEmployee() {
        return (Employee) ActionContext.getContext().getSession().get(USER_IN_SESSION);
    }


    public static void setCurrentPermission(Set<String> expressionSet) {
        ActionContext.getContext().getSession().put(PERMISSION_IN_SESSION, expressionSet);
    }

    public static Set<String> getCurrentPermission() {
       return (Set<String>) ActionContext.getContext().getSession().get(PERMISSION_IN_SESSION);
    }
}
