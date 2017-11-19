package com._520it.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.Brand;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.BrandQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class BrandAction extends BaseAction {

    @Setter//Brand页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    IBrandService brandService;
    @Setter
    IRoleService roleService;
    @Getter
    Brand brand = new Brand();
    @Getter
    BrandQueryObject qo = new BrandQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("品牌列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            PageResult result = brandService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("品牌编辑")
    public String input() throws Exception {
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
        return "input";
    }

    @RequiredPermission("品牌保存/更新")
    public String saveOrUpdate(){
        try {
            if (brand.getId() != null) {
                brandService.update(brand);
                addActionMessage("更新成功");
            } else {
                brandService.save(brand);
                addActionMessage("添加成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("品牌删除")
    public String delete() throws Exception {
        brandService.delete(brand);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }



    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到Brand信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
    }


}
