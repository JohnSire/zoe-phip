package com.zoe.phip.infrastructure.myvalidator.actualize;

import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateDecimalMin;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.BigInteger;


public class DecimalMinValidatorForNumber implements ConstraintValidator<ValidateDecimalMin, Number> {

    private static final Log log = LoggerFactory.make();

    private BigDecimal minValue;
    private boolean inclusive;

    public void initialize(ValidateDecimalMin minValue) {
        try {
            this.minValue = new BigDecimal(minValue.value());
        } catch (NumberFormatException nfe) {
            throw log.getInvalidBigDecimalFormatException(minValue.value(), nfe);
        }
        this.inclusive = minValue.inclusive();
    }

    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {

        //null values are valid
        if (value == null) {
            return true;
        }

        int comparisonResult;
        if (value instanceof BigDecimal) {
            comparisonResult = ((BigDecimal) value).compareTo(minValue);
        } else if (value instanceof BigInteger) {
            comparisonResult = (new BigDecimal((BigInteger) value)).compareTo(minValue);
        }
        if (value instanceof Long) {
            comparisonResult = (BigDecimal.valueOf(value.longValue()).compareTo(minValue));
        } else {
            comparisonResult = (BigDecimal.valueOf(value.doubleValue()).compareTo(minValue));
        }
        return inclusive ? comparisonResult >= 0 : comparisonResult > 0;
    }
}
