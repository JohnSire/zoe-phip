package com.zoe.phip.register.util;

import com.zoe.phip.infrastructure.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/13.
 */
public class ProcessXmlUtilTest {

    @Test
    public void testLoadXmlFile() throws Exception {
        ProcessXmlUtil.loadXmlFile("响应消息结果");
    }

    @Test
    public void testMixResponseXml() throws Exception {
        String xml="<PRPA_IN201312UV02 xmlns=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xsi:schemaLocation=\"urn:hl7-org:v3 ..\\multicacheschemas\\PRPA_IN201312UV02.xsd\" " +
                "ITSVersion=\"XML_1.0\">\n" +
                "  <id root=\"2.16.156.10011.0\" extension=\"22a0f9e0-4454-11dc-a6be-3603d6866807\" />\n" +
//                "  <creationTime value=\"20070803010624\" />\n" +
                "  <interactionId root=\"2.16.840.1.113883.1.6\" extension=\"PRPA_IN201312UV02\" />\n" +
                "  <processingCode code=\"P\" />\n" +
                "  <processingModeCode code=\"R\" />\n" +
                "  <acceptAckCode code=\"NE\" />\n" +
                "  <receiver typeCode=\"RCV\">\n" +
                "    <device classCode=\"DEV\" determinerCode=\"INSTANCE\">\n" +
                "      <id root=\"2.16.156.10011.0.1.2\" extension=\"2.16.156.10011.0.1.2\" />\n" +
                "    </device>\n" +
                "  </receiver>\n" +
                "  <sender typeCode=\"SND\">\n" +
                "    <device classCode=\"DEV\" determinerCode=\"INSTANCE\">\n" +
                "      <id root=\"2.16.156.10011.0.1.1\" extension=\"2.16.156.10011.0.1.1\" />\n" +
                "    </device>\n" +
                "  </sender>\n" +
                "  <controlActProcess classCode=\"CACT\" moodCode=\"EVN\">\n" +
                "    <subject typeCode=\"SUBJ\">\n" +
                "      <registrationEvent classCode=\"REG\" moodCode=\"EVN\">\n" +
                "        <statusCode code=\"active\" />\n" +
                "        <subject1 typeCode=\"SBJ\">\n" +
                "          <patient classCode=\"PAT\">\n" +
                "            <!--平台注册的患者ID -->\n" +
                "            <id root=\"2.16.156.10011.0.2.1\" extension=\"患者ID\" />\n" +
                "            <statusCode code=\"active\" />\n" +
                "            <patientPerson classCode=\"PSN\" determinerCode=\"INSTANCE\">\n" +
                "              <!--姓名-->\n" +
                "              <name use=\"L\">刘永好</name>\n" +
                "            </patientPerson>\n" +
                "          </patient>\n" +
                "        </subject1>\n" +
                "        <custodian typeCode=\"CST\">\n" +
                "          <assignedEntity classCode=\"ASSIGNED\">\n" +
                "            <id root=\"2.16.156.10011.0.3.2\" extension=\"修改人ID\" />\n" +
                "            <assignedPerson classCode=\"PSN\" determinerCode=\"INSTANCE\">\n" +
                "              <name use=\"L\">赵武x</name>\n" +
                "            </assignedPerson>\n" +
                "          </assignedEntity>\n" +
                "        </custodian>\n" +
                "      </registrationEvent>\n" +
                "    </subject>\n" +
                "  </controlActProcess>\n" +
                "</PRPA_IN201312UV02>\n";
        Document document= DocumentHelper.parseText(xml);
        /*String result= ProcessXmlUtil.mixResponseXml(document,"PRPA_IN201312UV02","PRPA_IN201312UV02","AA","更新失败了啊","123456","987654");
        System.out.println(result);*/

//        ProcessXmlUtil.verifyMessage(xml);


        String result= XmlUtil.validateXsd("E:\\workplace\\ZOE.PHIP\\zoe-phip\\2.module\\phip-register\\phip-register-service-impl\\src\\main\\resources\\multicacheschemas"+"\\PRPA_IN201312UV02.xsd",xml);


    }

    public void testXsdValidate(){
    }
}