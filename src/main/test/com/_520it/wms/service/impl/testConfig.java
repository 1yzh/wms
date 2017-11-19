package com._520it.wms.service.impl;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;
//定义一个方法用于将字符串进行加密，使用druid
public class testConfig {
@Test
    public void testConfigtools()throws Exception{
        //使用configtools方法进行加密，解密的设置在actioncontext文件中关于druid设置的那段
        String encrypt = ConfigTools.encrypt("admin");
        System.out.println(encrypt);
    }
}
