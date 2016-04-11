/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.BaseEntity;

import javax.persistence.*;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_XMAN_CARD")
public class XmanCard extends BaseEntity {
    /**
     * 健康卡代码
     */
    @Column(name = "XC_CARD_CODE")
    private String xcCardCode;
    /**
     * 卡类型
     */
    @Column(name = "XC_CARD_TYPE")
    private String xcCardType;
    /**
     * 注册用户索引
     */
    @Column(name = "XC_XMAN_ID")
    private String xcXmanId;
    /**
     * 系统的患者ID
     */
    @Column(name = "HEALTH_RECORD_NO")
    private String healthRecordNo;
    /**
     * 机构
     */
    @Column(name = "XC_ORG_ID")
    private String xcOrgId;
    /**
     * 创建人
     */
    @Column(name = "XC_CREATE_BY")
    private String xcCreateBy;
    /**
     * 创建时间
     */
    @Column(name = "XC_CREATE_AT")
    private java.sql.Timestamp xcCreateAt;
    /**
     * 最后修改人
     */
    @Column(name = "XC_UPDATE_BY")
    private String xcUpdateBy;
    /**
     * 最后修改时间
     */
    @Column(name = "XC_UPDATE_AT")
    private java.sql.Timestamp xcUpdateAt;
    /**
     * 描述
     */
    @Column(name = "XC_DESCR")
    private String xcDescr;
    /**
     * 状态
     */
    @Column(name = "XC_STATUS")
    private java.math.BigDecimal xcStatus;
    /**
     * 健康卡发放机构代码
     */
    @Column(name = "XC_ORG_CODE")
    private String xcOrgCode;

    public String getXcCardCode() {
        return this.xcCardCode;
    }

    public void setXcCardCode(String xcCardCode) {
        this.xcCardCode = xcCardCode;
    }

    public String getXcCardType() {
        return this.xcCardType;
    }

    public void setXcCardType(String xcCardType) {
        this.xcCardType = xcCardType;
    }

    public String getXcXmanId() {
        return this.xcXmanId;
    }

    public void setXcXmanId(String xcXmanId) {
        this.xcXmanId = xcXmanId;
    }

    public String getHealthRecordNo() {
        return this.healthRecordNo;
    }

    public void setHealthRecordNo(String healthRecordNo) {
        this.healthRecordNo = healthRecordNo;
    }

    public String getXcOrgId() {
        return this.xcOrgId;
    }

    public void setXcOrgId(String xcOrgId) {
        this.xcOrgId = xcOrgId;
    }

    public String getXcCreateBy() {
        return this.xcCreateBy;
    }

    public void setXcCreateBy(String xcCreateBy) {
        this.xcCreateBy = xcCreateBy;
    }

    public java.sql.Timestamp getXcCreateAt() {
        return this.xcCreateAt;
    }

    public void setXcCreateAt(java.sql.Timestamp xcCreateAt) {
        this.xcCreateAt = xcCreateAt;
    }

    public String getXcUpdateBy() {
        return this.xcUpdateBy;
    }

    public void setXcUpdateBy(String xcUpdateBy) {
        this.xcUpdateBy = xcUpdateBy;
    }

    public java.sql.Timestamp getXcUpdateAt() {
        return this.xcUpdateAt;
    }

    public void setXcUpdateAt(java.sql.Timestamp xcUpdateAt) {
        this.xcUpdateAt = xcUpdateAt;
    }

    public String getXcDescr() {
        return this.xcDescr;
    }

    public void setXcDescr(String xcDescr) {
        this.xcDescr = xcDescr;
    }

    public java.math.BigDecimal getXcStatus() {
        return this.xcStatus;
    }

    public void setXcStatus(java.math.BigDecimal xcStatus) {
        this.xcStatus = xcStatus;
    }

    public String getXcOrgCode() {
        return this.xcOrgCode;
    }

    public void setXcOrgCode(String xcOrgCode) {
        this.xcOrgCode = xcOrgCode;
    }
}
