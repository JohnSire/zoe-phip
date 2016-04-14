package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateFuture;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;


public class FutureValidatorForDate implements ConstraintValidator<ValidateFuture, Date> {

	public void initialize(ValidateFuture constraintAnnotation) {
	}

	public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
		if ( date == null ) {
			return true;
		}
		return date.after( new Date() );
	}
}
