package com.zoe.phip.model.sm;

/**
 * Created by zengjiyang on 2016/3/21.
 */
public class LoginCredentials {

    /**
     * 用户标识
     */
    public String userId;

    /**
     * 用户名
     */
    public String UserName;

    /**
     * 凭据
     */
    public String Credential;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCredential() {
        return Credential;
    }

    public void setCredential(String credential) {
        Credential = credential;
    }
}
