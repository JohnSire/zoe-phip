package com.zoe.phip.infrastructure.util;


import com.zoe.phip.infrastructure.logger.LoggerImpl;
import org.junit.Test;

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
        String result=StringUtil.toMD5("admin");
        result=StringUtil.toMD5("123"+result+"admin");
        System.out.println(result);
    }

    @Test
    public void doLogger() {
        com.zoe.phip.infrastructure.logger.Logger logger = new LoggerImpl();
        logger.debug("{}","debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}