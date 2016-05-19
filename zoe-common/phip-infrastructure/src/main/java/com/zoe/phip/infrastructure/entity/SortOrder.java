package com.zoe.phip.infrastructure.entity;

/**
 * Created by zengjiyang on 2016/3/31.
 */
public enum SortOrder {
    DESC,
    ASC;

    public static SortOrder forValue(String value) {
        for (SortOrder type : values()) {
            if (type.toString().equalsIgnoreCase(value))
                return type;
        }
        return null;
    }
}
