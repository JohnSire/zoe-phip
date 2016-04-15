package com.zoe.phip.register.service.impl;

import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.service.IAreaRegister;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/11.
 */
public class AreaRegisterImplTest extends BaseTest {


    @Autowired
    private AreaRegisterImpl areaRegister;

    @org.junit.Test
    public void testAddAreaRequest() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context-consumer.xml");
        classPathXmlApplicationContext.start();

        IAreaRegister helloService = (IAreaRegister) classPathXmlApplicationContext.getBean("AreaRegister");
        String world = helloService.addAreaRequest("World");

        System.out.println("=====================================");
        System.out.println(world);
        System.out.println("=====================================");
    }

    @Test
    public void testUpdateAreaRequest() throws Exception {
        String doc="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<root>\n" +
                "    <code value=\"xiamen\"/>\n" +
                "\t<name value=\"厦门\"/>\n" +
                "\t<pareaId value=\"\"/>\n" +
                "\t<buildTime value=\"20160415\"/>\n" +
                "\t<areaCancellationDate value=\"\"/>\n" +
                "\t<areaCancelReson value=\"\"/>\n" +
                "\t<historyAreaId value=\"\"/>\n" +
                "</root>";
        areaRegister.addAreaRequest(doc);

    }

    @Test
    public void testAreaDetailQuery() throws Exception {

    }

    @Test
    public void testAreaChildrenRegistryQuery() throws Exception {

    }

    @Test
    public void testAreaHistoryRegistryQuery() throws Exception {

    }
}