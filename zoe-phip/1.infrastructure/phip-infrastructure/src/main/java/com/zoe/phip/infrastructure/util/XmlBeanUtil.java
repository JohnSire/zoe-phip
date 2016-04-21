package com.zoe.phip.infrastructure.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zhangwenbin on 2016/4/12.
 */
public final class XmlBeanUtil {

    /**
     * 将xml转换成java bean
     * @param document 要转换的xml
     * @param clazz 转换后的实体类
     * @param parserDoc 转换适配xml
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T toBean(Document document, Class<T> clazz,Document parserDoc) throws Exception {
        if(document==null||parserDoc==null){
            throw new Exception("document or parserDoc could not be null!");
        }
        //获取所有的属性
        Field[] fields = clazz.getDeclaredFields();

        T instance =  clazz.newInstance();
        for (Field field : fields) {
            //属性名
            String fieldName = field.getName();

            //将第一位转化为大写，便于获取实体类中set,get方法
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            XPath x = parserDoc.createXPath("//"+fieldName);
            //路径
            if((Element)x.selectSingleNode(parserDoc)==null
                        ||((Element)x.selectSingleNode(parserDoc)).attribute("path")==null){
                    continue;
            }
            Attribute pathAttr=((Element)x.selectSingleNode(parserDoc)).attribute("path");
            String path =pathAttr.getValue();// xPath.value();
            //默认值
            String defValue =((Element)x.selectSingleNode(parserDoc)).attribute("defValue")!=null?
                    ((Element)x.selectSingleNode(parserDoc)).attribute("defValue").getValue():"";
            String fieldType = field.getType().toString();
            String value = defValue;
            if(!StringUtil.isNullOrWhiteSpace(path))
            {
                XPath xpath= document.createXPath(path);
                value=xpath.selectSingleNode(document).getText();
            }
            System.out.println(path);//todo 删除这句代码
            System.out.println(value);//todo 删除这句代码
            if(value==""){
                continue;
            }
            Method method = clazz.getMethod("set" + fieldName,field.getType());
            if (fieldType.endsWith("String")) {
                //获得set方法
                method.invoke(instance, value);
            } else if (fieldType.endsWith("int")) {
                method.invoke(instance, Integer.parseInt(value));
            } else if (fieldType.endsWith("Integer")) {
                method.invoke(instance, new Integer(value));
            } else if (fieldType.endsWith("double")) {
                method.invoke(instance, Double.parseDouble(value));
            } else if (fieldType.endsWith("Double")) {
                method.invoke(instance, new Double(value));
            } else if (fieldType.endsWith("Date")) {
                method.invoke(instance, DateUtil.stringToDateTime(value));
            } else if (fieldType.endsWith("Boolean")) {
                method.invoke(instance, (value.toUpperCase().equals("TRUE") || value.toUpperCase().equals("1")) ?
                        new Boolean(true) :
                        new Boolean(false));
            } else if (fieldType.endsWith("boolean")) {
                method.invoke(instance, (value.toUpperCase().equals("TRUE") || value.toUpperCase().equals("1")) ?
                        true : false);
            } else if (fieldType.endsWith("List")) {
                // TODO: 2016/4/13
                Type fc = field.getGenericType();
                if (fc instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) fc;
                    Class parameterClazz = (Class) pt.getActualTypeArguments()[0]; //
                }
            }
        }
        return instance;
    }
}
