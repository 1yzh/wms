package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryObject {
    @Getter
    @Setter
    private Integer currentPage = 1;
    @Getter
    @Setter
    private Integer pageSize = 5;

    protected List<String> conditions = new ArrayList<String>();//查询语句
    protected List<Object> params = new ArrayList<Object>();//查询条件
    private boolean isInit;

    public void customizQuery(){};

    public String getQuery() {
        //设置一个isinit变量，根据其状态判断customizQuery是否只调用了一次，在分页查询时可能会调用2次
        if (!isInit) {
            customizQuery();
        }
        isInit = true;
        if (conditions.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(80);
        //注意where和and两边要有空格
        //StringUtils.join方法是将conditions语句用and连接起来
        sb.append(" WHERE ").append(StringUtils.join(conditions, " AND "));
        return sb.toString();
    }

    public List<Object> getParams() {
        return params;
    }

    //创建此方法用于向集合中传递参数(condition语句和参数数组）
    public void addQuery(String condition, Object... args) {
        conditions.add(condition);
        params.addAll(Arrays.asList(args));
    }
}
