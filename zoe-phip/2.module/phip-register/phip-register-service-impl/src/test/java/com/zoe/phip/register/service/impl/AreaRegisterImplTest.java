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
        String doc = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
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
        List<AreaBaseInfo> baseInfoList = new ArrayList<>();

        AreaBaseInfo b1 = new AreaBaseInfo();
        b1.setCode("351000");
        baseInfoList.add(b1);

        AreaBaseInfo b2 = new AreaBaseInfo();
        b2.setCode("351008");
        baseInfoList.add(b2);
        String message = RegisterUtil.registerMessage(RegisterType.AREA_QUERY_CHILDREN, baseInfoList);

        System.out.println(message);
    }

    @Test
    public void testAreaHistoryRegistryQuery() throws Exception {

    }
}