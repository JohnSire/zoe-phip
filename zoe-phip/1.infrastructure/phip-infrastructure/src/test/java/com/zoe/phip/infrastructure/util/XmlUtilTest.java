package com.zoe.phip.infrastructure.util;

import org.junit.Test;
/**
 * Created by zhangwenbin on 2016-05-19.
 */
public class XmlUtilTest {
    @Test
    public void getHtmlString() throws Exception{
        String xmlString ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<root version=\"1.0.0.7\">\n" +
                "  <item date=\"2007-7-18 10:28:56\" code=\"011010000103\" spec=\"\" unit=\"次\" price=\"1\" quantity=\"1\" total=\"1\" notes=\"\">挂号费（住院、主治）</item>\n" +
                "  <item date=\"2007-7-18 10:28:56\" code=\"011020000100\" spec=\"\" unit=\"次\" price=\"5\" quantity=\"1\" total=\"5\" notes=\"\">普通门诊诊查费—三级</item>\n" +
                "  <item date=\"2007-7-18 10:57:36\" code=\"033010000100\" spec=\"\" unit=\"次\" price=\"40\" quantity=\"1\" total=\"40\" notes=\"\">局部浸润麻醉</item>\n" +
                "  <item date=\"2007-7-18 10:57:36\" code=\"033070100101\" spec=\"\" unit=\"次\" price=\"80\" quantity=\"1\" total=\"80\" notes=\"\">经直达喉镜喉肿物摘除术</item>\n" +
                "  <item date=\"2007-7-18 13:59:08\" code=\"23000052073634410101\" spec=\"3g*6包\" unit=\"包\" price=\"3.1933\" quantity=\"12\" total=\"38.32\" notes=\"\">*十味龙胆花颗粒</item>\n" +
                "</root>";
        String htmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:hl7=\"urn:hl7-org:v3\" xmlns:lab=\"urn:oid:1.3.6.1.4.1.19376.1.3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><xsl:template match=\"/\"><xsl:choose><xsl:when test=\"ClinicalDocument/version[@code='2.0.0.0' or @code='2.0.0.1']\"><html><head><title></title><style>\n" +
                "              .border_td {\n" +
                "              BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid; BACKGROUND-COLOR: #ffffff\n" +
                "              }\n" +
                "              .no_border_td {\n" +
                "              BACKGROUND-COLOR: #ffffff\n" +
                "              }\n" +
                "              .border_table {\n" +
                "              BORDER-RIGHT: medium none; BORDER-TOP: medium none; BORDER-LEFT: medium none; BORDER-BOTTOM: medium none; BORDER-COLLAPSE: collapse;\n" +
                "              }\n" +
                "              .myhr {\n" +
                "              margin-top: 7px;\n" +
                "              margin-bottom: 7px;\n" +
                "              }\n" +
                "              hr {\n" +
                "              margin-top: 15px;\n" +
                "              color: #666;\n" +
                "              margin-bottom: -1px;\n" +
                "              }\n" +
                "              .no_border_table {\n" +
                "              BACKGROUND-COLOR: darkgray\n" +
                "              }\n" +
                "              .MainFx {\n" +
                "              text-align: center;\n" +
                "              width: 700px;\n" +
                "              margin-right: auto;\n" +
                "              margin-left: auto;\n" +
                "              }\n" +
                "              .ContentLeft {\n" +
                "              text-align: left;\n" +
                "              padding-left: 5px;\n" +
                "              }\n" +
                "            </style><style id=\"Revision\">\n" +
                "              .INSERT1 {\n" +
                "              COLOR: #0099ff; TEXT-DECORATION: underline\n" +
                "              }\n" +
                "              .DELETE1 {\n" +
                "              COLOR: #ff00cc; TEXT-DECORATION: line-through\n" +
                "              }\n" +
                "              .INSERT2 {\n" +
                "              COLOR: #0000ff; TEXT-DECORATION: underline\n" +
                "              }\n" +
                "              .DELETE2 {\n" +
                "              COLOR: #ff0000; TEXT-DECORATION: line-through\n" +
                "              }\n" +
                "              .INSERT3 {\n" +
                "              COLOR: #000099; TEXT-DECORATION: underline\n" +
                "              }\n" +
                "              .DELETE3 {\n" +
                "              COLOR: #aa0000; TEXT-DECORATION: line-through\n" +
                "              }\n" +
                "              .InsertCtrl {\n" +
                "              BORDER-RIGHT: #0000ff 1px solid; BORDER-TOP: #0000ff 1px solid; BORDER-LEFT: #0000ff 1px solid; BORDER-BOTTOM: #0000ff 1px solid\n" +
                "              }\n" +
                "              .DeleteCtrl {\n" +
                "              BORDER-RIGHT: #ff0000 1px solid; BORDER-TOP: #ff0000 1px solid; BORDER-LEFT: #ff0000 1px solid; BORDER-BOTTOM: #ff0000 1px solid\n" +
                "              }\n" +
                "            </style><style type=\"text/css\">\n" +
                "              .UnderLine {\n" +
                "              border-bottom:#000000 1px solid;\n" +
                "              }\n" +
                "              .UnderLineX {\n" +
                "              border-bottom:#CDCDCD 1px solid;\n" +
                "              }\n" +
                "              .UnderLine2 {\n" +
                "              border-bottom:#000000 2px solid;\n" +
                "              }\n" +
                "              .LeftLine {\n" +
                "              border-left:#000000 1px solid;\n" +
                "              }\n" +
                "              .RightLine {\n" +
                "              border-right:#000000 1px solid;\n" +
                "              }\n" +
                "              .TopLine {\n" +
                "              border-top:#000000 1px solid;\n" +
                "              }\n" +
                "              .TopLine2 {\n" +
                "              border-top:#000000 2px solid;\n" +
                "              }\n" +
                "              .R_B_Line {\n" +
                "              border-right:#000000 1px solid;\n" +
                "              border-bottom:#000000 1px solid;\n" +
                "              }\n" +
                "              .R_B_Line2 {\n" +
                "              border-right:#000000 1px solid;\n" +
                "              border-bottom:#000000 2px solid;\n" +
                "              }\n" +
                "              .L_R_B_Line {\n" +
                "              border-left:#000000 1px solid;\n" +
                "              border-right:#000000 1px solid;\n" +
                "              border-bottom:#000000 1px solid;\n" +
                "              }\n" +
                "              .B_Line {  font-size: 14px;\n" +
                "              }\n" +
                "              .T_L_R_B_Line {\n" +
                "              border-top:#000000 1px solid;\n" +
                "              border-left:#000000 1px solid;\n" +
                "              border-right:#000000 1px solid;\n" +
                "              border-bottom:#000000 1px solid;\n" +
                "              }\n" +
                "              .T_B_Line {\n" +
                "              border-top:#000000 1px solid;\n" +
                "              border-bottom:#000000 1px solid;\n" +
                "              }\n" +
                "              .T_L_R_Line {\n" +
                "              border-top:#000000 1px solid;\n" +
                "              border-left:#000000 1px solid;\n" +
                "              border-right:#000000 1px solid;\n" +
                "              }\n" +
                "              .T_R_B_Line {\n" +
                "              border-top:#000000 1px solid;\n" +
                "              border-right:#000000 1px solid;\n" +
                "              border-bottom:#000000 1px solid;\n" +
                "              }\n" +
                "              .T_R_Line {\n" +
                "              border-top:#000000 1px solid;\n" +
                "              border-right:#000000 1px solid;\n" +
                "              }\n" +
                "              .cBig {\n" +
                "              font-size: 18px; FONT-FAMILY: \"宋体\";\n" +
                "              }\n" +
                "              .cTitle {\n" +
                "              font-size: 27px;\n" +
                "              font-weight: bold;\n" +
                "              letter-spacing: 8px;\n" +
                "              margin-bottom: 20px;\n" +
                "              margin-top: 10px;\n" +
                "              font-family: \"黑体\";\n" +
                "              }\n" +
                "              .cHspl {\n" +
                "              font-family: \"楷体\";\n" +
                "              font-size: 18px;\n" +
                "              }\n" +
                "              body,td,th {\n" +
                "              font-size: 12px;\n" +
                "              font-family: 宋体;\n" +
                "              }\n" +
                "              td {\n" +
                "              height: 20px;\n" +
                "              }\n" +
                "              body{text-align:center;}\n" +
                "              .BottomFx {\n" +
                "              border-top-width: 2px;\n" +
                "              border-top-style: solid;\n" +
                "              border-top-color: #000000;\n" +
                "              margin-top: 40px;\n" +
                "              }\n" +
                "              .TopFx {\n" +
                "              border-top-width: 2px;\n" +
                "              border-top-style: solid;\n" +
                "              border-top-color: #000000;\n" +
                "              margin-bottom: 20px;\n" +
                "              }\n" +
                "              .myline {\n" +
                "              border-top-width: 2px;\n" +
                "              border-top-style: solid;\n" +
                "              border-top-color: #000000;\n" +
                "              margin-bottom: 20px;\n" +
                "              width: 650px;\n" +
                "              }\n" +
                "            </style></head><body><xsl:for-each select=\"/ClinicalDocument\"><div class=\"MainFx\"><div class=\"cTitle\"><xsl:value-of select=\"title\"/><xsl:text></xsl:text></div><div class=\"TopFx\"></div><table cellspacing=\"0\" cellpadding=\"0\" width=\"650\" align=\"center\" border=\"0\"><tbody><h1></h1><tr><td width=\"35\"><xsl:text>姓名:</xsl:text></td><td class=\"UnderLine ContentLeft\" width=\"130\"><xsl:value-of select=\"recordTarget/patient/name\"/><xsl:text></xsl:text></td><td width=\"90\"><xsl:text>市民健康卡号:</xsl:text></td><td class=\"UnderLine  ContentLeft\" width=\"170\"><xsl:value-of select=\"recordTarget/patient/id/@extension\"/><xsl:text>　</xsl:text></td><td width=\"225\"></td></tr></tbody></table><div class=\"myhr\"></div><table cellspacing=\"0\" cellpadding=\"0\" width=\"650\" align=\"center\" border=\"0\"><tbody><tr><td width=\"90\"><xsl:text>医疗机构名称:</xsl:text></td><td class=\"UnderLine ContentLeft\" width=\"170\"><xsl:value-of select=\"org\"/><xsl:text>　</xsl:text></td><td width=\"390\"></td></tr></tbody></table><!--<div class=\"myhr\">\n" +
                "            </div>\n" +
                "            <div class=\"myline\">\n" +
                "            </div>--><div class=\"myhr\"></div><table cellspacing=\"0\" cellpadding=\"0\" width=\"650\" align=\"center\" border=\"0\"><tbody><tr><td width=\"90\"><xsl:text>门诊费用明细:</xsl:text></td><td width=\"560\" class=\"ContentLeft\"></td></tr></tbody></table><div class=\"myhr\"></div><xsl:for-each select=\"component/section/entry/group\"><xsl:sort select=\"type\" order=\"ascending\"/><!--排序--><div class=\"myhr\"></div><div class=\"myline\"></div><div class=\"myhr\"></div><table cellspacing=\"0\" cellpadding=\"0\" width=\"650\" align=\"center\" border=\"0\"><tbody><tr><td width=\"60\"><xsl:text>费用类别:</xsl:text></td><td width=\"155\" class=\"UnderLine ContentLeft\"><xsl:value-of select=\"type\"/><xsl:text>　</xsl:text></td><td width=\"60\"><xsl:text>费用金额:</xsl:text></td><td width=\"155\" class=\"UnderLine ContentLeft\"><xsl:value-of select=\"fee\"/><xsl:text>　</xsl:text></td><td width=\"60\"><xsl:text>支付方式:</xsl:text></td><td width=\"160\" class=\"UnderLine ContentLeft\"><xsl:value-of select=\"payway\"/><xsl:text>　</xsl:text></td></tr></tbody></table><div class=\"myhr\"></div><table cellspacing=\"0\" cellpadding=\"0\" width=\"650\" align=\"center\" border=\"0\"><tbody><tr><td class=\"T_R_Line \" align=\"middle\" width=\"180\"><xsl:text>项目名称</xsl:text></td><td class=\"T_R_Line\" align=\"middle\" width=\"50\"><xsl:text>数量</xsl:text></td><td class=\"T_R_Line\" align=\"middle\" width=\"50\"><xsl:text>单位</xsl:text></td><td class=\"T_R_Line\" align=\"middle\" width=\"50\"><xsl:text>单价</xsl:text></td><td class=\"T_R_Line\" align=\"middle\" width=\"100\"><xsl:text>总价</xsl:text></td><td class=\"T_R_Line\" align=\"middle\" width=\"120\"><xsl:text>扣费日期</xsl:text></td><td class=\"TopLine\" align=\"middle\" width=\"100\"><xsl:text>备注</xsl:text></td></tr><xsl:for-each select=\"item\"><xsl:sort select=\"custome\" order=\"ascending\"/><!--排序--><TR><TD class=\"T_R_Line \" align=\"middle\" width=\"180\"><xsl:value-of select=\"custome\"/><xsl:text>　</xsl:text></TD><TD class=\"T_R_Line \" align=\"middle\" width=\"50\"><xsl:value-of select=\"quantity\"/><xsl:text>　</xsl:text></TD><TD class=\"T_R_Line \" align=\"middle\" width=\"50\"><xsl:value-of select=\"unit\"/><xsl:text>　</xsl:text></TD><TD class=\"T_R_Line \" align=\"middle\" width=\"50\"><xsl:value-of select=\"price\"/><xsl:text>　</xsl:text></TD><TD class=\"T_R_Line \" align=\"middle\" width=\"100\"><xsl:value-of select=\"total\"/><xsl:text>　</xsl:text></TD><TD class=\"T_R_Line \" align=\"middle\" width=\"120\"><xsl:value-of select=\"time\"/><xsl:text>　</xsl:text></TD><TD class=\"TopLine \" align=\"middle\" width=\"100\"><xsl:value-of select=\"notes\"/><xsl:text>　</xsl:text></TD></TR></xsl:for-each><tr><td class=\"T_R_B_Line \" align=\"middle\" width=\"180\"><xsl:text>项目名称</xsl:text></td><td class=\"T_R_B_Line\" align=\"middle\" width=\"50\"><xsl:text>数量</xsl:text></td><td class=\"T_R_B_Line\" align=\"middle\" width=\"50\"><xsl:text>单位</xsl:text></td><td class=\"T_R_B_Line\" align=\"middle\" width=\"50\"><xsl:text>单价</xsl:text></td><td class=\"T_R_B_Line\" align=\"middle\" width=\"100\"><xsl:text>总价</xsl:text></td><td class=\"T_R_B_Line\" align=\"middle\" width=\"120\"><xsl:text>扣费日期</xsl:text></td><td class=\"T_B_Line\" align=\"middle\" width=\"100\"><xsl:text>备注</xsl:text></td></tr></tbody></table></xsl:for-each><hr/><div class=\"myhr\"></div><table cellspacing=\"0\" cellpadding=\"0\" width=\"650\" align=\"center\" border=\"0\"><tbody><tr><td width=\"175\"></td><td width=\"270\" class=\"ContentLeft\"></td><td width=\"90\"><span><xsl:text>文档创建时间:</xsl:text></span></td><td class=\"UnderLine ContentLeft\" width=\"120\"><xsl:value-of select=\"effectiveTime\"/><xsl:text>　</xsl:text></td></tr></tbody></table></div></xsl:for-each></body></html></xsl:when><xsl:otherwise><html><head><style type=\"text/css\">\n" +
                "              .style2 {font-size: 14pt}\n" +
                "              .style4 {\n" +
                "              font-size: 16pt;\n" +
                "              font-weight: bold;\n" +
                "              }\n" +
                "              .style11 {\n" +
                "              font-size: 9pt;\n" +
                "              margin:2px\n" +
                "              }\n" +
                "            </style></head><body><xsl:for-each select=\"/root\"><div align=\"center\" style=\"width:720px;height:30px;\"><span class=\"style4\">门 诊 费 用 明 细</span><br/></div><table style=\"width:720px;\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#000000\"><xsl:for-each select=\"item\"><tr bgcolor=\"#FFFFFF\" class=\"style11\"><td style=\"width:150px;\" rowspan=\"3\"><xsl:value-of select=\"@date\"/></td><td style=\"width:60px;\"><span class=\"style11\">医保代码</span></td><td style=\"width:130px;\"><xsl:value-of select=\"@code\"/></td><td style=\"width:60px;\"><span class=\"style11\">项目名称</span></td><td style=\"width:130px;\"><xsl:value-of select=\".\"/></td><td style=\"width:60px;\"><span class=\"style11\">规格</span></td><td style=\"width:130px;\"><xsl:value-of select=\"@spec\"/></td></tr><tr bgcolor=\"#FFFFFF\" class=\"style11\"><td><span class=\"style11\">单位</span></td><td><xsl:value-of select=\"@unit\"/></td><td><span class=\"style11\">数量</span></td><td><xsl:value-of select=\"@quantity\"/></td><td><span class=\"style11\">单价</span></td><td><xsl:value-of select=\"@price\"/></td></tr><tr bgcolor=\"#FFFFFF\" class=\"style11\"><td><span class=\"style11\">总费用</span></td><td><xsl:value-of select=\"@total\"/></td><td><span class=\"style11\">备注</span></td><td colspan=\"3\"><xsl:value-of select=\"@notes\"/></td></tr></xsl:for-each></table></xsl:for-each></body></html></xsl:otherwise></xsl:choose></xsl:template></xsl:stylesheet>";
        String outputString = XmlUtil.getHtmlString(xmlString,htmlString);
        System.out.println(outputString);
    }
}