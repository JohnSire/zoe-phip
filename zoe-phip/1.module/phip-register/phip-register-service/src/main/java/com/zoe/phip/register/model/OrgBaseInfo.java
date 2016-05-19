/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-04-25
 */
@Table(name = "PHIP_ORG_BASE_INFO")
public class OrgBaseInfo extends MasterEntity {
    /**
     * 机构科室标识:0：机构、1：科室
     */
    @Column(name = "TYPE")
    private int type;
    /**
     * 机构类型:机构类型：1：医疗机构、2：卫生管理机构、3：第三方机构
     */
    @Column(name = "CATALOG_ID")
    private int catalogId;
    /**
     * 机构代码:
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 机构名称
     */
    @Column(name = "ORG_NAME")
    private String orgName;
    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;
    /**
     * 联系电话
     */
    @Column(name = "TELECOM")
    private String telecom;
    /**
     * 有效时间（开始）
     */
    @Column(name = "EFFECTIVE_TIME_LOW")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date effectiveTimeLow;
    /**
     * 有效时间（结束）
     */
    @Column(name = "EFFECTIVE_TIME_HIGH")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date effectiveTimeHigh;
    /**
     * 科室名称
     */
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    /**
     * 父级机构
     */
    @Column(name = "PARENT_ID")
    private String parentId;
    /**
     * 申请者
     */
    @Column(name = "APPLICANT_ID")
    private String applicantId;

    /**
     * 法人代表
     */
    @Column(name = "REPRESENTATIVE")
    private String representative;
    /**
     * 营业执照
     */
    @Column(name = "LICENSE")
    private String license;
    /**
     * 信息负责人
     */
    @Column(name = "CHARGE")
    private String charge;
    /**
     * 信息负责人电话
     */
    @Column(name = "CHARGE_PHONE")
    private String chargePhone;
    /**
     * 组织机构代码
     */
    @Column(name = "AGENCY_CODE")
    private String agencyCode;
    /**
     * 行政区划
     */
    @Column(name = "GOV_ID")
    private String govId;
    /**
     * 邮政编码
     */
    @Column(name = "ZIP_CODE")
    private String zipCode;
    /**
     * 床位数
     */
    @Column(name = "BED")
    private Integer bed;
    /**
     * 公钥
     */
    @Column(name = "PUBLIC_KEY")
    private String publicKey;
    /**
     * 网址
     */
    @Column(name = "WEB")
    private String web;
    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;
    /**
     * 简称
     */
    @Column(name = "SHORT_NAME")
    private String shortName;
    /**
     * 预约提示
     */
    @Column(name = "NOTICE")
    private String notice;
    /**
     * 预约电话
     */
    @Column(name = "REGISTE_PHONE")
    private String registePhone;
    /**
     * 机构介绍
     */
    @Column(name = "SUMMARY")
    private String summary;
    /**
     * 地理_经度
     */
    @Column(name = "G_LONGITUDE")
    private double glongitude;
    /**
     * 地理_维度
     */
    @Column(name = "G_LATITUDE")
    private double glatitude;
    /**
     * 投诉电话
     */
    @Column(name = "COMPLAINT_TEL")
    private String complaintTel;
    /**
     * 城市代码
     */
    @Column(name = "CITY_CODE")
    private String cityCode;
    /**
     * 行政类型
     */
    @Column(name = "GOV_TYPE")
    private Integer govType;
    /**
     * 描述
     */
    @Column(name = "DESCR")
    private String descr;
    /**
     * 权限编码
     */
    @Column(name = "ROLECODE")
    private String rolecode;


    public String getCode() {
        return this.code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgName() {
        return this.orgName;
    }


    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelecom() {
        return this.telecom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public void setTelecom(String telecom) {
        this.telecom = telecom;
    }


    public void setEffectiveTimeLow(java.sql.Date effectiveTimeLow) {
        this.effectiveTimeLow = effectiveTimeLow;
    }


    public void setEffectiveTimeHigh(java.sql.Date effectiveTimeHigh) {
        this.effectiveTimeHigh = effectiveTimeHigh;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }


    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getParentId() {
        return this.parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getApplicantId() {
        return this.applicantId;
    }


    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }


    public String getRepresentative() {
        return this.representative;
    }


    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getLicense() {
        return this.license;
    }


    public void setLicense(String license) {
        this.license = license;
    }

    public String getCharge() {
        return this.charge;
    }


    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getChargePhone() {
        return this.chargePhone;
    }


    public void setChargePhone(String chargePhone) {
        this.chargePhone = chargePhone;
    }

    public String getAgencyCode() {
        return this.agencyCode;
    }


    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getGovId() {
        return this.govId;
    }


    public void setGovId(String govId) {
        this.govId = govId;
    }

    public String getZipCode() {
        return this.zipCode;
    }


    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getBed() {
        return this.bed;
    }


    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public String getWeb() {
        return this.web;
    }


    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getShortName() {
        return this.shortName;
    }


    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNotice() {
        return this.notice;
    }


    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getRegistePhone() {
        return this.registePhone;
    }


    public void setRegistePhone(String registePhone) {
        this.registePhone = registePhone;
    }

    public String getSummary() {
        return this.summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getComplaintTel() {
        return this.complaintTel;
    }


    public void setComplaintTel(String complaintTel) {
        this.complaintTel = complaintTel;
    }

    public String getCityCode() {
        return this.cityCode;
    }


    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getGovType() {
        return this.govType;
    }


    public void setGovType(Integer govType) {
        this.govType = govType;
    }

    public String getDescr() {
        return this.descr;
    }


    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getRolecode() {
        return this.rolecode;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public double getGlongitude() {
        return glongitude;
    }

    public void setGlongitude(double glongitude) {
        this.glongitude = glongitude;
    }

    public double getGlatitude() {
        return glatitude;
    }

    public void setGlatitude(double glatitude) {
        this.glatitude = glatitude;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public Date getEffectiveTimeHigh() {
        return effectiveTimeHigh;
    }

    public void setEffectiveTimeHigh(Date effectiveTimeHigh) {
        this.effectiveTimeHigh = effectiveTimeHigh;
    }

    public Date getEffectiveTimeLow() {
        return effectiveTimeLow;
    }

    public void setEffectiveTimeLow(Date effectiveTimeLow) {
        this.effectiveTimeLow = effectiveTimeLow;
    }
}
