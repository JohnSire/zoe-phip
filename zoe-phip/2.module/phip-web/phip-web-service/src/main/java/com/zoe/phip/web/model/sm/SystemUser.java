/*
 * Powered By zoe
 * Since 2008 - 2016
 */
package com.zoe.phip.web.model.sm;

import com.zoe.phip.module.infrastructure.entity.MasterEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
@Table(name = "SYS_SYSTEM_USER")
public class SystemUser extends MasterEntity {

    /**
     * 名称
     */
    @Column(name = "NAME")
//    @JSONField(name = "Name")
    private String name;

    /**
     * 登陆名
     */
    @Column(name = "LOGIN_NAME")
//    @JSONField(name = "LoginName")
    private String loginName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
//    @JSONField(name = "Password")
    private String password;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(String competenceId) {
        this.competenceId = competenceId;
    }

    /// <summary>
    /// 对应的权限标识
    /// </summary>
    @Transient
    @Column(name = "COMPETENCE_ID")
    public String competenceId;
}
