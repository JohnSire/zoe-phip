package com.zoe.phip.infrastructure.parser.model;

/**
 * Created by zhangwenbin on 2016/3/2.
 */
public class AbstractDataQualityValue implements DataQualityValue {
    private DqvType dqvType;
    private String id;

    public AbstractDataQualityValue(DqvType dqvType) {
        this.dqvType = dqvType;
    }

    public DqvType getDqvType() {
        return dqvType;
    }

    public void setDqvType(DqvType dqvType) {
        this.dqvType = dqvType;
    }

    @Override
    public DqvType getType() {
        return this.dqvType;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
