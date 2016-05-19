package com.zoe.phip.infrastructure.parser.model;

/**
 * Created by zhangwenbin on 2016/3/2.
 */
public class DqvNumber extends AbstractDataQualityValue {

    private FixedType fixedType;
    private String value;

    public DqvNumber() {
        super(DqvType.Number);
    }

    public final FixedType getFixedType() {
        return fixedType;
    }

    public final void setFixedType(FixedType value) {
        fixedType = value;
    }

    public final String getValue() {
        return value;
    }

    public final void setValue(String value) {
        this.value = value;
    }


}
