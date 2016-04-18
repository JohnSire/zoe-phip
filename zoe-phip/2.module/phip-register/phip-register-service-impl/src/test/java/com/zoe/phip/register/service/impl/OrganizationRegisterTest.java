package com.zoe.phip.register.service.impl;

import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.infrastructure.util.XmlUtil;
import com.zoe.phip.register.model.OrgDeptInfo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * Created by huangyinfu on 2016/4/18.
 */
public class OrganizationRegisterTest {
    @Test
    public void testAdd() throws Exception {
        String patientInput ="<PRPM_IN401030UV01 xmlns=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ITSVersion=\"XML_1.0\" xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/PRPM_IN401030UV01.xsd\">\n" +
                "  <id root=\"040CD76A-ED0E-400B-9FD3-60387BCDE0EB\" extension=\"8D73520B-D489-4B70-8F4B-7B5C2D7961B5\"/>\n" +
                "  <creationTime value=\"20130116112855\"/>\n" +
                "  <interactionId root=\"2.16.840.1.113883.1.6\" extension=\"PRPM_IN401030UV01\"/>\n" +
                "  <processingCode code=\"P\"/>\n" +
                "  <processingModeCode code=\"I\"/>\n" +
                "  <acceptAckCode code=\"AL\"/>\n" +
                "  <receiver typeCode=\"RCV\">\n" +
                "    <device classCode=\"DEV\" determinerCode=\"INSTANCE\">\n" +
                "      <id root=\"1.2.840.114350.1.13.999.567\"/>\n" +
                "    </device>\n" +
                "  </receiver>\n" +
                "  <sender typeCode=\"SND\">\n" +
                "    <device classCode=\"DEV\" determinerCode=\"INSTANCE\">\n" +
                "      <id root=\"1.2.840.114350.1.13.999.234\"/>\n" +
                "    </device>\n" +
                "  </sender>\n" +
                "  <controlActProcess classCode=\"CACT\" moodCode=\"EVN\">\n" +
                "    <code code=\"PRPM_TE401010UV01\" codeSystem=\"2.16.840.1.113883.1.6\"/>\n" +
                "    <subject typeCode=\"SUBJ\">\n" +
                "      <registrationRequest classCode=\"REG\" moodCode=\"RQO\">\n" +
                "        <statusCode code=\"active\"/>\n" +
                "        <subject1 typeCode=\"SBJ\">\n" +
                "          <assignedEntity classCode=\"ASSIGNED \">\n" +
                "            <!--医疗卫生机构（科室）标识-科室为例-->\n" +
                "            <id root=\"2.16.156.10011.1.26\" extension=\"1234567890\"/>\n" +
                "            <!--医疗卫生机构（科室）类别-科室为例-->\n" +
                "            <code code=\"A03.01\" displayName=\" 呼吸内科专业\"\n" +
                "            codeSystem=\"2.16.156.10011.2.3.2.62\" codeSystemName=\"医疗卫生机构业务科室分类与代码表\"/>\n" +
                "            <!--医疗卫生机构（科室）角色名称-->\n" +
                "            <name>管理</name>\n" +
                "            <!--工作地址-->\n" +
                "            <addr>厦门大学附属第一医院</addr>\n" +
                "            <!--工作联系方式：电话、邮箱地址等-->\n" +
                "            <telecom></telecom>\n" +
                "            <!--角色状态RoleStatus-->\n" +
                "            <statusCode code=\"active\"/>\n" +
                "            <!--角色有效期间-->\n" +
                "            <effectiveTime>\n" +
                "              <low value=\"20100101\"/>\n" +
                "              <high value=\"20501231\"/>\n" +
                "            </effectiveTime>\n" +
                "            <assignedPrincipalOrganization classCode=\"ORG\"\n" +
                "            determinerCode=\"INSTANCE\">\n" +
                "              <!--医疗卫生机构（科室）实体名称-->\n" +
                "              <name>呼吸内科</name>\n" +
                "              <asAffiliate classCode=\"AFFL\">\n" +
                "                <code/>\n" +
                "                <effectiveTime/>\n" +
                "                <!--上级机构-->\n" +
                "                <scoper2 classCode=\"ORG\" determinerCode=\"INSTANCE\">\n" +
                "                  <!--上级医疗卫生机构（科室）号标识-->\n" +
                "                  <id root=\"2.16.156.10011.1.26\" extension=\"0234567890\"/>\n" +
                "                  <name>内科x</name>\n" +
                "                </scoper2>\n" +
                "              </asAffiliate>\n" +
                "            </assignedPrincipalOrganization>\n" +
                "          </assignedEntity>\n" +
                "        </subject1>\n" +
                "        <author typeCode=\"AUT\">\n" +
                "          <assignedEntity classCode=\"ASSIGNED\">\n" +
                "            <!--医务人员ID-->\n" +
                "            <id root=\"2.16.156.10011.1.4\" extension=\"120109197706015518\"/>\n" +
                "            <assignedPerson classCode=\"PSN\" determinerCode=\"INSTANCE\">\n" +
                "              <name>李人事</name>\n" +
                "            </assignedPerson>\n" +
                "            <representedOrganization classCode=\"ORG\"\n" +
                "            determinerCode=\"INSTANCE\">\n" +
                "              <!--科室号标识-->\n" +
                "              <id root=\"2.16.156.10011.1.26\" extension=\"xxx12345-X\"/>\n" +
                "              <name>人事科</name>\n" +
                "              <contactParty classCode=\"CON\">\n" +
                "                <contactPerson classCode=\"PSN\"\n" +
                "                determinerCode=\"INSTANCE\">\n" +
                "                  <name>王联系</name>\n" +
                "                </contactPerson>\n" +
                "              </contactParty>\n" +
                "            </representedOrganization>\n" +
                "          </assignedEntity>\n" +
                "        </author>\n" +
                "      </registrationRequest>\n" +
                "    </subject>\n" +
                "  </controlActProcess>\n" +
                "</PRPM_IN401030UV01>";


        SAXReader reader = new SAXReader();
        String filePath = "/template/Patient/In/Adapter/OrganizationRegisterAdapter.xml";
        Document document = null;
        try {
            document = reader.read(this.getClass().getResourceAsStream(filePath));
        } catch (DocumentException e) {

        }
        patientInput = XmlUtil.removeNameSpace(patientInput);
        Document doc = DocumentHelper.parseText(patientInput);
        OrgDeptInfo info = XmlBeanUtil.toBean(doc, OrgDeptInfo.class, document);

    }
}
