package com.zoe.phip.infrastructure.security;

/**
 * 权限枚举
 * Created by zengjiyang on 2016/3/29.
 */
public enum Permission {

    View(0),
    Query(1),
    Add(2),
    Update(3),
    Delete(4);

    int code;

    Permission(int code) {
        this.code = code;
    }

    public static Permission forValue(int value) {
        for (Permission type : values()) {
            if (type.getCode() == value)
                return type;
        }
        return null;
    }

    public int getCode() {
        return this.code;
    }
}
