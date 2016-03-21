package com.zoe.phip.infrastructure.util;


import org.junit.Test;
import sun.java2d.SurfaceDataProxy;

import java.util.Calendar;

/**
 * Created by zengjiyang on 2016/3/15.
 */
public class StringUtilTest {

    @Test
    public void testToJsonString() throws Exception {
        User user = new User();
        user.setAge(20);
        user.setName("test");

        String json = StringUtil.toJsonString(user);
        User u1 = StringUtil.parseJsonObject(json, User.class);
    }

    @Test
    public void md5Test(){
        Calendar cl=Calendar.getInstance();
        cl.add(Calendar.MINUTE,10);
        System.out.println(cl.getTime());
        String result=StringUtil.toMD5("admin");
        result=StringUtil.toMD5("123"+result+"admin");
        System.out.println(result);
    }

}