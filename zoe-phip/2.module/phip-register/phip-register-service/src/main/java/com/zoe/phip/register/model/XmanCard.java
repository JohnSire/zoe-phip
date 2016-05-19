/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.*;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_XMAN_CARD")
public class XmanCard extends MasterEntity {
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
     * 城乡居民健康档案编号
     */
    @Column(name = "HEALTH_RECORD_NO")
    private String healthRecordNo;
    /**
     * 机构
     */
    @Column(name = "XC_ORG_ID")
    private String xcOrgId;
    /**
     * 描述
     */
    @Column(name = "XC_DESCR")
    private String xcDescr;

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

    public String getXcDescr() {
        return this.xcDescr;
    }

    public void setXcDescr(String xcDescr) {
        this.xcDescr = xcDescr;
    }

    public String getXcOrgCode() {
        return this.xcOrgCode;
    }

    public void setXcOrgCode(String xcOrgCode) {
        this.xcOrgCode = xcOrgCode;
    }
}
