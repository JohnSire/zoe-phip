package com.zoe.phip.infrastructure.parser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwenbin on 2016/3/2.
 */
public class ExpectedValue {

    private List<DataQualityValue> Values;

    public ExpectedValue() {
        Values = new ArrayList<DataQualityValue>();
    }

    public List<DataQualityValue> getValues() {
        return Values;
    }

    public void setValues(List<DataQualityValue> values) {
        Values = values;
    }
}
