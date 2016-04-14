package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NotBlankValidator implements ConstraintValidator<ValidateNotBlank, CharSequence> {

	public void initialize(ValidateNotBlank annotation) {
	}


	public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
		if ( charSequence == null ) {
			return true;
		}

		return charSequence.toString().trim().length() > 0;
	}
}
