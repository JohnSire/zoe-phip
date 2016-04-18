/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;
import com.zoe.phip.infrastructure.annotation.XPath;

import javax.persistence.*;
import javax.swing.plaf.synth.Region;
import javax.swing.text.EditorKit;
import java.util.Date;
import java.util.List;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_XMAN_BASE_INFO")
public class XmanBaseInfo extends MasterEntity {

//    private String id;


    /**
     * 注册机构编码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/providerOrganization/id/@extension", descr = "机构编码")
    @Column(name = "ORG_CODE")
    private String orgCode;

    /**
     * 注册机构名称
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/providerOrganization/name", descr = "机构名称")

    @Column(name = "ORG_NAME")
    private String orgName;

    /**
     * 身份证件号码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/id/@extension", descr = "身份证件号码")
    @Column(name = "ID_NO")
    private String idNo;

    /**
     * 本人姓名
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/name", descr = "本人姓名")
    @Column(name = "NAME")
    private String name;

    /**
     * 性别代码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/administrativeGenderCode/@code", descr = "性别代码")
    @Column(name = "SEX_CODE")
    private int sexCode;

    /**
     * 性别名称
     */
    @Transient
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/administrativeGenderCode/@displayName", descr = "性别名称")
    @Column(name = "SEX_CODE_NAME")
    private String sexCodeName;



    /**
     * 出生日期
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/birthTime/@value", descr = "出生日期")
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    /**
     * 身份证件类别代码
     */
    @XPath(defaultValue = "01")
    @Column(name = "ID_TYPE_CODE")
    private int idTypeCode;

    /**
     * 工作单位名称
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/asEmployee/employerOrganization/name", descr = "工作单位名称")
    @Column(name = "EMPLOYER_NAME")
    private String employerName;



    /**
     * 工作单位联系方式
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/asEmployee/employerOrganization/contactParty/telecom", descr = "工作单位联系方式")
    @Column(name = "EMPLOYER_TEL_NO")
    private String employerTelNo;


    /**
     * 联系电话
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/telecom/@value", descr = "本人电话号码")
    @Column(name = "TEL_NO")
    private String telNo;

    /**
     * 联系人姓�
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/personalRelationship/relationshipHolder1/name", descr = "联系人姓")
    @Column(name = "REL_NAME")
    private String relName;

    /**
     * 联系人电�
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/personalRelationship/telecom/@value", descr = "联系人电")
    @Column(name = "REL_TEL_NO")
    private String relTelNo;

    /**
     * 常住地址户籍
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 地址
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/addr/streetAddressLine", descr = "常住地址户籍")
    @Column(name = "STATE_CODE")
    private String stateCode;

    /**
     * 地址
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/addr/city", descr = "地址")
    @Column(name = "CITY_CODE")
    private String cityCode;

    /**
     * 地址
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/addr/county", descr = "地址")
    @Column(name = "AREA_CODE")
    private String areaCode;
    /**
     * 地址
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/addr/streetNameBase", descr = "地址")
    @Column(name = "STREET_CODE")
    private String streetCode;
    /**
     *
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/addr/streetName", descr = "地址")
    @Column(name = "COMMITTEE_CODE")
    private String committeeCode;

    /**
     * 门牌�
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/addr/houseNumber", descr = "门牌")
    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;

    /**
     * 邮政编码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/addr/postalCode", descr = "邮政编码")
    @Column(name = "POSTALCODE")
    private String postalcode;

    /**
     * 民族代码
     */
    @XPath(value = "/controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/ethnicGroupCode/@code")
    @Column(name = "NATIONALITY_CODE")
    private int nationalityCode;

    /**
     * 民族名称
     */
    @XPath(value = "/controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/ethnicGroupCode/@displayName")
    @Column(name = "NATIONALITY_NAME")
    private String nationalityName;


    /**
     * abo血型代�
     */
    @XPath(descr = "5")
    @Column(name = "ABO_CODE")
    private int aboCode;

    /**
     * rh血型代�
     */
    @XPath(descr = "3")
    @Column(name = "RH_CODE")
    private int rhCode;

