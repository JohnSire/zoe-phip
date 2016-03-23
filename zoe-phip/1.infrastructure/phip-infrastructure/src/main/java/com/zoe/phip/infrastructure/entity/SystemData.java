package com.zoe.phip.infrastructure.entity;

import java.io.Serializable;

/**
 * Created by zengjiyang on 2016/3/22.
 */
public final class SystemData implements Serializable {

    /**
     * 用户标识
     */
    public String userId;

    /**
     * 菜单标识
     */
    public int menuCode;

    /**
     * 登陆凭据
     */
    public String credential;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
