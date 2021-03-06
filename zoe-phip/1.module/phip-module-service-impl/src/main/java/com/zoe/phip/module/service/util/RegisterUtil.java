package com.zoe.phip.module.service.util;

import com.zoe.phip.infrastructure.bean.BeanFactory;
import com.zoe.phip.infrastructure.parser.Parser;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.module.service.entity.base.Acknowledgement;
import com.zoe.phip.module.service.entity.base.RegisterEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by zhangwenbin on 2016/4/19.
 */
public final class RegisterUtil {

    @Autowired
    private static Parser parser;

    public static String registerMessage(String template, Object o) {
        Map map = MapUtil.createMap(m -> m.put("Model", o));
        String result = getParser().parseByResource(template, map);
        map.clear();
        map = null;
        //删除属性为空的节点
        Document xd;
        try {
            xd = DocumentHelper.parseText(result);
            ProcessXmlUtil.deleteNullAttributes(xd.getRootElement());
            return xd.asXML();
        } catch (DocumentException ex) {
        }
        return result;
    }

    public static String responseFailed(RegisterEntity entity, String errorMsg, String path) {
        Acknowledgement acknowledgement = new Acknowledgement();
        acknowledgement.setTypeCode("AE");
        acknowledgement.setText(errorMsg);
        entity.setAcknowledgement(acknowledgement);
        return RegisterUtil.registerMessage(path, entity);
    }

    private static Parser getParser() {
        if (parser == null)
            return BeanFactory.getBean("BeetlParserImpl");
        else
            return parser;
    }
}
