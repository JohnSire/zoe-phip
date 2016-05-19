package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NotNullValidator implements ConstraintValidator<ValidateNotNull, Object> {

    public void initialize(ValidateNotNull parameters) {
    }

    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {

        return object != null;
    }
}
