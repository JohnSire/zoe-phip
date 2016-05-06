/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.Column;
import javax.persistence.Table;


/**
 * @author
 * @version 1.0
 * @date 2016-05-06
 */
@Table(name = "PHIP_XMAN_EHR")
public class XmanEhr extends MasterEntity {
    /**
     * 源文档ID（注册用）
     */
    @Column(name = "SOURCE_ID")
    private String sourceId;
    /**
     * 文档ID（注册用）
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
     * 事件ID
     */
    @Column(name = "INDEX_ID")
    private String indexId;
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
     * 病人ID
     */
    @Column(name = "PATIENT_ID")
    private String patientId;
    /**
     * 事件号
     */
    @Column(name = "EVENT_NO")
    private String eventNo;
    /**
     * 事件类型
     */
    @Column(name = "EVENT_TYPE")
    private String eventType;
    /**
     * 健康档案编码（CDA）
     */
    @Column(name = "CATALOG_CODE")
    private String catalogCode;

    public String getSourceId() {
        return this.sourceId;
    }


    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public String getIndexId() {
        return this.indexId;
    }


    public void setIndexId(String indexId) {
        this.indexId = indexId;
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

    public String getPatientId() {
        return this.patientId;
    }


    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public String getCatalogCode() {
        return this.catalogCode;
    }


    public void setCatalogCode(String catalogCode) {
        this.catalogCode = catalogCode;
    }
}
