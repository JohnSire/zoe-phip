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
@Table(name = "PHIP_XMAN_EHR_CONTENT")
public class XmanEhrContent extends MasterEntity {
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
    /**
     * 档案数据
     */
    @Column(name = "CONTENT")
    private java.sql.Clob content;
    /**
     * 流程类型（0：归档流程，1：即时调用，3：档案注册）
     */
    @Column(name = "FLOW_TYPE")
    private java.math.BigDecimal flowType;

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

    public java.sql.Clob getContent() {
        return this.content;
    }


    public void setContent(java.sql.Clob content) {
        this.content = content;
    }

    public java.math.BigDecimal getFlowType() {
        return this.flowType;
    }


    public void setFlowType(java.math.BigDecimal flowType) {
        this.flowType = flowType;
    }
}
