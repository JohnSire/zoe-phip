package com.zoe.phip.infrastructure.util;

import com.zoe.phip.infrastructure.annotation.XPath;
import org.dom4j.Document;

import java.lang.reflect.Field;

/**
 * Created by zhangwenbin on 2016/4/12.
 */
public final class XmlBeanUtil {

    public static <T> T toBean(Document document, T clazz) {


        //获取所有的属性
        Field[] fields = clazz.getClass().getDeclaredFields();
        for (Field field : fields) {
            //获取注解
            XPath xPath = field.getAnnotation(XPath.class);
            if (xPath == null) continue;
            String path = xPath.value();
            String defValue = xPath.defaultValue();
            String fieldName = field.getName();


        }
        return clazz;
    }
}
