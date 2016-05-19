package com.zoe.phip.infrastructure.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.*;
import org.dom4j.util.XMLErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;

/**
 * Created by zhangwenbin on 2016/4/12.
 */
public final class XmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);


    /**
     * 验证XML是否正确格式
     *
     * @param xmlString xml格式的文本
     * @return
     */
    public static boolean isValid(String xmlString) throws Exception {

        if (!StringUtil.isNullOrWhiteSpace(xmlString)) {
            //XML转换时，如果转换前编码方法是UTF-8 或者 UniCode，则XML可能首位会出现异常字符
            //所以需要判断第一位字符是否为 “<”,如果不是 则需要去除
            if (!xmlString.startsWith("<")) {
                xmlString = xmlString.substring(0, 1);
            }
            DocumentHelper.parseText(xmlString);
            return true;
        }
        return false;
    }

    /**
     * 通过XSD验证xml的准确性
     *
     * @param xsdFileName
     * @param xmlString
     * @return
     */
    public static String validateXsd(String xsdFileName, String xmlString) {
        try {
            XMLErrorHandler handler = new XMLErrorHandler();
            //获取基于 SAX 的解析器的实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //解析器在解析时验证 XML 内容。
            factory.setValidating(true);
            //指定由此代码生成的解析器将提供对 XML 名称空间的支持。
            factory.setNamespaceAware(true);
            //使用当前配置的工厂参数创建 SAXParser 的一个新实例。
            SAXParser parser = factory.newSAXParser();
            //创建一个读取工具
            //获取要校验xml文档实例
            Document document = DocumentHelper.parseText(xmlString);
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "file:" + xsdFileName);
            //创建一个SAXValidator校验工具，并设置校验工具的属性
            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            //设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
            validator.setErrorHandler(handler);
            //校验
            validator.validate(document);

            XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
            if (handler.getErrors().hasContent()) {
                logger.error("XML文件通过XSD文件校验失败!");
                writer.write(handler.getErrors());
                StringBuffer buf = new StringBuffer();
                for (Node node : handler.getErrors().content()) {
                    buf.append(node.asXML());
                    buf.append(System.getProperty("line.separator"));
                }
                logger.error("错误:" + buf.toString());
                return buf.toString();
            } else {
                logger.info("XML文件通过XSD文件校验成功!");
            }
            return "";
        } catch (Exception ex) {
            logger.error("XML文件通过XSD文件:" + xsdFileName + "检验失败。\n原因： " + ex.getMessage());
            return ex.getMessage();
        }
    }

    /**
     * 去掉XML文件中的命名空间xmlns,防止查找元素出错
     *
     * @param xmlString
     * @return
     */
    public static String removeNameSpace(String xmlString) {
        String awsNamespace = "urn:hl7-org:v3";
        String xmlnsStr = " xmlns=\"" + awsNamespace + "\"";
        return xmlString.replace(xmlnsStr, "");
    }


    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append("jkjk");
            buffer.append("-9-");
        }
        String s = "错误的：+" + buffer.toString();
        System.out.println(s);
    }

    public static String getHtmlString(String xmlString, String xslString) throws Exception {
        SAXReader reader = new SAXReader();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
        Document document = reader.read(inputStream);
        Document transformerDoc = transformerDocument(document, xslString);
        return write2String(transformerDoc);
    }

    private static Document transformerDocument(Document document, String xslString) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Document transformerDoc = null;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xslString.getBytes());
        Transformer transformer = factory.newTransformer(new StreamSource(inputStream));
        DocumentSource docSource = new DocumentSource(document);
        DocumentResult docResult = new DocumentResult();
        transformer.transform(docSource, docResult);
        transformerDoc = docResult.getDocument();
        return transformerDoc;
    }

    private static String write2String(Document transformDoc) throws Exception {
        StringWriter writer = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        format.setXHTML(true);
        HTMLWriter htmlWriter = new HTMLWriter(writer, format);
        format.setExpandEmptyElements(false);
        htmlWriter.write(transformDoc);
        htmlWriter.flush();
        return writer.toString();
    }
}
