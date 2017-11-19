package ${basePkg}.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import ${basePkg}.domain.${className};
import ${basePkg}.page.PageResult;
import ${basePkg}.query.${className}QueryObject;
import ${basePkg}.service.IDepartmentService;
import ${basePkg}.service.I${className}Service;
import ${basePkg}.service.IRoleService;
import ${basePkg}.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class ${className}Action extends BaseAction {

    @Setter//${className}页面需要获取部门信息，需要用到departmentservice。配置文件中也同样需要配置
            IDepartmentService departmentService;
    @Setter
    I${className}Service ${objectName}Service;
    @Setter
    IRoleService roleService;
    @Getter
    ${className} ${objectName} = new ${className}();
    @Getter
    ${className}QueryObject qo = new ${className}QueryObject();

    @Setter
    List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("${className}列表")
    @InputConfig(methodName = "input")
    public String execute() {

        try {
            PageResult result = ${objectName}Service.query(qo);
            putContext("pageResult", result);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("${className}编辑")
    public String input() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
        return "input";
    }

    @RequiredPermission("${className}保存/更新")
    public String saveOrUpdate(){
        try {
            if (${objectName}.getId() != null) {
                ${objectName}Service.update(${objectName});
                addActionMessage("更新成功");
            } else {
                ${objectName}Service.save(${objectName});
                addActionMessage("添加成功");
            }
        } catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("${className}删除")
    public String delete() throws Exception {
        ${objectName}Service.delete(${objectName});
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }



    /*预先处理saveorupdate方法，方法名为prepareSaveOrUpdate()，第一个s要大写
    * 配置struts文件，默认拦截器使用paramsPrepareParamsStack
    * 可以预先获取到${className}信息，防止密码等参数丢失*/
    public void prepareSaveOrUpdate() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
    }


}
