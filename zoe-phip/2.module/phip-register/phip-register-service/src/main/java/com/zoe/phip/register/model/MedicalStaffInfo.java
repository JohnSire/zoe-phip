/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;
import com.zoe.phip.register.model.base.RegisterEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-04-18
 */
@Table(name = "PHIP_MEDICAL_STAFF_INFO")
public class MedicalStaffInfo extends RegisterEntity {
    /**
     * 医务人员ID
     */
    @Column(name = "EXTENSION_ID")
    private String extensionId;
    /**
     * 姓名
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 性别代码
     */
    @Column(name = "GENDER_CODE")
    private String genderCode;
    /**
     * 性别名称
     */
    @Transient
//    @Column(name = "GENDER_NAME")
    private String genderName;



    /**
     * 隶属科室
     */
    @Transient
    private String deptName;

    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 出生日期
     */
    @Column(name = "BIRTH_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthTime;
    /**
     * 出生地
     */
    @Column(name = "BIRTHPLACE_ADDR")
    private String birthplaceAddr;
    /**
     * 身份证号
     */
    @Column(name = "ID_NO")
    //
    private String idNo;
    /**
     * 专业技术职务代码
     */
    @Column(name = "TECHNICAL_CODE")
    private String technicalCode;

    /**
     * 专业技术职务名称
     */
    @Transient
//    @Column(name = "TECHNICAL_NAME")
    private String technicalName;

    /**
     * 工作地址
     */
    @Column(name = "EMPLOYER_ADDR")
    private String employerAddr;
    /**
     * 工作联系电话
     */
    //
    @Column(name = "EMPLOYER_TEL_NO")
    private String employerTelNo;
    /**
     * 角色有效期间(起始日期)
     */
    @Column(name = "EFFECTIVE_TIME_LOW")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effectiveTimeLow;
    /**
     * 角色有效期间(截止日期)
     */
    @Column(name = "EFFECTIVE_TIME_HIGH")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effectiveTimeHigh;
    /**
     * 机构编码
     */
    @Column(name = "AFFILIATED_ORG_CODE")
    private String affiliatedOrgCode;
    /**
     * 机构名称
     */
//    @Column(name = "AFFILIATED_ORG_NAME")
    @Transient
    private String affiliatedOrgName;
    /**
     * 消息id
     */
    @Column(name = "MSG_ID")
    private String msgId;

    /**
     * 申请者代码
     */
    @Column(name = "ASSIGNED_CODE")
    private String assignedCode;
    /**
     * 申请者名称
     */
    @Column(name = "ASSIGNED_NAME")
    private String assignedName;
    /**
     * 申请者科室代码
     */
    @Column(name = "ASSIGNED_DEPT_CODE")
    private String assignedDeptCode;
    /**
     * 申请者科室名称
     */
//    @Column(name = "ASSIGNED_DEPT_NAME")
    @Transient
    private String assignedDeptName;

    public String getAssignedContactPerson() {
        return assignedContactPerson;
    }

    public void setAssignedContactPerson(String assignedContactPerson) {
        this.assignedContactPerson = assignedContactPerson;
    }

    /**
     * 申请联系人
     */
    @Column(name = "ASSIGNED_CONTACT_PERSON")
    private String assignedContactPerson;
    /**
     * 医护人员激活状态
     */
    @Column(name = "STATUS_CODE")
    private String statusCode;

    //@Transient
    @Column(name = "CREATION_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date creationTime;


    @Transient
    private String interactionId;

    /**
     * 根节点
     */
    @Transient
    private String typeCode;

    /**
     * 操作(Add,Update,Query)
     */

    @Transient
    private String operateCode;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }


    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }


    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public String getExtensionId() {
        return this.extensionId;
    }


    public void setExtensionId(String extensionId) {
        this.extensionId = extensionId;
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


    public String getStatusCode() {
        return this.statusCode;
    }


    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
