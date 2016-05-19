package com.zoe.phip.infrastructure.parser;

/**
 * Created by zhangwenbin on 2016-05-06.
 */

import java.math.BigDecimal;

import com.zoe.phip.infrastructure.parser.model.*;

import com.zoe.phip.infrastructure.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 预期值转换实现类
 */
public final class ExpectedValueParser {
    public static ExpectedValue parser(String xmlString) {
        ExpectedValue expectedValue = new ExpectedValue();
        try {
            Document document = DocumentHelper.parseText(xmlString);
            Element root = document.getRootElement();
            Element element = root.element("expected");
            if (element.elements().size() <= 0) return expectedValue;
            String nodeName = element.elements().get(0).getName();
            switch (nodeName.toLowerCase()) {
                case "interval":
                    for (Element ele : element.elements()) {
                        DqvInterval value = new DqvInterval();
                        value.setId(readAttribute(ele, "id"));
                        value.setFromValue(BigDecimal.valueOf(Integer.parseInt(readAttribute(ele, "from"))));
                        value.setToValue(BigDecimal.valueOf(Integer.parseInt(readAttribute(ele, "to"))));
                        expectedValue.getValues().add(value);
                    }
                    break;
                case "fixed":
                    for (Element ele : element.elements()) {
                        DqvFixed value = new DqvFixed();
                        value.setId(readAttribute(ele, "id"));
                        value.setFixedType(FixedType.String);
                        value.setValue(StringUtil.fromBase64String(readAttribute(ele, "value")));
                        expectedValue.getValues().add(value);
                    }
                    break;
                case "dictionary":
                    for (Element e : element.elements()) {
                        DqvDictionary value = new DqvDictionary();
                        value.setDqvType(DqvType.Dictionary);
                        value.setId(readAttribute(e, "id"));
                        value.setKey(readAttribute(e, "key"));
                        value.setCodeColumn(readAttribute(e, "code-col"));
                        value.setKeyColumn(readAttribute(e, "key-col"));
                        value.setTableName(readAttribute(e, "table"));
                        value.setDictId(e.attributeValue("dictId"));
                        expectedValue.getValues().add(value);
                    }
                    break;
                case "number":
                    for (Element e : element.elements()) {
                        DqvNumber value = new DqvNumber();
                        value.setId(readAttribute(e, "id"));
                        value.setFixedType(FixedType.Number);
                        value.setValue(StringUtil.fromBase64String(readAttribute(e, "value")));
                        expectedValue.getValues().add(value);
                    }
                    break;
                case "sql":
                    for (Element e : element.elements()) {
                        DqvSql value = new DqvSql();
                        value.setId(readAttribute(e, "id"));
                        value.setFixedType(FixedType.Sql);
                        value.setValue(StringUtil.fromBase64String(readAttribute(e, "value")));
                        expectedValue.getValues().add(value);
                    }
                    break;
                case "regex":
                    for (Element e : element.elements()) {
                        DqvRegex value = new DqvRegex();
                        value.setId(readAttribute(e, "id"));
                        value.setFixedType(FixedType.Regex);
                        value.setValue(StringUtil.fromBase64String(readAttribute(e, "value")));
                        expectedValue.getValues().add(value);
                    }
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return expectedValue;
    }

    protected static String readAttribute(Element element, String attribute) {
        return element.attributeValue(attribute).toString();
    }
}
