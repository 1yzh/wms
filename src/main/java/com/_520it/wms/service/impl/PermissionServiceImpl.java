package com._520it.wms.service.impl;

import com._520it.wms.dao.IPermissionDAO;
import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.PermissionUtil;
import com._520it.wms.util.RequiredPermission;
import com._520it.wms.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {
    @Setter
    private IPermissionDAO permissionDAO;
    private ApplicationContext ctx;

    //上面实现ApplicationContextAware类后，spring可以通过此处注入ApplicationContext容器，用于拿容器中的action
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    public void delete(Permission entity) {
        permissionDAO.delete(entity);
    }

    public List listAll() {
        return permissionDAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return permissionDAO.query(qo);
    }

    public void reload() {

        /*创建一个集合，获取permission中的权限集合，病把权限集中每个表达式expression放入到set集合中。set集合不可重复*/
        Set<String> expressionSet = new HashSet<>();
        List<Permission> permissionList = permissionDAO.listAll();
        for (Permission permission : permissionList) {
            expressionSet.add(permission.getExpression());
        }

        Map<String, BaseAction> baseActionMap = ctx.getBeansOfType(BaseAction.class);//获取容器中的所有action类
        //遍历所有的action
        for (BaseAction action : baseActionMap.values()) {//获取MAP的集合用。values方法
            //获取action中的所有方法的名称数组
            Method[] methods = action.getClass().getDeclaredMethods();
            for (Method method : methods) {//遍历获取到的所有方法
                //根据方法获取方法上面的注解，如果有注解，返回注解，没有则返回null；
                RequiredPermission rq = method.getAnnotation(RequiredPermission.class);
                if (rq != null) {

                    //创建一个静态方法，用于获取权限的表达式expression：如xxx.xxx.xxx:xxx
                    String expression = PermissionUtil.build(method);
                    //判断获取到的expression表达式是否在expressionSet中，如果不在则进行保存，避免页面重复加载权限
                    if (!expressionSet.contains(expression)) {
                        Permission p = new Permission();
                        p.setName(rq.value());
                        p.setExpression(expression);
                        permissionDAO.save(p);
                    }
                }
            }

        }
    }


}
