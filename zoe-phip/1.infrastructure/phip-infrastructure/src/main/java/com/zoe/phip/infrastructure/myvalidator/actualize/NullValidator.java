
package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NullValidator implements ConstraintValidator<ValidateNull, Object> {

	public void initialize(ValidateNull constraintAnnotation) {

	}

	public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
		return object == null;
	}

}
