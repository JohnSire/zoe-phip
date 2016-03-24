package com.zoe.phip.model.sm;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by zengjiyang on 2016/3/21.
 */
public class LoginCredentials implements Serializable {

    /**
     * 用户标识
     */
    @JSONField(name = "UserId")
    public String userId;

    /**
     * 用户名
     */
    @JSONField(name = "UserName")
    public String userName;

    /**
     * 凭据
     */
    @JSONField(name = "Credential")
    public String credential;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
