package com.zoe.phip.register.util;

import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.service.impl.external.PatientRegisterImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

/**
 * Created by zengjiyang on 2016/4/18.
 */
public class ModelXmlBuilder extends BaseTest {


    @Test
    public void builder() throws Exception {
        String head = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
        StringBuffer buffer = new StringBuffer();
        buffer.append(head);
        buffer.append("\r\n");
        Class clazz = AreaBaseInfo.class;
        Field[] fields = clazz.getDeclaredFields();
        String className = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1, clazz.getName().length());

        buffer.append("<" + className + ">" + "\r\n");
        for (Field field : fields) {
            String fieldName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            buffer.append("<" + fieldName + " descr = \"\" path=" + "\"//" + fieldName + "/@value\"" + "/>");
            buffer.append("\r\n");
        }
        buffer.append("</" + className + ">");
        System.out.println(buffer.toString());
        buffer.delete(0, buffer.length() - 1);
    }


}