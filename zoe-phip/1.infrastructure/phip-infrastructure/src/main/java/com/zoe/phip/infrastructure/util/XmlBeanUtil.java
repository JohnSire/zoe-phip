package com.zoe.phip.infrastructure.util;

import com.zoe.phip.infrastructure.annotation.XPath;
import org.dom4j.Document;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by zhangwenbin on 2016/4/12.
 */
public final class XmlBeanUtil {

    public static <T> T toBean(Document document, T clazz) throws Exception {

        //获取所有的属性
        Field[] fields = clazz.getClass().getDeclaredFields();

        clazz = (T) clazz.getClass().newInstance();
        for (Field field : fields) {

            //获取注解
            XPath xPath = field.getAnnotation(XPath.class);

            if (xPath == null) continue;
            //路径
            String path = xPath.value();
            //默认值
            String defValue = xPath.defaultValue();
            //级别
            int level = xPath.level();
            //属性名
            String fieldName = field.getName();

            //将第一位转化为大写，便于获取实体类中set,get方法
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            String fieldType = field.getType().toString();

            String value = StringUtil.isNullOrWhiteSpace(path) ? defValue :
                    document.selectSingleNode(path).getText();

            if (fieldType.endsWith("String")) {
                //获得set方法
                Method method = clazz.getClass().getMethod("set" + fieldName, String.class);
                method.invoke(clazz, value);
            } else if (fieldType.endsWith("Int")) {
                Method method = clazz.getClass().getMethod("set" + fieldName, int.class);
                method.invoke(clazz, Integer.parseInt(value));
            } else if (fieldType.endsWith("Integer")) {
                Method method = clazz.getClass().getMethod("set" + fieldName, Integer.class);
                method.invoke(clazz, new Integer(value));
            } else if (fieldType.endsWith("double")) {
                Method method = clazz.getClass().getMethod("set" + fieldName, double.class);
                method.invoke(clazz, Double.parseDouble(value));
            } else if (fieldType.endsWith("double")) {
                Method method = clazz.getClass().getMethod("set" + fieldName, Double.class);
                method.invoke(clazz, new Double(value));
            }  else if (fieldType.endsWith("Date")) {
                Method method = clazz.getClass().getMethod("set" + fieldName, Date.class);
                method.invoke(clazz, DateUtil.stringToDateTime(value));
            } else if (fieldType.endsWith("Boolean")) {
                Method method = clazz.getClass().getMethod("set" + fieldName, Boolean.class);
                method.invoke(clazz, (value.toUpperCase() == "TRUE" || value.toUpperCase() == "1") ? new Boolean(true) :
                        new Boolean(false));
            } else if (fieldType.endsWith("boolean")) {
                Method method = clazz.getClass().getMethod("set" + fieldName, Boolean.class);
                method.invoke(clazz, (value.toUpperCase() == "TRUE" || value.toUpperCase() == "1") ? true :
                        false);
            } else if (fieldType.endsWith("List")) {
                // TODO: 2016/4/13
                Type fc = field.getGenericType();
                if (fc instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) fc;
                    Class parameterClazz = (Class) pt.getActualTypeArguments()[0]; //
                }
            }
        }
        return clazz;
    }
}
