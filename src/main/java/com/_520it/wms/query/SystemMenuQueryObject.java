package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemMenuQueryObject extends QueryObject {
    private Long parentId = -1L;//增加查询条件 父级菜单编号为-1;
    private String parentSn;
    public void customizQuery() {
        if (parentId == -1) {
            //增加查询条件,如果父级菜单是-1,就查询没有父级菜单的.或者用is null也行,
            // parent的外键是parent_id,这里相当于parent_id=null
            addQuery("obj.parent=null");
        } else {
            addQuery("obj.parent.id=?", parentId);//parentId可以通过qo.parentId=? 传入
        }
    }
}
