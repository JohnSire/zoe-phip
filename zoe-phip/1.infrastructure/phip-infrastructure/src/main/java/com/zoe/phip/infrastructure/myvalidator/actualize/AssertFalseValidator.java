package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateAssertFalse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class AssertFalseValidator implements ConstraintValidator<ValidateAssertFalse, Boolean> {

    public void initialize(ValidateAssertFalse constraintAnnotation) {
    }

    public boolean isValid(Boolean bool, ConstraintValidatorContext constraintValidatorContext) {
        return bool == null || !bool;
    }

}
