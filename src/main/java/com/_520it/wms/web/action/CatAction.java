package com._520it.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.Cat;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.CatQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.ICatService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class CatAction extends BaseAction {

    @Setter//Cat页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    ICatService catService;
    @Setter
    IRoleService roleService;
    @Getter
    Cat cat = new Cat();
    @Getter
    CatQueryObject qo = new CatQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("Cat列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            PageResult result = catService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("Cat编辑")
    public String input() throws Exception {
        if (cat.getId() != null) {
            cat = catService.get(cat.getId());
        }
        return "input";
    }

    @RequiredPermission("Cat保存/更新")
    public String saveOrUpdate(){
        try {
            if (cat.getId() != null) {
                catService.update(cat);
                addActionMessage("更新成功");
            } else {
                catService.save(cat);
                addActionMessage("添加成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("Cat删除")
    public String delete() throws Exception {
        catService.delete(cat);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }



    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到Cat信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (cat.getId() != null) {
            cat = catService.get(cat.getId());
        }
    }


}
