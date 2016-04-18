/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.infrastructure.annotation.XPath;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.infrastructure.util.XmlUtil;
import com.zoe.phip.module.service.entity.MasterEntity;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import javax.persistence.*;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_ORG_DEPT_INFO")
public class OrgDeptInfo extends MasterEntity {
    /**
     * 医疗卫生机构（科室）标识
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/id/@extension", descr = "机构（科室）标识")
    @Column(name = "CODE")
    private String code;



    /**
     * 医疗卫生机构（科室）实体名称
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/assignedPrincipalOrganization/name", descr = "机构（科室）实体名称")
    @Column(name = "DEPT_NAME")
    private String deptName;
    /**
     * 医疗卫生机构（科室）类别代码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/code/@code", descr = "机构（科室）类别代码")
    @Column(name = "DEPT_TYPE_CODE")
    private String deptTypeCode;
    /**
     * 医疗卫生机构（科室）类别名称
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/code/@displayName", descr = "机构（科室）类别名称")
    @Column(name = "DEPT_TYPE_NAME")
    private String deptTypeName;
    /**
     * 医疗卫生机构（科室）角色名称
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/name", descr = "机构（科室）角色名称")
    @Column(name = "DEPT_ROLE_NAME")
    private String deptRoleName;
    /**
     * 上级机构（科室）号标识
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/assignedPrincipalOrganization/asAffiliate/scoper2/id/@extension", descr = "上级机构（科室）号标识")
    @Column(name = "DEPT_PARENT_CODE")
    private String deptParentCode;
    /**
     * 上级科室名称
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/assignedPrincipalOrganization/asAffiliate/scoper2/name", descr = "上级科室名称")
    @Column(name = "DEPT_PARENT_NAME")
    private String deptParentName;
    /**
     * 角色有效期间(起始日期)
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/effectiveTime/low/@value", descr = "起始日期")
    @Column(name = "EFFECTIVE_TIME_LOW")
    private Date effectiveTimeLow;
    /**
     * 角色有效期间(截止日期)
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/effectiveTime/high/@value", descr = "截止日期")
    @Column(name = "EFFECTIVE_TIME_HIGH")
    private Date effectiveTimeHigh;
    /**
     * 工作地址
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/addr", descr = "工作地址")
    @Column(name = "EMPLOYER_ADDR")
    private String employerAddr;
    /**
     * 工作联系电话
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/assignedEntity/telecom", descr = "工作联系电话")
    @Column(name = "EMPLOYER_TEL_NO")
    private String employerTelNo;

    /**
     * 创建时间
     */
    @XPath(value = "//creationTime/@value", descr = "创建时间")
    @Column(name = "CREATION_TIME")
    private Date creationTime;

    /**
     * 消息id
     */
    @XPath(value = "//id/@extension", descr = "消息id")
    @Column(name = "MSG_ID")
    private String msgId;
    /**
     * 申请者代码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/id/@extension", descr = "申请者代码")
    @Column(name = "ASSIGNED_CODE")
    private String assignedCode;
    /**
     * 申请者名称
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/assignedPerson/name", descr = "申请者名称")
    @Column(name = "ASSIGNED_NAME")
    private String assignedName;
    /**
     * 申请者科室代码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/representedOrganization/id/@extension", descr = "申请者科室代码")
    @Column(name = "ASSIGNED_DEPT_CODE")
    private String assignedDeptCode;
    /**
     * 申请者科室名称
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/representedOrganization/name", descr = "申请者科室名称")
    @Column(name = "ASSIGNED_DEPT_NAME")
    private String assignedDeptName;
    /**
     * 申请联系人
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/representedOrganization/contactParty/contactPerson/name", descr = "申请联系人")
    @Column(name = "ASSIGNED_ONTACT_PERSON")
    private String assignedOntactPerson;

    /**
     * 排序值  越小排在越前
     */
    ////
    // @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/providerOrganization/name", descr = "排序值")
    @Column(name = "SORT_NUM")
    private int sortNum;


    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptTypeCode() {
        return this.deptTypeCode;
    }

    public void setDeptTypeCode(String deptTypeCode) {
        this.deptTypeCode = deptTypeCode;
    }

    public String getDeptTypeName() {
        return this.deptTypeName;
    }

    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }

    public String getDeptRoleName() {
        return this.deptRoleName;
    }

    public void setDeptRoleName(String deptRoleName) {
        this.deptRoleName = deptRoleName;
    }

    public String getDeptParentCode() {
        return this.deptParentCode;
    }

    public void setDeptParentCode(String deptParentCode) {
        this.deptParentCode = deptParentCode;
    }

    public String getDeptParentName() {
        return this.deptParentName;
    }

    public void setDeptParentName(String deptParentName) {
        this.deptParentName = deptParentName;
    }

    public Date getEffectiveTimeLow() {
        return this.effectiveTimeLow;
    }

    public void setEffectiveTimeLow(Date effectiveTimeLow) {
        this.effectiveTimeLow = effectiveTimeLow;
    }

    public Date getEffectiveTimeHigh() {
        return this.effectiveTimeHigh;
    }

    public void setEffectiveTimeHigh(Date effectiveTimeHigh) {
        this.effectiveTimeHigh = effectiveTimeHigh;
    }

    public String getEmployerAddr() {
        return this.employerAddr;
    }

    public void setEmployerAddr(String employerAddr) {
        this.employerAddr = employerAddr;
    }

    public String getEmployerTelNo() {
        return this.employerTelNo;
    }

    public void setEmployerTelNo(String employerTelNo) {
        this.employerTelNo = employerTelNo;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getAssignedCode() {
        return this.assignedCode;
    }

    public void setAssignedCode(String assignedCode) {
        this.assignedCode = assignedCode;
    }

    public String getAssignedName() {
        return this.assignedName;
    }

    public void setAssignedName(String assignedName) {
        this.assignedName = assignedName;
    }

    public String getAssignedDeptCode() {
        return this.assignedDeptCode;
    }

    public void setAssignedDeptCode(String assignedDeptCode) {
        this.assignedDeptCode = assignedDeptCode;
    }

    public String getAssignedDeptName() {
        return this.assignedDeptName;
    }

    public void setAssignedDeptName(String assignedDeptName) {
        this.assignedDeptName = assignedDeptName;
    }

    public String getAssignedOntactPerson() {
        return this.assignedOntactPerson;
    }

    public void setAssignedOntactPerson(String assignedOntactPerson) {
        this.assignedOntactPerson = assignedOntactPerson;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public int getSortNum() {
        return this.sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public static void main(String[] args) {

        String xmlString = "<PRPM_IN401030UV01 xmlns=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ITSVersion=\"XML_1.0\" xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/PRPM_IN401030UV01.xsd\">\n" +
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
        xmlString = XmlUtil.removeNameSpace(xmlString);
        OrgDeptInfo baseInfo = null;

        try {
            Document document = DocumentHelper.parseText(xmlString);
            baseInfo = XmlBeanUtil.toBean(document, OrgDeptInfo.class,null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
