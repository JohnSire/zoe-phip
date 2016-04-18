package com.zoe.phip.register.util;

import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlUtil;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/13.
 */
public class ProcessXmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(ProcessXmlUtil.class);

    private static final String nameSpace = "urn:hl7-org:v3";

    /**
     * 拼装响应消息XML
     *
     * @param xmlDoc           请求XML
     * @param strRootModelCode 请求消息模型代码
     * @param strMsgModelCode  响应消息模型代码
     * @param strTypeCode      处理结果，AA 表示成功 AE 表示失败
     * @param strResultShow    处理结果说明
     * @param strMsgId         请求的消息ID
     * @param strIdRoot        请求的消息ID Root属性值
     * @return
     */
    public static String mixResponseXml(Document xmlDoc, String strRootModelCode, String strMsgModelCode,
                                        String strTypeCode, String strResultShow, String strMsgId, String strIdRoot) {

        Document newXmlDoc = DocumentHelper.createDocument();
        //头部构建
        Element rootNode = newXmlDoc.addElement(strMsgModelCode);
        newXmlDoc.setRootElement(rootNode);
        String xsi = "http://www.w3.org/2001/XMLSchema-instance";
        String xsi_schemaLocation = MessageFormat.format("urn:hl7-org:v3 ..\\multicacheschemas\\{0}.xsd", strMsgModelCode);
        //添加命名空间
        rootNode.addNamespace("", nameSpace);
        rootNode.addAttribute("xmlns:xsi", xsi);
        rootNode.addAttribute("xsi:schemaLocation", xsi_schemaLocation);
        if (!strMsgModelCode.equals("RegistryResponse") && !strMsgModelCode.equals("PRPA_IN201306UV02")) {
            rootNode.addAttribute("status", strTypeCode);
        }
        if (strMsgModelCode.equals("PRPA_IN201306UV02")) {
            rootNode.addAttribute("ITSVersion", "XML_1.0");
        }

        //在新的XML根节点下加入原来XML除了根节点外的所有节点
        Element ele = (Element) xmlDoc.selectSingleNode("/" + strRootModelCode);
        ele.elements().forEach(e -> {
            rootNode.add((Node) e.clone());
        });
        Map map = new HashMap();
        map.put("ns", nameSpace);
        XPath x = newXmlDoc.createXPath("//ns:interactionId");

        x.setNamespaceURIs(map);
        ((Element) x.selectSingleNode(rootNode)).attribute("extension").setValue(strMsgModelCode);

        if (!StringUtil.isNullOrWhiteSpace(strResultShow)) {
            //在节点sender之后插入响应消息结果xml文档
            Element responseEle = responseMsgXml(strTypeCode, strResultShow, strMsgId, strIdRoot);
            if (responseEle != null) {
                List<Element> elements = rootNode.elements();
                int index = 0;
                for (Element element : elements) {
                    index++;
                    if ("sender".equals(element.getName())) {
                        elements.add(index, responseEle);
                        break;
                    }
                }
            }
        }
        deleteNullAttributes(newXmlDoc.getRootElement());
        return newXmlDoc.getRootElement().asXML();
    }

    /**
     * 拼装响应消息XML--健康档案
     *
     * @param xmlDoc           请求XML
     * @param strRootModelCode 请求消息模型代码
     * @param strMsgModelCode  响应消息模型代码
     * @param strTypeCode      处理结果，AA 表示成功 AE 表示失败
     * @return
     */
    public static String mixResponseXml(Document xmlDoc, String strRootModelCode, String strMsgModelCode, String strTypeCode) {
        return mixResponseXml(xmlDoc, strRootModelCode, strMsgModelCode, strTypeCode, null, null, null);
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
     * 响应消息结果赋值
     *
     * @param strTypeCode   处理结果，AA 表示成功 AE 表示失败
     * @param strResultShow 处理结果说明
     * @param strMsgID      请求的消息ID
     * @param strIdRoot
     * @return
     */
    public static Element responseMsgXml(String strTypeCode, String strResultShow,
                                         String strMsgID, String strIdRoot) {
        Document xmlResponseMsg = loadXmlFile("响应消息结果");
        if (xmlResponseMsg == null)
            return null;
        ((Element) xmlResponseMsg.selectSingleNode("/acknowledgement")).attribute("typeCode").setValue(strTypeCode);
        xmlResponseMsg.selectSingleNode("/acknowledgement/acknowledgementDetail/text").setText(strResultShow);
        ((Element) xmlResponseMsg.selectSingleNode("/acknowledgement/targetMessage/id")).attribute("extension").setValue(strMsgID);
        ((Element) xmlResponseMsg.selectSingleNode("/acknowledgement/targetMessage/id")).attribute("root").setValue(strIdRoot);
        return xmlResponseMsg.getRootElement();
    }

    /**
     * 加载XML模板文件
     *
     * @param fileName XML文件名称
     * @return
     */
    public static Document loadXmlFile(String fileName) {
        SAXReader reader = new SAXReader();
        String filePath = "/xmlfile/" + fileName + ".xml";
        Document document = null;
        try {
            document = reader.read(ProcessXmlUtil.class.getResourceAsStream(filePath));
        } catch (DocumentException e) {
            logger.error("error:", e);
        }
        return document;
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
        String xsdPath = "/multicacheschemas/" + rootName + ".xsd";
//        String NamespaceUrl = "urn:hl7-org:v3";

        //TODO xsdPath的路径
        String result = "success:数据集内容验证正确";
        String strMessage = XmlUtil.validateXsd(xsdPath, strXml);
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
}
