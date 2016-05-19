package com.zoe.phip.infrastructure.parser.model;

/**
 * Created by zhangwenbin on 2016/3/2.
 */
public enum DqvType {
    /**
     * 区间
     */
    Interval(0),
    /**
     * 固定值
     */
    Fixed(1),
    /**
     * 字典
     */
    Dictionary(2),
    /**
     * 数值
     */
    Number(3),
    /**
     * Sql语句
     */
    Sql(4),
    /**
     * 正则表达式
     */
    Regex(5);

    private int value = 0;

    private DqvType(int value) {
        this.value = value;

    }

    public static DqvType forValue(int value) {
        for (DqvType type : values()) {
            if (type.getValue() == value)
                return type;
        }
        return null;
    }

    public int getValue() {
        return this.ordinal();
    }

}
