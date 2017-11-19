package com._520it.generator;

import com._520it.wms.domain.BaseDomain;
import lombok.Getter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

//描述类的信息
@Getter
public class ClassInfo {
    private String basePkg;
    private String className;
    private String objectName;
    private List<String> props = new ArrayList<>();

    public ClassInfo(Class<?> clz) throws Exception {//传入一份字节码,获取这个字节码类的包名和类名
        className = clz.getSimpleName();
        basePkg = clz.getPackage().getName();
        basePkg = basePkg.substring(0, basePkg.lastIndexOf("."));//获取basePkg字符串从0到最后一个点之间的字符串
        objectName = className.substring(0, 1).toLowerCase() + className.substring(1);//获取对象名,
        //获取javabean中所有属性名
        BeanInfo beanInfo = Introspector.getBeanInfo(clz, BaseDomain.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            props.add(pd.getName());
        }
    }
}
