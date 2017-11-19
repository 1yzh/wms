package com._520it.wms.web.action;

import com._520it.wms.domain.Product;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IProductService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.FileUploadUtil;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductAction extends BaseAction {

    @Setter//Product页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    IProductService productService;
    @Setter
    IBrandService brandService;
    @Setter
    IRoleService roleService;
    @Getter
    Product product = new Product();
    @Getter
    ProductQueryObject qo = new ProductQueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Setter
    private File pic;//上文的文件对象
    @Setter
    private String picFileName;//上传文件的名称

    @Override
    @RequiredPermission("货品列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            PageResult result = productService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    public String showSelectProduct() {

        try {
            qo.setPageSize(50);
            PageResult result = productService.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "showSelectProduct";
    }

    @RequiredPermission("货品编辑")
    public String input() throws Exception {
            putContext("brands", brandService.listAll());
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
        return "input";
    }

    @RequiredPermission("货品保存/更新")
    public String saveOrUpdate() throws Exception{
        if(product.getId()!=null && StringUtils.isNotBlank(product.getImagePath())){
            FileUploadUtil.deleteFile(product.getImagePath());
        }
        String saveFilePath = FileUploadUtil.uploadFile(pic, picFileName);
        product.setImagePath(saveFilePath);
        try {
            if (product.getId() != null) {
                productService.update(product);
                addActionMessage("更新成功");
            } else {
                productService.save(product);
                addActionMessage("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("货品删除")
    public String delete() throws Exception {
        if(product.getId()!=null) {
            product=productService.get(product.getId());
            if(StringUtils.isNotBlank(product.getImagePath())){
                FileUploadUtil.deleteFile(product.getImagePath());
            }
            productService.delete(product);
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().print("删除成功");
        }
        return NONE;
    }


    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到Product信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
        product.setBrand(null);
    }


}
