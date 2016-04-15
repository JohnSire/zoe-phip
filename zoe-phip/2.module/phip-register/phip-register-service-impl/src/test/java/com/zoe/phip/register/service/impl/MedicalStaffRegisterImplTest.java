package com.zoe.phip.register.service.impl;

import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.infrastructure.util.XmlUtil;
import com.zoe.phip.register.model.MedicalStaffInfo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Test;

/**
 * Created by zhanghao on 2016/4/15.
 */
public class MedicalStaffRegisterImplTest {


    @Test
    public void toBean() throws Exception {
        String xml = "<PRPM_IN301010UV01 xmlns=\"urn:hl7-org:v3\"\n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ITSVersion=\"XML_1.0\"\n" +
                "xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/PRPM_IN301010UV01.xsd\">\n" +
                "  <id root=\"040CD76A-ED0E-400B-9FD3-60387BCDE0EB\"\n" +
                "  extension=\"8D73520B-D489-4B70-8F4B-7B5C2D7961B5\"/>\n" +
                "  <creationTime value=\"20130116112855\"/>\n" +
                "  <interactionId root=\"2.16.840.1.113883.1.6\" extension=\"PRPM_IN301010UV01\"/>\n" +
                "  <processingCode code=\"P\"/>\n" +
                "  <processingModeCode code=\"I\"/>\n" +
                "  <acceptAckCode code=\"AL\"/>\n" +
                "  <receiver typeCode=\"RCV\">\n" +
                "    <telecom/>\n" +
                "    <device classCode=\"DEV\" determinerCode=\"INSTANCE\">\n" +
                "      <id root=\"1.2.840.114350.1.13.999.567\"/>\n" +
                "    </device>\n" +
                "  </receiver>\n" +
                "  <sender typeCode=\"SND\">\n" +
                "    <telecom/>\n" +
                "    <device classCode=\"DEV\" determinerCode=\"INSTANCE\">\n" +
                "      <id root=\"1.2.840.114350.1.13.999.234\"/>\n" +
                "    </device>\n" +
                "  </sender>\n" +
                "  <controlActProcess classCode=\"CACT\" moodCode=\"EVN\">\n" +
                "    <code code=\"PRPM_TE301010UV01\" codeSystem=\"2.16.840.1.113883.1.6\"/>\n" +
                "    <subject typeCode=\"SUBJ\">\n" +
                "      <registrationRequest classCode=\"REG\" moodCode=\"RQO\">\n" +
                "        <statusCode code=\"active\"/>\n" +
                "        <subject1 typeCode=\"SBJ\">\n" +
                "          <healthCareProvider classCode=\"PROV\">\n" +
                "            <!--医务人员ID-->\n" +
                "            <id root=\"2.16.156.10011.1.4\" extension=\"120109197706015518\"/>\n" +
                "            <!--专业技术职务代码-->\n" +
                "            <code code=\"231\" displayName=\" 主任医师\"\n" +
                "            codeSystem=\"2.16.156.10011.2.3.3.10\" codeSystemName=\"专业技术职务代码( GB/T 8561)\"/>\n" +
                "            <!--工作地址-->\n" +
                "            <addr/>\n" +
                "            <!--工作联系方式：电话、邮箱地址等-->\n" +
                "            <telecom/>\n" +
                "            <!--角色状态RoleStatus-->\n" +
                "            <statusCode code=\"active\"/>\n" +
                "            <!--角色有效期间-->\n" +
                "            <effectiveTime>\n" +
                "              <low value=\"20100101\"/>\n" +
                "              <high value=\"20501231\"/>\n" +
                "            </effectiveTime>\n" +
                "            <healthCarePrincipalPerson classCode=\"PSN\"\n" +
                "            determinerCode=\"INSTANCE\">\n" +
                "              <!--身份证号-->\n" +
                "              <id root=\"2.16.156.10011.1.3\" extension=\"120109197706015518\"/>\n" +
                "              <!--姓名-->\n" +
                "              <name use=\"L\">李医生</name>\n" +
                "              <!--性别-->\n" +
                "              <administrativeGenderCode code=\"1\"\n" +
                "              codeSystem=\"2.16.156.10011.2.3.3.4\" displayName=\"男性\" codeSystemName=\"生理性别代码表(GB/T 2261.1)\"/>\n" +
                "              <!--出生日期-->\n" +
                "              <birthTime value=\"19570323\"/>\n" +
                "              <!--隶属-->\n" +
                "              <asAffiliate classCode=\"AFFL\">\n" +
                "                <code/>\n" +
                "                <effectiveTime/>\n" +
                "                <affiliatedPrincipalOrganization classCode=\"ORG\"\n" +
                "                determinerCode=\"INSTANCE\">\n" +
                "                  <!--科室号标识-->\n" +
                "                  <id root=\"2.16.156.10011.1.26\" extension=\"xxx12345-X\"/>\n" +
                "                  <name>呼吸内科</name>\n" +
                "                </affiliatedPrincipalOrganization>\n" +
                "              </asAffiliate>\n" +
                "              <!--出生地-->\n" +
                "              <birthplace classCode=\"BIRTHPL\">\n" +
                "                <addr/>\n" +
                "              </birthplace>\n" +
                "            </healthCarePrincipalPerson>\n" +
                "          </healthCareProvider>\n" +
                "        </subject1>\n" +
                "        <!--申请者-->\n" +
                "        <author typeCode=\"AUT\">\n" +
                "          <assignedEntity classCode=\"ASSIGNED\">\n" +
                "            <!--医务人员ID-->\n" +
                "            <id root=\"2.16.156.10011.1.4\" extension=\"120109197706015518\"/>\n" +
                "            <assignedPerson classCode=\"PSN\" determinerCode=\"INSTANCE\">\n" +
                "              <name>李人事</name>\n" +
                "            </assignedPerson>\n" +
                "            <representedOrganization classCode=\"ORG\" determinerCode=\"INSTANCE\">\n" +
                "              <!--科室号标识-->\n" +
                "              <id root=\"2.16.156.10011.1.26\" extension=\"xxx12345-X\"/>\n" +
                "              <name>人事科</name>\n" +
                "              <contactParty classCode=\"CON\">\n" +
                "                <contactPerson classCode=\"PSN\" determinerCode=\"INSTANCE\">\n" +
                "                  <name>王联系</name>\n" +
                "                </contactPerson>\n" +
                "              </contactParty>\n" +
                "            </representedOrganization>\n" +
                "          </assignedEntity>\n" +
                "        </author>\n" +
                "      </registrationRequest>\n" +
                "    </subject>\n" +
                "  </controlActProcess>\n" +
                "</PRPM_IN301010UV01>";

        xml = XmlUtil.removeNameSpace(xml);
        Document document = DocumentHelper.parseText(xml);
        MedicalStaffInfo info = XmlBeanUtil.toBean(document, new MedicalStaffInfo());
        System.out.println();
    }
}
