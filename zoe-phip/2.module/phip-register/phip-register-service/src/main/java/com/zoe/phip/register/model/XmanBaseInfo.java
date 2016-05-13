/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.infrastructure.annotation.XPath;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateIDCard;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNumberPlusMinus;
import com.zoe.phip.module.service.entity.base.RegisterEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_XMAN_BASE_INFO")
public class XmanBaseInfo extends RegisterEntity {

//    private String id;


    /**
     * 注册机构编码
     */
    @Column(name = "ORG_CODE")
    private String orgCode;

    /**
     * 注册机构名称
     */
//    @Column(name = "ORG_NAME"
    @Transient
    private String orgName;

    /**
     * 身份证件号码
     */
    @Column(name = "ID_NO")
    @ValidateIDCard
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
    private String sexCode;

    /**
     * 性别名称
     */
    @Transient
//    @Column(name = "SEX_CODE_NAME")
    private String sexCodeName;


    /**
     * 出生日期
     */
    @Column(name = "BIRTH_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    /**
     * 身份证件类别代码
     */
    @XPath(defaultValue = "01")
    @Column(name = "ID_TYPE_CODE")
    private Integer idTypeCode;

    /**
     * 工作单位名称
     */
    @Column(name = "EMPLOYER_NAME")
    private String employerName;


    /**
     * 工作单位联系方式
     */
    @Column(name = "EMPLOYER_TEL_NO")
    private String employerTelNo;


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
    @ValidateNumberPlusMinus(message = "联系人电话不符合规则")
    private String relTelNo;

    /**
     * 常住地址户籍
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 地址
     */
    @Column(name = "STATE_CODE")
    private String stateCode;

    /**
     * 地址
     */
    @Column(name = "CITY_CODE")
    private String cityCode;

    /**
     * 地址
     */
    @Column(name = "AREA_CODE")
    private String areaCode;
    /**
     * 地址
     */
    @Column(name = "STREET_CODE")
    private String streetCode;
    /**
     *
     */
    @Column(name = "COMMITTEE_CODE")
    private String committeeCode;

    /**
     * 门牌�
     */
    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;

    /**
     * 邮政编码
     */
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    /**
     * 民族代码
     */
    @Column(name = "NATIONALITY_CODE")
    private String nationalityCode;

    /**
     * 民族名称
     */
    @Transient
//    @Column(name = "NATIONALITY_NAME")
    private String nationalityName;


    /**
     * abo血型代�
     */
    @XPath(descr = "5")
    @Column(name = "ABO_CODE")
    private Integer aboCode;

    /**
     * rh血型代�
     */
    @XPath(descr = "3")
    @Column(name = "RH_CODE")
    private Integer rhCode;

    /**
     * 学历代码
     */
    @XPath(defaultValue = "90")
    @Column(name = "EDUCATION_CODE")
    private Integer educationCode;

    /**
     * 职业类别代码
     */
    @Column(name = "OCCUPATION_CODE")
    private String occupationCode;


    /**
     * 职业类别代码
     */
    @Transient
    private String occupationName;

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }


    /**
     * 婚姻状况代码
     */
    @Column(name = "MARRIAGE_CODE")
    private String marriageCode;

    /**
     * 婚姻状况名称
     */
    @Transient
//    @Column(name = "MARRIAGE_Name")
    private String marriageName;


    /**
     * 医疗保险类别代码
     */
    @Column(name = "CODE_SYS_CODE")
    private String codeSysCode;

    /**
     * 医疗保险类别名称
     */
    @Transient
    private String codeSysName;

    /**
     * 作者职工代�
     */
    @Column(name = "ASSIGNED_PERSON_CODE")
    private String assignedPersonCode;

    /**
     * 作者人姓名
     */
    @Column(name = "ASSIGNED_PERSON_NAME")
    private String assignedPersonName;

