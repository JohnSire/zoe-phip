package com.zoe.phip.infrastructure.parser.model;

/**
 * Created by zhangwenbin on 2016/3/2.
 */
public class DqvSql extends AbstractDataQualityValue {

    private FixedType FixedType;
    private String Value;

    public DqvSql() {
        super(DqvType.Sql);
    }

    public final FixedType getFixedType() {
        return FixedType;
    }

    public final void setFixedType(FixedType value) {
        FixedType = value;
    }

    public final String getValue() {
        return Value;
    }

    public final void setValue(String value) {
        Value = value;
    }
}