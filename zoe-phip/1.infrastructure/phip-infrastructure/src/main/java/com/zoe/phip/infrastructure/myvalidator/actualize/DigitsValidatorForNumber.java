
package com.zoe.phip.infrastructure.myvalidator.actualize;

import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateDigits;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class DigitsValidatorForNumber implements ConstraintValidator<ValidateDigits, Number> {

	private static final Log log = LoggerFactory.make();
	
	private int maxIntegerLength;
	private int maxFractionLength;

	public void initialize(ValidateDigits constraintAnnotation) {
		this.maxIntegerLength = constraintAnnotation.integer();
		this.maxFractionLength = constraintAnnotation.fraction();
		validateParameters();
	}

	public boolean isValid(Number num, ConstraintValidatorContext constraintValidatorContext) {
		//null values are valid
		if ( num == null ) {
			return true;
		}

		BigDecimal bigNum;
		if ( num instanceof BigDecimal ) {
			bigNum = ( BigDecimal ) num;
		}
		else {
			bigNum = new BigDecimal( num.toString() ).stripTrailingZeros();
		}

		int integerPartLength = bigNum.precision() - bigNum.scale();
		int fractionPartLength = bigNum.scale() < 0 ? 0 : bigNum.scale();

		return ( maxIntegerLength >= integerPartLength && maxFractionLength >= fractionPartLength );
	}

	private void validateParameters() {
		if ( maxIntegerLength < 0 ) {
			throw log.getInvalidLengthForIntegerPartException();
		}
		if ( maxFractionLength < 0 ) {
			throw log.getInvalidLengthForFractionPartException();
		}
	}
}
