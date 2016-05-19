
package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateAssertTrue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AssertTrueValidator implements ConstraintValidator<ValidateAssertTrue, Boolean> {

    public void initialize(ValidateAssertTrue constraintAnnotation) {
    }

    public boolean isValid(Boolean bool, ConstraintValidatorContext constraintValidatorContext) {
        //null values are valid
        return bool == null || bool;
    }

}
