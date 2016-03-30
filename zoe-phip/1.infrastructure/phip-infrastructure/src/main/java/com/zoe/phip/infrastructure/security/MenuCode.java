package com.zoe.phip.infrastructure.security;

/**
 * Created by zengjiyang on 2016/3/29.
 */
public enum MenuCode {
    SystemManager("SystemManager"),
    SystemUser("SystemUser"),
    SystemMenu("SystemMenu"),
    SystemParameter("SystemParameter"),
    SystemDict("SystemDict");

    String code;

    MenuCode(String code){
        this.code=code;
    }

    public static MenuCode forValue(String value) {
        for (MenuCode type : values()) {
            if (type.getCode() == value)
                return type;
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }
}