    /**
     * 建档时间
     */
    @Column(name = "CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


    /**
     * 患者登记时间
     */
    @Column(name = "EFFECTIVE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effectiveTime;

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * 城乡居民健康档案编号
     */
    @Column(name = "HEALTH_RECORD_NO")
    private String healthRecordNo;

    /**
     * 建档医疗机构组织机构代码
     */
    @Column(name = "HEALTH_RECORD_ORG_CODE")
    private String healthRecordOrgCode;

    /**
     * 建档医疗机构组织机构名称
     */
    @Transient
    private String healthRecordOrgName;

    /**
     * 病人ID
     */
    @Column(name = "PATIENT_ID")
    private String patientId;

    /**
     * 消息ID
     */
    @Column(name = "MSG_ID")
    private String msgId;

    /**
     * 消息ID
     */
    @Column(name = "STATUS_CODE")
    private String statusCode;

    /**
     * 状态代码
     *
     * @return
     */
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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

    public String getSexCode() {
        return this.sexCode;
    }


    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }


    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getIdTypeCode() {
        return this.idTypeCode;
    }


    public void setIdTypeCode(Integer idTypeCode) {
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

    public String getStateCode() {
        return this.stateCode;
    }


    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCityCode() {
        return this.cityCode;
    }


    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return this.areaCode;
    }


    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getStreetCode() {
        return this.streetCode;
    }


    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getCommitteeCode() {
        return this.committeeCode;
    }


    public void setCommitteeCode(String committeeCode) {
        this.committeeCode = committeeCode;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }


    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return this.postalCode;
    }


    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNationalityCode() {
        return this.nationalityCode;
    }


    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public Integer getAboCode() {
        return this.aboCode;
    }


    public void setAboCode(Integer aboCode) {
        this.aboCode = aboCode;
    }

    public Integer getRhCode() {
        return this.rhCode;
    }


    public void setRhCode(Integer rhCode) {
        this.rhCode = rhCode;
    }

    public Integer getEducationCode() {
        return this.educationCode;
    }


    public void setEducationCode(Integer educationCode) {
        this.educationCode = educationCode;
    }

    public String getOccupationCode() {
        return this.occupationCode;
    }


    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getMarriageCode() {
        return this.marriageCode;
    }


    public void setMarriageCode(String marriageCode) {
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

    public String getAssignedPersonCode() {
        return this.assignedPersonCode;
    }


    public void setAssignedPersonCode(String assignedPersonCode) {
        this.assignedPersonCode = assignedPersonCode;
    }

    public String getAssignedPersonName() {
        return this.assignedPersonName;
    }


    public void setAssignedPersonName(String assignedPersonName) {
        this.assignedPersonName = assignedPersonName;
    }

    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHealthRecordNo() {
        return this.healthRecordNo;
    }


    public void setHealthRecordNo(String healthRecordNo) {
        this.healthRecordNo = healthRecordNo;
    }

    public String getHealthRecordOrgCode() {
        return this.healthRecordOrgCode;
    }


    public void setHealthRecordOrgCode(String healthRecordOrgCode) {
        this.healthRecordOrgCode = healthRecordOrgCode;
    }

    public String getHealthRecordOrgName() {
        return healthRecordOrgName;
    }

    public void setHealthRecordOrgName(String healthRecordOrgName) {
        this.healthRecordOrgName = healthRecordOrgName;
    }

    public String getPatientId() {
        return this.patientId;
    }


    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMsgId() {
        return this.msgId;
    }


    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getSexCodeName() {
        return sexCodeName;
    }

    public void setSexCodeName(String sexCodeName) {
        this.sexCodeName = sexCodeName;
    }

    public String getMarriageName() {
        return marriageName;
    }

    public void setMarriageName(String marriageName) {
        this.marriageName = marriageName;
    }

    public String getEmployerTelNo() {
        return employerTelNo;
    }

    public void setEmployerTelNo(String employerTelNo) {
        this.employerTelNo = employerTelNo;
    }

    /**
     * 省
     */
    @Transient
    private String stateName;

    /**
     * 城市
     */
    @Transient
    private String cityName;

    /**
     * 县
     */
    @Transient
    private String areaName;

    /**
     * 地址-乡（镇、街道办事处）
     */
    @Transient
    private String streetName;

    /**
     * 地址-村（街、路、弄等）
     */
    @Transient
    private String committeeName;


    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    //region 健康卡
    @Transient
    private XmanCard xmanCard;

    public XmanCard getXmanCard() {
        return xmanCard;
    }

    public void setXmanCard(XmanCard xmanCard) {
        this.xmanCard = xmanCard;
    }

    /**
     * 健康卡号
     */
    @Transient
    private String cardCode;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    /**
     *健康卡发放机构代码
     */
    @Transient
    private String xcOrgCode;

    /**
     *健康卡发放机构名称
     */
    @Transient
    private String xcOrgName;

    public String getXcOrgCode() {
        return xcOrgCode;
    }

    public void setXcOrgCode(String xcOrgCode) {
        this.xcOrgCode = xcOrgCode;
    }

    public String getXcOrgName() {
        return xcOrgName;
    }

    public void setXcOrgName(String xcOrgName) {
        this.xcOrgName = xcOrgName;
    }

    //endregion
}
