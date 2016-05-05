/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import javax.persistence.*;
import com.zoe.phip.module.service.entity.MasterEntity;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-05-05
 */
@Table(name = "PHIP_EHR_DATA_INFO")
public class EhrDataInfo extends MasterEntity {
    /**
     * 请求消息ID(guid)
     */
    @Column(name = "MSG_ID")
    private String msgId;
    /**
     * 患者标识(y)
     */
    @Column(name = "PATIENT_ID")
    private String patientId;
    /**
     * 患者姓名(y)
     */
    @Column(name = "PATIENT_NAME")
    private String patientName;
    /**
     * 居民健康档案编码（y)
     */
    @Column(name = "HEALTH_CARD_ID")
    private String healthCardId;
    /**
     * 居民身份证号(y)
     */
    @Column(name = "ID_NO")
    private String idNo;
    /**
     * 机构组织机构代码(y)
     */
    @Column(name = "ORG_CODE")
    private String orgCode;
    /**
     * 机构名称(y)
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
     * 机构邮箱地址
     */
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    /**
     * 工作地址-市（地区）
     */
    @Column(name = "ADDRESS_CITY")
    private String addressCity;
    /**
     * 邮政编码--国家
     */
    @Column(name = "ADDRESS_COUNTRY")
    private String addressCountry;
    /**
     * 工作地址-邮编
     */
    @Column(name = "ADDRESS_POSTAL")
    private String addressPostal;
    /**
     * 工作地址-省（自治区、直辖市）
     */
    @Column(name = "ADDRESS_STATE")
    private String addressState;
    /**
     * 工作地址-乡（镇、街道办事处）
     */
    @Column(name = "ADDRESS_STREET")
    private String addressStreet;
    /**
     * 工作地址-门牌号码
     */
    @Column(name = "STREET_NUMBER")
    private String streetNumber;
    /**
     * 提交时间
     */
    @Column(name = "COMMIT_TIME")
    private Date commitTime;
    /**
     * 共享文档注册唯一标识符(可以是提交时生成，也可以为空.)
     */
    @Column(name = "UNIQUE_ID")
    private String uniqueId;
    /**
     * 源文档ID
     */
    @Column(name = "SOURCE_ID")
    private String sourceId;
    /**
     * 备注
     */
    @Column(name = "COMMENTS")
    private String comments;
    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;
    /**
     * 服务机构名称
     */
    @Column(name = "SERVER_ORG_NAME")
    private String serverOrgName;
    /**
     * 活动服务编号(如住院号、就诊号、会诊号等)
     */
    @Column(name = "EPISODE_ID")
    private String episodeId;
    /**
     * 活动服务开始时间(y)
     */
    @Column(name = "IN_TIME")
    private Date inTime;
    /**
     * 活动服务结束时间(y)
     */
    @Column(name = "OUT_TIME")
    private Date outTime;
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
     * 文档ID
     */
    @Column(name = "DOCUMENT_ID")
    private String documentId;
    /**
     * 上级文档关联关系
     */
    @Column(name = "PARENT_DOCUMENT_RELATIONSHIP")
    private String parentDocumentRelationship;
    /**
     * 上级文档ID
     */
    @Column(name = "PARENT_DOCUMENT_ID")
    private String parentDocumentId;
    /**
     * 文档内容
     */
    @Column(name = "CONTENT")
    private String content;
    /**
     * 响应消息ID
     */
    @Column(name = "RESPONSE_ID")
    private String responseId;
    /**
     * 共享文档平台注册ID(文档注册时生成的的唯一编号)guid(y)
     */
    @Column(name = "DOCUMENT_UNIQUE_ID")
    private String documentUniqueId;
    /**
     * 文档库唯一标识符(注册响应ID)guid(y)
     */
    @Column(name = "REPOSITORY_UNIQUE_ID")
    private String repositoryUniqueId;
    /**
     * 文档注册URL 地址
     */
    @Column(name = "DOUMENT_URL")
    private String doumentUrl;
    /**
     * 文档类型
     */
    @Column(name = "MIME_TYPE")
    private String mimeType;
    /**
     * 事件号(y)
     */
    @Column(name = "EVENT_NO")
    private String eventNo;

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

    public Date getCommitTime() {
        return this.commitTime;
    }


    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }


    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSourceId() {
        return this.sourceId;
    }


    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getServerOrgName() {
        return this.serverOrgName;
    }


    public void setServerOrgName(String serverOrgName) {
        this.serverOrgName = serverOrgName;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }


    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public Date getInTime() {
        return this.inTime;
    }


    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return this.outTime;
    }


    public void setOutTime(Date outTime) {
        this.outTime = outTime;
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

    public String getDocumentId() {
        return this.documentId;
    }


    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getParentDocumentRelationship() {
        return this.parentDocumentRelationship;
    }


    public void setParentDocumentRelationship(String parentDocumentRelationship) {
        this.parentDocumentRelationship = parentDocumentRelationship;
    }

    public String getParentDocumentId() {
        return this.parentDocumentId;
    }


    public void setParentDocumentId(String parentDocumentId) {
        this.parentDocumentId = parentDocumentId;
    }

    public String getContent() {
        return this.content;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public String getResponseId() {
        return this.responseId;
    }


    public void setResponseId(String responseId) {
        this.responseId = responseId;
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

    public String getDoumentUrl() {
        return this.doumentUrl;
    }


    public void setDoumentUrl(String doumentUrl) {
        this.doumentUrl = doumentUrl;
    }

    public String getMimeType() {
        return this.mimeType;
    }


    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getEventNo() {
        return this.eventNo;
    }


    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }
}
