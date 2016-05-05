package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateMax;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.BigInteger;


public class MaxValidatorForNumber implements ConstraintValidator<ValidateMax, Number> {

    private long maxValue;

    public void initialize(ValidateMax maxValue) {
        this.maxValue = maxValue.value();
    }

    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).compareTo(BigDecimal.valueOf(maxValue)) != 1;
        } else if (value instanceof BigInteger) {
            return ((BigInteger) value).compareTo(BigInteger.valueOf(maxValue)) != 1;
        } else {
            long longValue = value.longValue();
            return longValue <= maxValue;
        }
    }
}
