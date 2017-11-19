package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SystemMenu extends BaseDomain implements IJsonObject{
    private String name;//菜单名
    private String url;//菜单url
    private String sn;//菜单编号

    private SystemMenu parent;//父级菜单
    private List<SystemMenu> children = new ArrayList<>();//子集菜单

    public Object toJson() {
        Map<String,Object> jsonMap=new HashMap<>();
        jsonMap.put("id",id);
        jsonMap.put("name",name);
        jsonMap.put("action",url);
        jsonMap.put("pId",this.getParent().getId());
        return jsonMap;
    }
}
