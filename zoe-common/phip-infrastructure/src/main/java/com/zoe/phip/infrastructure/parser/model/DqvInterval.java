package com.zoe.phip.infrastructure.parser.model;

public class DqvInterval extends AbstractDataQualityValue {
    private java.math.BigDecimal fromValue = new java.math.BigDecimal(0);
    private java.math.BigDecimal toValue = new java.math.BigDecimal(0);

    public DqvInterval() {
        super(DqvType.Interval);
    }

    public final java.math.BigDecimal getFromValue() {
        return fromValue;
    }

    public final void setFromValue(java.math.BigDecimal value) {
        fromValue = value;
    }

    public final java.math.BigDecimal getToValue() {
        return toValue;
    }

    public final void setToValue(java.math.BigDecimal value) {
        toValue = value;
    }


}