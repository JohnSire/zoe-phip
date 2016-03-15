package com.zoe.phip.infrastructure.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/3/15.
 */
public class StringUtilTest {

    @Test
    public void testToJsonString() throws Exception {
        User user=new User();
        user.setAge(20);
        user.setName("test");
        String json= StringUtil.toJsonString(user);
        User u1= StringUtil.parseJsonObject(json,User.class);

    }

}