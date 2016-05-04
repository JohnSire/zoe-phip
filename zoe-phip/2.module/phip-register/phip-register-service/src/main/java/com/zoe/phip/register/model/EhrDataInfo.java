/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import javax.persistence.*;

import com.zoe.phip.module.service.entity.MasterEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-04-14
 */
@Table(name = "EHR_DATA_INFO")
public class EhrDataInfo extends MasterEntity {
    /**
     * 请求消息ID
     */
    @Column(name = "MSG_ID")
    private String msgId;
    /**
     * 患者标识
     */
    @Column(name = "PATIENT_ID")
    private String patientId;
    /**
     * 患者姓名
     */
    @Column(name = "PATIENT_NAME")
    private String patientName;
    /**
     * 居民健康卡号
     */
    @Column(name = "HEALTH_CARD_ID")
    private String healthCardId;
    /**
     * 居民身份证号
     */
    @Column(name = "ID_NO")
    private String idNo;
    /**
     * 机构组织机构代码
     */
    @Column(name = "ORG_CODE")
    private String orgCode;
    /**
     * 机构名称
     */
    @Column(name = "ORG_NAME")
    private String orgName;
    /**
     * 机构电话号码区号
     */
    @Column(name = "AREA_CODE")
    private String areaCode;
    /**
     * 机构电话号码
     */
    @Column(name = "TEL_NO")
    private String telNo;
    /**
     * 工作地址
     */
    @Column(name = "ADDRESS_STREET")
    private String addressStreet;
    /**
     * 活动服务结束时间
     */
    @Column(name = "OUT_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outTime;
    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;
    /**
     * 共享文档平台注册ID
     */
    @Column(name = "DOCUMENT_UNIQUE_ID")
    private String documentUniqueId;
    /**
     * 文档库唯一标识符
     */
    @Column(name = "REPOSITORY_UNIQUE_ID")
    private String repositoryUniqueId;

    public String getMsgId() {
        return this.msgId;
    }


    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getPatientId() {
        return this.patientId;
    }


    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public String getAreaCode() {
        return this.areaCode;
    }


    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTelNo() {
        return this.telNo;
    }


    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getAddressStreet() {
        return this.addressStreet;
    }


    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public Date getOutTime() {
        return this.outTime;
    }


    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocumentUniqueId() {
        return this.documentUniqueId;
    }


    public void setDocumentUniqueId(String documentUniqueId) {
        this.documentUniqueId = documentUniqueId;
    }

    public String getRepositoryUniqueId() {
        return this.repositoryUniqueId;
    }


    public void setRepositoryUniqueId(String repositoryUniqueId) {
        this.repositoryUniqueId = repositoryUniqueId;
    }
}
