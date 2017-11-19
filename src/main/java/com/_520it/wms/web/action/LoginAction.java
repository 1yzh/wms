package com._520it.wms.web.action;

import com._520it.wms.service.IEmployeeService;
import lombok.Setter;

public class LoginAction extends BaseAction {
    @Setter
    IEmployeeService employeeService;
    @Setter
    private String username;
    @Setter
    private String password;

    public String execute(){
        try{
            employeeService.checkLogin(username, password);
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
            return LOGIN;//出现异常信息后返回登录页面
        }

            return SUCCESS;
    }
}

