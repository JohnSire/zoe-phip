package com.zoe.phip.infrastructure.myvalidator.actualize;

import com.zoe.phip.infrastructure.myvalidator.annotation.ValidatePattern;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;


public class PatternValidator implements ConstraintValidator<ValidatePattern, CharSequence> {

    private static final Log log = LoggerFactory.make();

    private java.util.regex.Pattern pattern;

    public void initialize(ValidatePattern parameters) {
        ValidatePattern.Flag[] flags = parameters.flags();
        int intFlag = 0;
        for (ValidatePattern.Flag flag : flags) {
            intFlag = intFlag | flag.getValue();
        }

        try {
            pattern = java.util.regex.Pattern.compile(parameters.regexp(), intFlag);
        } catch (PatternSyntaxException e) {
            throw log.getInvalidRegularExpressionException(e);
        }
    }

    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
