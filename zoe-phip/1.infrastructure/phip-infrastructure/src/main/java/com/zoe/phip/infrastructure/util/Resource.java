package com.zoe.phip.infrastructure.util;

/**
 * Created by qiuyungen on 2016/2/23.
 */
public final class Resource {
    private final String value;
    private final String key;

    public Resource(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public byte[] getBytes() {
        return value.getBytes();
    }
}
