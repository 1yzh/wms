package com._520it.wms.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Product extends BaseDomain {
    private String name;//产品名称
    private String sn;//产品编号
    private BigDecimal costPrice;//成本价额
    private BigDecimal salePrice;//销售价格
    private String imagePath;//图片存放路径
    private String intro;//产品介绍
    private Brand brand;//产品品牌

    public String getJasonString(){
        Map<String,Object> jasonMap=new HashMap<>();
        jasonMap.put("id",id);
        jasonMap.put("name",name);
        jasonMap.put("brandName",brand.getName());
        jasonMap.put("costPrice",costPrice);
        jasonMap.put("salePrice",salePrice);
        String jsonString = JSON.toJSONString(jasonMap);
        return jsonString;
    }

    public String getSmallImagePath() {
        if (imagePath == null || "".equals(imagePath)) {
            return "";
        } else {
            int index = imagePath.lastIndexOf(".");
            return imagePath.substring(0, index) + "_small" + imagePath.substring(index);
        }
    }
}
