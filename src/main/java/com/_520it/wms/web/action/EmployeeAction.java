package com._520it.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.Employee;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAction extends BaseAction {

    @Setter//员工页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    IEmployeeService employeeService;
    @Setter
    IRoleService roleService;
    @Getter
    Employee employee = new Employee();
    @Getter
    EmployeeQueryObject qo = new EmployeeQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("员工列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            putContext("depts", departmentService.listAll());//高级查询需要的部门信息
            PageResult result = employeeService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("员工编辑")
    public String input() throws Exception {
        //需要获得部门信息，此处需要将部门信息传递到页面中
        putContext("depts", departmentService.listAll());
        putContext("roles", roleService.listAll());

        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());
        }
        return "input";
    }

    @RequiredPermission("员工保存/更新")
    public String saveOrUpdate(){
        try {
            if (employee.getId() != null) {
                employeeService.update(employee);
                addActionMessage("更新成功");
            } else {
                employeeService.save(employee);
                addActionMessage("添加成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("员工删除")
    public String delete() throws Exception {
        employeeService.delete(employee);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    @RequiredPermission("员工批量删除")
    public String batchDelete() throws Exception {
        if (ids.size() > 0) {
            System.out.println(ids);
        employeeService.batchDelete(ids);
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().print("批量删除成功");
        }
        return NONE;
    }


    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到员工信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());
        }
        employee.getRoles().clear();//在编辑员工信息时,变更角色,清除角色信息;
        employee.setDept(null);//在编辑员工信息时,变更部门,清除部门信息;
    }


}