    /**
     * 学历代码
     */
    @XPath(defaultValue = "90")
    @Column(name = "EDUCATION_CODE")
    private int educationCode;

    /**
     * 职业类别代码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/asEmployee/occupationCode", descr = "职业类别代码")
    @Column(name = "OCCUPATION_CODE")
    private String occupationCode;

    /**
     * 婚姻状况代码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/maritalStatusCode/@code", descr = "婚姻状况代码")
    @Column(name = "MARRIAGE_CODE")
    private int marriageCode;

    /**
     * 婚姻状况名称
     */
    @Transient
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/maritalStatusCode/@displayName", descr = "婚姻状况名称")
    @Column(name = "MARRIAGE_Name")
    private String marriageName;



    /**
     * 医疗保险类别代码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/coveredPartyOf/coverageRecord/beneficiary/beneficiary/code/@code", descr = "医疗保险类别代码")
    @Column(name = "CODE_SYS_CODE")
    private String codeSysCode;

    /**
     * 医疗保险类别名称
     */
    @Transient
    @Column(name = "CODE_SYS_NAME")
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/coveredPartyOf/coverageRecord/beneficiary/beneficiary/code/@displayName", descr = "医疗保险类别名称")
    private String codeSysName;

    /**
     * 作者职工代�
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/id/@extension", descr = "作者职工代")
    @Column(name = "ASSIGNED_PERSON_CODE")
    private String assignedPersonCode;

    /**
     * 作者人姓名
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/author/assignedEntity/assignedPerson/name", descr = "作者职工名")
    @Column(name = "ASSIGNED_PERSON_NAME")
    private String assignedPersonName;

    /**
     * 建档时间
     */
    @XPath(value = "//creationTime/@value")
    @Column(name = "CREATE_TIME")
    private Date createTime;


    /**
     * 登记日期
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/effectiveTime/@value", descr = "登记时间")
    @Column(name = "REQISTER_DATE")
    private Date reqisterDate;

    public Date getReqisterDate() {
        return reqisterDate;
    }

    public void setReqisterDate(Date reqisterDate) {
        this.reqisterDate = reqisterDate;
    }

    /**
     * 城乡居民健康档案编号
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/asOtherIDs/id[@root='2.16.156.10011.1.2']/@extension", descr = "城乡居民健康档案编号")
    @Column(name = "HEALTH_RECORD_NO")
    private String healthRecordNo;

    /**
     * 建档机构代码
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/patientPerson/asOtherIDs/scopingOrganization/id/@extension")
    @Column(name = "HEALTH_RECORD_ORG_CODE")
    private String healthRecordOrgCode;

    /**
     * 病人ID
     */
    @XPath(value = "//controlActProcess/subject/registrationRequest/subject1/patient/id/@extension", descr = "病人ID")
    @Column(name = "PATIENT_ID")
    private String patientId;

    /**
     * 消息ID
     */
    @XPath(value = "//id/@extension", descr = "消息ID")
    @Column(name = "MSG_ID")
    private String msgId;



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

    public int getSexCode() {
        return this.sexCode;
    }


    public void setSexCode(int sexCode) {
        this.sexCode = sexCode;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }


    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getIdTypeCode() {
        return this.idTypeCode;
    }


    public void setIdTypeCode(int idTypeCode) {
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

    public String getPostalcode() {
        return this.postalcode;
    }


    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public int getNationalityCode() {
        return this.nationalityCode;
    }


    public void setNationalityCode(int nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public int getAboCode() {
        return this.aboCode;
    }


    public void setAboCode(int aboCode) {
        this.aboCode = aboCode;
    }

    public int getRhCode() {
        return this.rhCode;
    }


    public void setRhCode(int rhCode) {
        this.rhCode = rhCode;
    }

    public int getEducationCode() {
        return this.educationCode;
    }


    public void setEducationCode(int educationCode) {
        this.educationCode = educationCode;
    }

    public String getOccupationCode() {
        return this.occupationCode;
    }


    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public int getMarriageCode() {
        return this.marriageCode;
    }


    public void setMarriageCode(int marriageCode) {
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


}
