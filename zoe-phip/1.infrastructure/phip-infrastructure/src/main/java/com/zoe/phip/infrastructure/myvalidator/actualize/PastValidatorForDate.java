package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidatePast;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;


public class PastValidatorForDate implements ConstraintValidator<ValidatePast, Date> {

	public void initialize(ValidatePast constraintAnnotation) {
	}

	public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
		//null values are valid
		if ( date == null ) {
			return true;
		}
		return date.before( new Date() );
	}
}
