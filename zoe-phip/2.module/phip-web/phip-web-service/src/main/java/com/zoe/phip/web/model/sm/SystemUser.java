/*
 * Powered By zoe
 * Since 2008 - 2016
 */
package com.zoe.phip.web.model.sm;

import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateLength;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotBlank;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotNull;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidatePattern;
import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
@Table(name = "SYS_SYSTEM_USER")
public class  SystemUser extends MasterEntity {

    /**
     * 名称
     */
    @Column(name = "NAME")
//    @JSONField(name = "Name")
    @ValidateNotBlank(message = "{SystemUser.name.ValidateNotBlank.illegal}")
    @ValidateLength(min = 5, max = 20, message = "{SystemUser.name.ValidateLength.illegal}")
    @ValidatePattern(regexp = "[a-zA-Z]{5,20}",message = "{SystemUser.name.ValidatePattern.illegal}")
    @ValidateNotNull(message = "{SystemUser.name.ValidateNotNull.illegal}")
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
   // @MyPassword(message = "{SystemUser.password.MyPassword.illegal}")
    private String password;
/*
   @ValidateMax(message = "{SystemUser.password.ValidateMax.illegal}", value = 1)
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }*/

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
