package com.zoe.phip.module.service.util;

import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlUtil;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
/**
 * Created by zengjiyang on 2016/4/13.
 */
public class ProcessXmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(ProcessXmlUtil.class);

    private static String schemasPath;

    public void setSchemasPath(String schemas) {
        schemasPath = schemas;
    }


    /**
     * 删除值为空的属性
     *
     * @param node
     */
    public static void deleteNullAttributes(Element node) {
        //对于叶子节点（无子节点的节点），如果含有属性但是属性值为空，则删除该属性
        if (node.elements().size() == 0 && node.attributes().size() > 0) {
            int length = node.attributes().size();
            for (int i = 0; i < length; i++) {
                if (StringUtil.isNullOrWhiteSpace(node.attributes().get(i).getValue())) {
                    node.remove(node.attributes().get(i));
                    length--;
                }
            }
        } else {
            for (Element childNode : node.elements()) {
                //递归
                deleteNullAttributes(childNode);
            }
        }
    }

    /**
     * 验证xml格式是否正确
     *
     * @param strXml
     * @return
     */
    public static String verifyMessage(String strXml) {
        Document xd;
        //校验字符串是否符合xml格式
        try {
            xd = DocumentHelper.parseText(strXml);
        } catch (DocumentException ex) {
            logger.error("error:", ex);
            return "error:传入的参数不符合xml格式。" + ex.getMessage();
        }
        String rootName = xd.getRootElement().getName();
        String xsdPath = "multicacheschemas/" + rootName + ".xsd";
        String xsdFilePath = ProcessXmlUtil.class.getClassLoader().getResource(xsdPath).getPath();
        if(xsdFilePath.contains("jar")){
            xsdFilePath=schemasPath + rootName + ".xsd";
        }
        String result = "success:数据集内容验证正确";
        String strMessage = XmlUtil.validateXsd(xsdFilePath, strXml);
        if (!strMessage.equals("")) {
            result = "error:数据集内容验证错误(" + strMessage + ")";
        }

        return result;
    }

    public static Document load(String xmlString) {
        xmlString = XmlUtil.removeNameSpace(xmlString);
        if (!xmlString.startsWith("<")) {
            xmlString = xmlString.substring(0, 1);
        }
        try {
            return DocumentHelper.parseText(xmlString);
        } catch (DocumentException ex) {
            return null;
        }
    }

    public static Document getAdapterDom(String path){
        try{
            //todo 先暂时用file的方式获取
            SAXReader reader = new SAXReader();
            Document parserDoc;
            InputStream adapterStream=ProcessXmlUtil.class.getResourceAsStream(path);
            if(adapterStream==null){
                parserDoc = reader.read(new File("."+path));
            }else {
                parserDoc = reader.read(adapterStream);
            }
            return parserDoc;
        }catch (DocumentException e){
            logger.error("errors",e);
            return null;
        }
    }

}
