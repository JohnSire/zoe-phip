
package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateMin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.BigInteger;


public class MinValidatorForNumber implements ConstraintValidator<ValidateMin, Number> {

	private long minValue;

	public void initialize(ValidateMin minValue) {
		this.minValue = minValue.value();
	}

	public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
		if ( value == null ) {
			return true;
		}
		if ( value instanceof BigDecimal ) {
			return ( ( BigDecimal ) value ).compareTo( BigDecimal.valueOf( minValue ) ) != -1;
		}
		else if ( value instanceof BigInteger ) {
			return ( ( BigInteger ) value ).compareTo( BigInteger.valueOf( minValue ) ) != -1;
		}
		else {
			long longValue = value.longValue();
			return longValue >= minValue;
		}
	}
}
