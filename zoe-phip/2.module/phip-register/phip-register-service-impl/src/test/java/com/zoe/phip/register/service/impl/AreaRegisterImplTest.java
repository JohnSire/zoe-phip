package com.zoe.phip.register.service.impl;

import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.service.external.IAreaRegister;
import com.zoe.phip.register.service.impl.external.AreaRegisterImpl;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.module.service.util.RegisterUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengjiyang on 2016/4/11.
 */
public class AreaRegisterImplTest extends BaseTest {


    @Autowired
    private AreaRegisterImpl areaRegister;

    @Test
    public void testAddAreaRequest() throws Exception {
        /*ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context-consumer.xml");
        classPathXmlApplicationContext.start();

        IAreaRegister helloService = (IAreaRegister) classPathXmlApplicationContext.getBean("AreaRegister");
        String world = helloService.addAreaRequest("World");

        System.out.println("=====================================");
        System.out.println(world);
        System.out.println("=====================================");*/

        String doc = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<data>\n" +
                "    <Code value=\"test01\"></Code>\n" +
                "    <Name value=\"test01\"></Name>\n" +
                "    <Pid value=\"\"></Pid>\n" +
                "    <BuildTime value=\"\"></BuildTime>\n" +
                "    <AreaCancellationDate value=\"\"></AreaCancellationDate>\n" +
                "    <AreaCancelReason value=\"\"></AreaCancelReason>\n" +
                "    <HistoryAreaId value=\"\"></HistoryAreaId>\n" +
                "</data>";
        String result = areaRegister.addAreaRequest(doc);
        System.out.println(result);
    }

    @Test
    public void testUpdateAreaRequest() throws Exception {
        String doc = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<data>\n" +
                "    <Id value=\"873f7f0e-3b77-413c-a2b3-9a91f8928554\"></Id>\n" +
                "    <Code value=\"test01\"></Code>\n" +
                "    <Name value=\"test01\"></Name>\n" +
                "    <Pid value=\"\"></Pid>\n" +
                "    <BuildTime value=\"\"></BuildTime>\n" +
                "    <AreaCancellationDate value=\"\"></AreaCancellationDate>\n" +
                "    <AreaCancelReason value=\"\"></AreaCancelReason>\n" +
                "    <HistoryAreaId value=\"9BC2CEB4B2EE47488703F5A45EB998E0\"></HistoryAreaId>\n" +
                "</data>";
        String result = areaRegister.updateAreaRequest(doc);
        System.out.println(result);
    }

    @Test
    public void testAreaDetailQuery() throws Exception {
        String doc = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<data>\n" +
                "    <Id value=\"873f7f0e-3b77-413c-a2b3-9a91f8928554\"></Id>\n" +
                "</data>";
        String result = areaRegister.areaDetailQuery(doc);
        System.out.println(result);
    }

    @Test
    public void testAreaChildrenRegistryQuery() throws Exception {
        String doc = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<data>\n" +
                "    <Id value=\"9BC2CEB4B2EE47488703F5A45EB998E0\"></Id>\n" +
                "</data>";
        String result = areaRegister.areaChildrenRegistryQuery(doc);
        System.out.println(result);
    }

    @Test
    public void testAreaHistoryRegistryQuery() throws Exception {
        String doc = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<data>\n" +
                "    <HistoryId value=\"9BC2CEB4B2EE47488703F5A45EB998E0\"></HistoryId>\n" +
                "</data>";
        String result = areaRegister.areaHistoryRegistryQuery(doc);
        System.out.println(result);
    }
}