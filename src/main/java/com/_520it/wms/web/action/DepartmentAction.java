package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

public class DepartmentAction extends BaseAction {
    @Setter//员工页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
    IDepartmentService departmentService;
    @Getter
    Department department = new Department();//创建department对象，接收页面表单传过来的数据。如department.xxx
    @Getter
    QueryObject qo=new QueryObject();
    @RequiredPermission("部门列表")
    @InputConfig(methodName = "input")//保存失败后返回input方法
    public String execute(){
        try{
            putContext("pageResult", departmentService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("部门编辑")
    public String input() throws Exception {
        if (department.getId() != null) {
            department = departmentService.get(department.getId());
        }

        return "input";
    }

    @RequiredPermission("部门保存/更新")
    public String saveOrUpdate(){
        try{
        if (department.getId() != null) {
            departmentService.update(department);
            addActionMessage("更新成功");
        }else {
            departmentService.save(department);
            addActionMessage("添加成功");
        }} catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("部门删除")
    public String delete(){
        try{
        departmentService.delete(department);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        } catch (Exception e){
        e.printStackTrace();
        addActionError(e.getMessage());
    }
        return NONE;
    }
    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
       * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
       * 可以预先获取到员工信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (department.getId() != null) {
            department = departmentService.get(department.getId());
        }
    }

}
