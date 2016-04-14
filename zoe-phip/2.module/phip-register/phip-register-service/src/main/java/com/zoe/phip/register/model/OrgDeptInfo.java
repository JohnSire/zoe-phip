/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.*;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_ORG_DEPT_INFO")
public class OrgDeptInfo extends MasterEntity {
    /**
     * 医疗卫生机构（科室）标识
     */
    @Column(name = "DEPT_CODE")
    private String deptCode;
    /**
     * 医疗卫生机构（科室）实体名称
     */
    @Column(name = "DEPT_NAME")
    private String deptName;
    /**
     * 医疗卫生机构（科室）类别代码
     */
    @Column(name = "DEPT_TYPE_CODE")
    private String deptTypeCode;
    /**
     * 医疗卫生机构（科室）类别名称
     */
    @Column(name = "DEPT_TYPE_NAME")
    private String deptTypeName;
    /**
     * 医疗卫生机构（科室）角色名称
     */
    @Column(name = "DEPT_ROLE_NAME")
    private String deptRoleName;
    /**
     * 上级机构（科室）号标识
     */
    @Column(name = "DEPT_PARENT_CODE")
    private String deptParentCode;
    /**
     * 上级科室名称
     */
    @Column(name = "DEPT_PARENT_NAME")
    private String deptParentName;
    /**
     * 角色有效期间(起始日期)
     */
    @Column(name = "EFFECTIVE_TIME_LOW")
    private Date effectiveTimeLow;
    /**
     * 角色有效期间(截止日期)
     */
    @Column(name = "EFFECTIVE_TIME_HIGH")
    private Date effectiveTimeHigh;
    /**
     * 工作地址
     */
    @Column(name = "EMPLOYER_ADDR")
    private String employerAddr;
    /**
     * 工作联系电话
     */
    @Column(name = "EMPLOYER_TEL_NO")
    private String employerTelNo;

    /**
     * 消息id
     */
    @Column(name = "MSG_ID")
    private String msgId;
    /**
     * 申请者代码
     */
    @Column(name = "ASSIGNED_CODE")
    private String assignedCode;
    /**
     * 申请者名称
     */
    @Column(name = "ASSIGNED_NAME")
    private String assignedName;
    /**
     * 申请者科室代码
     */
    @Column(name = "ASSIGNED_DEPT_CODE")
    private String assignedDeptCode;
    /**
     * 申请者科室名称
     */
    @Column(name = "ASSIGNED_DEPT_NAME")
    private String assignedDeptName;
    /**
     * 申请联系人
     */
    @Column(name = "ASSIGNED_ONTACT_PERSON")
    private String assignedOntactPerson;
    /**
     * 机构编码
     */
    @Column(name = "ORG_CODE")
    private String orgCode;
    /**
     * 排序值  越小排在越前
     */
    @Column(name = "SORT_NUM")
    private int sortNum;

    public String getDeptCode() {
        return this.deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptTypeCode() {
        return this.deptTypeCode;
    }

    public void setDeptTypeCode(String deptTypeCode) {
        this.deptTypeCode = deptTypeCode;
    }

    public String getDeptTypeName() {
        return this.deptTypeName;
    }

    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }

    public String getDeptRoleName() {
        return this.deptRoleName;
    }

    public void setDeptRoleName(String deptRoleName) {
        this.deptRoleName = deptRoleName;
    }

    public String getDeptParentCode() {
        return this.deptParentCode;
    }

    public void setDeptParentCode(String deptParentCode) {
        this.deptParentCode = deptParentCode;
    }

    public String getDeptParentName() {
        return this.deptParentName;
    }

    public void setDeptParentName(String deptParentName) {
        this.deptParentName = deptParentName;
    }

    public Date getEffectiveTimeLow() {
        return this.effectiveTimeLow;
    }

    public void setEffectiveTimeLow(Date effectiveTimeLow) {
        this.effectiveTimeLow = effectiveTimeLow;
    }

    public Date getEffectiveTimeHigh() {
        return this.effectiveTimeHigh;
    }

    public void setEffectiveTimeHigh(Date effectiveTimeHigh) {
        this.effectiveTimeHigh = effectiveTimeHigh;
    }

    public String getEmployerAddr() {
        return this.employerAddr;
    }

    public void setEmployerAddr(String employerAddr) {
        this.employerAddr = employerAddr;
    }

    public String getEmployerTelNo() {
        return this.employerTelNo;
    }

    public void setEmployerTelNo(String employerTelNo) {
        this.employerTelNo = employerTelNo;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getAssignedCode() {
        return this.assignedCode;
    }

    public void setAssignedCode(String assignedCode) {
        this.assignedCode = assignedCode;
    }

    public String getAssignedName() {
        return this.assignedName;
    }

    public void setAssignedName(String assignedName) {
        this.assignedName = assignedName;
    }

    public String getAssignedDeptCode() {
        return this.assignedDeptCode;
    }

    public void setAssignedDeptCode(String assignedDeptCode) {
        this.assignedDeptCode = assignedDeptCode;
    }

    public String getAssignedDeptName() {
        return this.assignedDeptName;
    }

    public void setAssignedDeptName(String assignedDeptName) {
        this.assignedDeptName = assignedDeptName;
    }

    public String getAssignedOntactPerson() {
        return this.assignedOntactPerson;
    }

    public void setAssignedOntactPerson(String assignedOntactPerson) {
        this.assignedOntactPerson = assignedOntactPerson;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public int getSortNum() {
        return this.sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }
}
