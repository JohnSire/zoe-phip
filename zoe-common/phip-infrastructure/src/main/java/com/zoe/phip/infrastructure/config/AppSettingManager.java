package com.zoe.phip.infrastructure.config;


import com.zoe.phip.infrastructure.util.PropertyUtil;
import com.zoe.phip.infrastructure.util.StringUtil;

import java.util.Properties;

/**
 * Created by qiuyungen on 2016/2/23.
 */
public final class AppSettingManager {

    private final static Properties AppProperties = PropertyUtil.getProperties("app");

    public static String Query(String value) {
        String[] values = value.split("\\.");
        return Query(values[0], values[1], null);
    }

    public static String Query(String key, String name) {
        return Query(key, name, null);
    }

    public static String Query(String key, String name, String defaultValue) {
        String value = AppProperties.getProperty(String.format("%s.%s", key, name));
        if (StringUtil.isNullOrWhiteSpace(value))
            return defaultValue;
        return value;
    }
}