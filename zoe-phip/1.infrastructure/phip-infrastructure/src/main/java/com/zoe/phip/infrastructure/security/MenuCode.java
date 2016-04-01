package com.zoe.phip.infrastructure.security;

/**
 * Created by zengjiyang on 2016/3/29.
 */
public enum MenuCode {
    SystemManager("SystemManager","系统管理"),
    SystemUser("SystemUser","用户管理"),
    SystemMenu("SystemMenu","菜单管理"),
    SystemParameter("SystemParameter","参数管理"),
    SystemDict("SystemDict","系统字典");

    String code;
    String name;

    MenuCode(String code,String name)
    {
        this.code=code;
        this.name=name;
    }

    public static MenuCode forValue(String value) {
        for (MenuCode type : values()) {
            if (type.getCode().equals(value))
                return type;
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
