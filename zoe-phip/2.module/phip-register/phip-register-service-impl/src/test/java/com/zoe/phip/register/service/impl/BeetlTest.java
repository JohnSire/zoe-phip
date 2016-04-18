package com.zoe.phip.register.service.impl;


import com.zoe.phip.infrastructure.parser.BeetlParserImpl;
import com.zoe.phip.infrastructure.parser.Parser;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.infrastructure.util.XmlUtil;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.model.base.Receiver;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Test;

import com.zoe.phip.register.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by zhangwenbin on 2016/4/18.
 */
public class BeetlTest extends BaseTest {


    @Autowired
    private Parser parser;

    @Test

    public void toResponse() {
        parser.initialize();
        Acknowledgement model = new Acknowledgement();

        model.setTypeCode("AE");
        model.setExtension(UUID.randomUUID().toString());
        model.setText("失败");
        String msg = parser.parseByResource("template/响应消息结果.tbl", MapUtil.createMap(m -> {
            m.put("Model", model);
        }));
        System.out.println(msg);

    }
}

