package com.zoe.phip.infrastructure.myvalidator.actualize;

import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateDecimalMax;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.BigInteger;


public class DecimalMaxValidatorForNumber implements ConstraintValidator<ValidateDecimalMax, Number> {

	private static final Log log = LoggerFactory.make();

	private BigDecimal maxValue;
	private boolean inclusive;

	public void initialize(ValidateDecimalMax maxValue) {
		try {
			this.maxValue = new BigDecimal( maxValue.value() );
		}
		catch ( NumberFormatException nfe ) {
			throw log.getInvalidBigDecimalFormatException( maxValue.value(), nfe );
		}
		this.inclusive = maxValue.inclusive();
	}

	public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
		//null values are valid
		if ( value == null ) {
			return true;
		}

		int comparisonResult;
		if ( value instanceof BigDecimal ) {
			comparisonResult = ( (BigDecimal) value ).compareTo( maxValue );
		}
		else if ( value instanceof BigInteger ) {
			comparisonResult = ( new BigDecimal( (BigInteger) value ) ).compareTo( maxValue );
		}
		else if ( value instanceof Long ) {
			comparisonResult = ( BigDecimal.valueOf( value.longValue() ).compareTo( maxValue ) );
		}
		else {
			comparisonResult = ( BigDecimal.valueOf( value.doubleValue() ).compareTo( maxValue ) );
		}
		return inclusive ? comparisonResult <= 0 : comparisonResult < 0;
	}
}
