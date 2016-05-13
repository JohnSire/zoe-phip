/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateEmail;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateIDCard;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNumberPlusMinus;
import com.zoe.phip.module.service.entity.base.RegisterEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-05-06
 */
@Table(name = "PHIP_XMAN_INDEX")
public class XmanIndex extends RegisterEntity {
    /**
     * 档案地区编码
     */
    @Column(name = "AREA_CODE")
    private String areaCode;
    /**
     * 个人主索引
     */
    @Column(name = "XMAN_ID")
    private String xmanId;
    /**
     * 机构编码
     */
    @Column(name = "ORG_CODE")
    private String orgCode;
    /**
     * 机构名称
     */
    @Column(name = "ORG_NAME")
    private String orgName;
    /**
     * 事件号（活动服务编号）
     */
    @Column(name = "EVENT_NO")
    private String eventNo;
    /**
     * 事件类型
     */
    @Column(name = "EVENT_TYPE")
    private String eventType;
    /**
     * 患者姓名
     */
    @Column(name = "PATIENT_NAME")
    private String patientName;
    /**
     * 健康卡号
     */
    @Column(name = "HEALTH_CARD_ID")
    private String healthCardId;
    /**
     * 身份证号
     */
    @Column(name = "ID_NO")
    @ValidateIDCard
    private String idNo;
    /**
     * 机构电话号码区号
     */
    @Column(name = "PHONE_CODE")
    @ValidateNumberPlusMinus(message = "机构电话号码区号不符合规则")
    private String phoneCode;
    /**
     * 机构电话号码
     */
    @Column(name = "TEL_NO")
    @ValidateNumberPlusMinus(message = "机构电话号码不符合规则")
    private String telNo;
    /**
     * 机构邮箱地址
     */
    @Column(name = "EMAIL_ADDRESS")
    @ValidateEmail
    private String emailAddress;
    /**
     * 工作地址-省（自治区、直辖
     * 市）
     */
    @Column(name = "ADDRESS_CITY")
    private String addressCity;
    /**
     * 工作地址-市（地区）
     */
    @Column(name = "ADDRESS_COUNTRY")
    private String addressCountry;
    /**
     * 工作地址-县（区）
     */
    @Column(name = "ADDRESS_POSTAL")
    private String addressPostal;
    /**
     * 工作地址-乡（镇、街道办事
     * 处）
     */
    @Column(name = "ADDRESS_STATE")
    private String addressState;
    /**
     * 工作地址-村（街、路、弄等）
     */
    @Column(name = "ADDRESS_STREET")
    private String addressStreet;
    /**
     * 工作地址-门牌号码
     */
    @Column(name = "STREET_NUMBER")
    private String streetNumber;
    /**
     * 备注（诊断）
     */
    @Column(name = "COMMENTS")
    private String comments;
    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;
    /**
     * 服务机构名称
     */
    @Column(name = "SERVER_ORG_NAME")
    private String serverOrgName;
    /**
     * 负责人所属科室名称
     */
    @Column(name = "ADMISSION_DEPART")
    private String admissionDepart;
    /**
     * 负责人姓名
     */
    @Column(name = "ADMISSION_DOCTOR")
    private String admissionDoctor;
    /**
     * 活动类型描述
     */
    @Column(name = "ADMISSION_TYPE")
    private String admissionType;
    /**
     * 活动服务结果类型描述
     */
    @Column(name = "DIAGNOSIS_RESULT")
    private String diagnosisResult;
    /**
     * 数据记录者姓名
     */
    @Column(name = "AUTHOR_NAME")
    private String authorName;
    /**
     * 数据记录者所属机构名称
     */
    @Column(name = "AUTHOR_INSTITUTION")
    private String authorInstitution;
    /**
     * 数据记录者所属科室名称
     */
    @Column(name = "AUTHOR_SPECIALTY")
    private String authorSpecialty;
    /**
     * 数据记录者级别
     */
    @Column(name = "AUTHOR_ROLE")
    private String authorRole;
    /**
     * 活动服务起始时间
     */
    @Column(name = "START_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    /**
     * 活动服务结束时间
     */
    @Column(name = "END_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /*
    * 请求消息ID
    * */
    @Transient
    private String msgId;

    /*
    * 档案ID
    * */
    @Transient
    private String ehrId;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 病人ID
     */
    private String patientId;


    @Transient
    private String context;



    public String getAreaCode() {
        return this.areaCode;
    }


    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getXmanId() {
        return this.xmanId;
    }


    public void setXmanId(String xmanId) {
        this.xmanId = xmanId;
    }

    public String getOrgCode() {
        return this.orgCode;
    }


    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return this.orgName;
    }


    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEventNo() {
        return this.eventNo;
    }


    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getEventType() {
        return this.eventType;
    }


    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPatientName() {
        return this.patientName;
    }


    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getHealthCardId() {
        return this.healthCardId;
    }


    public void setHealthCardId(String healthCardId) {
        this.healthCardId = healthCardId;
    }

    public String getIdNo() {
        return this.idNo;
    }


    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPhoneCode() {
        return this.phoneCode;
    }


    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getTelNo() {
        return this.telNo;
    }


    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddressCity() {
        return this.addressCity;
    }


    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCountry() {
        return this.addressCountry;
    }


    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressPostal() {
        return this.addressPostal;
    }


    public void setAddressPostal(String addressPostal) {
        this.addressPostal = addressPostal;
    }

    public String getAddressState() {
        return this.addressState;
    }


    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressStreet() {
        return this.addressStreet;
    }


    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getStreetNumber() {
        return this.streetNumber;
    }


    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getComments() {
        return this.comments;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getServerOrgName() {
        return this.serverOrgName;
    }


    public void setServerOrgName(String serverOrgName) {
        this.serverOrgName = serverOrgName;
    }

    public String getAdmissionDepart() {
        return this.admissionDepart;
    }


    public void setAdmissionDepart(String admissionDepart) {
        this.admissionDepart = admissionDepart;
    }

    public String getAdmissionDoctor() {
        return this.admissionDoctor;
    }


    public void setAdmissionDoctor(String admissionDoctor) {
        this.admissionDoctor = admissionDoctor;
    }

    public String getAdmissionType() {
        return this.admissionType;
    }


    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getDiagnosisResult() {
        return this.diagnosisResult;
    }


    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    public String getAuthorName() {
        return this.authorName;
    }


    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorInstitution() {
        return this.authorInstitution;
    }


    public void setAuthorInstitution(String authorInstitution) {
        this.authorInstitution = authorInstitution;
    }

    public String getAuthorSpecialty() {
        return this.authorSpecialty;
    }


    public void setAuthorSpecialty(String authorSpecialty) {
        this.authorSpecialty = authorSpecialty;
    }

    public String getAuthorRole() {
        return this.authorRole;
    }


    public void setAuthorRole(String authorRole) {
        this.authorRole = authorRole;
    }

    public Date getStartTime() {
        return this.startTime;
    }


    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getEhrId() {
        return ehrId;
    }

    public void setEhrId(String ehrId) {
        this.ehrId = ehrId;
    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
