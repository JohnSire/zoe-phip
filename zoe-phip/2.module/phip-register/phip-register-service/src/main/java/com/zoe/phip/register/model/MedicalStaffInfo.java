/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.infrastructure.annotation.XPath;
import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.*;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_MEDICAL_STAFF_INFO")
public class MedicalStaffInfo extends MasterEntity {
    /**
     * 医务人员id
     */
    @Column(name = "STAFF_ID")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/id", descr = "医务人员id")
    private String staffId;
    /**
     * 姓名
     */
    @Column(name = "NAME")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/name", descr = "姓名")
    private String name;
    /**
     * 性别代码
     */
    @Column(name = "GENDER_CODE")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/administrativeGenderCode/@code", descr = "性别代码")
    private String genderCode;
    /**
     * 性别名称
     */
    @Column(name = "GENDER_NAME")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/administrativeGenderCode/@displayName", descr = "姓名名称")
    private String genderName;
    /**
     * 出生日期
     */
    @Column(name = "BIRTH_TIME")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/birthTime/@value", descr = "出生日期")
    private Date birthTime;
    /**
     * 出生地
     */
    @Column(name = "BIRTHPLACE_ADDR")
    @XPath(value = "//controlActProcess/subject/registrationRequest//subject1/healthCareProvider/healthCarePrincipalPerson/birthplace/addr", descr = "出生地")
    private String birthplaceAddr;
    /**
     * 身份证号
     */
    @Column(name = "ID_NO")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/id/@extension", descr = "身份证号")
    private String idNo;
    /**
     * 专业技术职务代码
     */
    @Column(name = "TECHNICAL_CODE")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/code/@code", descr = "专业技术职务代码")
    private String technicalCode;

    /**
     * 专业技术职务名称
     */
    @Column(name = "TECHNICAL_NAME")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/code/@displayName", descr = "专业技术职务名称")
    private String technicalName;

    /**
     * 工作地址
     */
    @Column(name = "EMPLOYER_ADDR")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/addr", descr = "工作地址")
    private String employerAddr;
    /**
     * 工作联系电话
     */
    @Column(name = "EMPLOYER_TEL_NO")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/telecom", descr = "工作联系电话")
    private String employerTelNo;
    /**
     * 角色有效期间(起始日期)
     */
    @Column(name = "EFFECTIVE_TIME_LOW")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/effectiveTime/low/@value", descr = "角色有效期间(起始日期)")
    private Date effectiveTimeLow;
    /**
     * 角色有效期间(截止日期)
     */
    @Column(name = "EFFECTIVE_TIME_HIGH")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/effectiveTime/high/@value", descr = "角色有效期间(截止日期)")
    private Date effectiveTimeHigh;
    /**
     * 科室号
     */
    @Column(name = "AFFILIATED_ORG_CODE")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/asAffiliate/affiliatedPrincipalOrganization/id/@extension", descr = "科室号")
    private String affiliatedOrgCode;
    /**
     * 科室名称
     */
    @Column(name = "AFFILIATED_ORG_NAME")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/asAffiliate/affiliatedPrincipalOrganization/name", descr = "科室名称")
    private String affiliatedOrgName;
    /**
     * 消息id
     */
    @Column(name = "MSG_ID")
    @XPath(value = "//id/@root", descr = "消息id")
    private String msgId;

    /**
     * 申请者代码
     */
    @Column(name = "ASSIGNED_CODE")
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/id/@extension", descr = "申请者代码")
    private String assignedCode;
    /**
     * 申请者名称
     */
    @Column(name = "ASSIGNED_NAME")
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/assignedPerson/name", descr = "申请者名称")
    private String assignedName;
    /**
     * 申请者科室代码
     */
    @Column(name = "ASSIGNED_DEPT_CODE")
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/representedOrganization/id/@extension", descr = "申请者科室代码")
    private String assignedDeptCode;
    /**
     * 申请者科室名称
     */
    @Column(name = "ASSIGNED_DEPT_NAME")
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/representedOrganization/name", descr = "申请者科室名称")
    private String assignedDeptName;
    /**
     * 申请联系人
     */
    @Column(name = "ASSIGNED_ONTACT_PERSON")
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/representedOrganization/contactParty/contactPerson/name", descr = "申请联系人")
    private String assignedOntactPerson;

    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenderCode() {
        return this.genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderName() {
        return this.genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Date getBirthTime() {
        return this.birthTime;
    }

    public void setBirthTime(Date birthTime) {
        this.birthTime = birthTime;
    }

    public String getBirthplaceAddr() {
        return this.birthplaceAddr;
    }

    public void setBirthplaceAddr(String birthplaceAddr) {
        this.birthplaceAddr = birthplaceAddr;
    }

    public String getIdNo() {
        return this.idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getTechnicalCode() {
        return this.technicalCode;
    }

    public void setTechnicalCode(String technicalCode) {
        this.technicalCode = technicalCode;
    }

    public String getTechnicalName() {
        return this.technicalName;
    }

    public void setTechnicalName(String technicalName) {
        this.technicalName = technicalName;
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

    public String getAffiliatedOrgCode() {
        return this.affiliatedOrgCode;
    }

    public void setAffiliatedOrgCode(String affiliatedOrgCode) {
        this.affiliatedOrgCode = affiliatedOrgCode;
    }

    public String getAffiliatedOrgName() {
        return this.affiliatedOrgName;
    }

    public void setAffiliatedOrgName(String affiliatedOrgName) {
        this.affiliatedOrgName = affiliatedOrgName;
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




}
