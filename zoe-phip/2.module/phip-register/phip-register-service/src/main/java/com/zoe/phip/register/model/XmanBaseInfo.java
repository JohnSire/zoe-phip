/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;
import com.zoe.phip.module.service.entity.BaseEntity;
import com.zoe.phip.register.service.annotation.XPath;

import javax.persistence.*;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_XMAN_BASE_INFO")
public class XmanBaseInfo extends MasterEntity {
    /**
     * 注册机构名称
     */
    @XPath(value = "",descr = "")
    @Column(name = "ORG_NAME")
    private String orgName;
    /**
     * 注册机构编码
     */
    @Column(name = "ORG_CODE")
    private String orgCode;
    /**
     * 注册用户索引
     */
    @Column(name = "XMAN_ID")
    private String xmanId;
    /**
     * 身份证件号码
     */
    @Column(name = "ID_NO")
    private String idNo;
    /**
     * 本人姓名
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 性别代码
     */
    @Column(name = "SEX_CODE")
    private Boolean sexCode;
    /**
     * 出生日期
     */
    @Column(name = "BIRTH_DATE")
    private java.sql.Timestamp birthDate;
    /**
     * 身份证件类别代码
     */
    @Column(name = "ID_TYPE_CODE")
    private Byte idTypeCode;
    /**
     * 工作单位名称
     */
    @Column(name = "EMPLOYER_NAME")
    private String employerName;
    /**
     * 联系电话
     */
    @Column(name = "TEL_NO")
    private String telNo;
    /**
     * 联系人姓�
     */
    @Column(name = "REL_NAME")
    private String relName;
    /**
     * 联系人电�
     */
    @Column(name = "REL_TEL_NO")
    private String relTelNo;
    /**
     * 常住地址户籍
     */
    @Column(name = "ADDRESS")
    private String address;
    /**
     * 邮政编码
     */
    @Column(name = "POSTALCODE")
    private String postalcode;
    /**
     * 民族代码
     */
    @Column(name = "NATIONALITY_CODE")
    private Byte nationalityCode;
    /**
     * abo血型代�
     */
    @Column(name = "ABO_CODE")
    private Boolean aboCode;
    /**
     * rh血型代�
     */
    @Column(name = "RH_CODE")
    private Boolean rhCode;
    /**
     * 学历代码
     */
    @Column(name = "EDUCATION_CODE")
    private Byte educationCode;
    /**
     * 职业类别代码
     */
    @Column(name = "OCCUPATION_CODE")
    private String occupationCode;
    /**
     * 婚姻状况代码
     */
    @Column(name = "MARRIAGE_CODE")
    private Byte marriageCode;
    /**
     * 医疗保险类别代码
     */
    @Column(name = "CODE_SYS_CODE")
    private String codeSysCode;
    /**
     * 医疗保险类别名称
     */
    @Column(name = "CODE_SYS_NAME")
    private String codeSysName;

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getXmanId() {
        return this.xmanId;
    }

    public void setXmanId(String xmanId) {
        this.xmanId = xmanId;
    }

    public String getIdNo() {
        return this.idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSexCode() {
        return this.sexCode;
    }

    public void setSexCode(Boolean sexCode) {
        this.sexCode = sexCode;
    }

    public java.sql.Timestamp getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(java.sql.Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public Byte getIdTypeCode() {
        return this.idTypeCode;
    }

    public void setIdTypeCode(Byte idTypeCode) {
        this.idTypeCode = idTypeCode;
    }

    public String getEmployerName() {
        return this.employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getTelNo() {
        return this.telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getRelName() {
        return this.relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getRelTelNo() {
        return this.relTelNo;
    }

    public void setRelTelNo(String relTelNo) {
        this.relTelNo = relTelNo;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return this.postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public Byte getNationalityCode() {
        return this.nationalityCode;
    }

    public void setNationalityCode(Byte nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public Boolean getAboCode() {
        return this.aboCode;
    }

    public void setAboCode(Boolean aboCode) {
        this.aboCode = aboCode;
    }

    public Boolean getRhCode() {
        return this.rhCode;
    }

    public void setRhCode(Boolean rhCode) {
        this.rhCode = rhCode;
    }

    public Byte getEducationCode() {
        return this.educationCode;
    }

    public void setEducationCode(Byte educationCode) {
        this.educationCode = educationCode;
    }

    public String getOccupationCode() {
        return this.occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public Byte getMarriageCode() {
        return this.marriageCode;
    }

    public void setMarriageCode(Byte marriageCode) {
        this.marriageCode = marriageCode;
    }

    public String getCodeSysCode() {
        return this.codeSysCode;
    }

    public void setCodeSysCode(String codeSysCode) {
        this.codeSysCode = codeSysCode;
    }

    public String getCodeSysName() {
        return this.codeSysName;
    }

    public void setCodeSysName(String codeSysName) {
        this.codeSysName = codeSysName;
    }
}
