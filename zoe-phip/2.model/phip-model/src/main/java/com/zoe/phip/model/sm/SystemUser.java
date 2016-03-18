/*
 * Powered By zoe
 * Since 2008 - 2016
 */
package com.zoe.phip.model.sm;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.*;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
@Table(name = "SYS_SYSTEM_USER")
public class SystemUser extends BaseEntity {

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 登陆名
     */
    @Column(name = "LOGIN_NAME")
    private String loginName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 状态
     */
    @Column(name = "STATE")
    private int state;

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

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

}