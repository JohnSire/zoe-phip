
package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.IDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;


public class MobileValidator implements ConstraintValidator<ValidateMobile, CharSequence> {

    private static String pattern = "^((13[0-9])|(15[02789])|(18[679]))\\\\d{8}$";

    private Pattern toPattern = Pattern.compile(
            pattern, CASE_INSENSITIVE
    );

    public void initialize(ValidateMobile annotation) {
    }

    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        return matchPart(value.toString(), toPattern);
    }

    private boolean matchPart(String part, Pattern pattern) {
        try {
            part = IDN.toASCII(part);
        } catch (IllegalArgumentException e) {

            return false;
        }
        Matcher matcher = pattern.matcher(part);
        return matcher.matches();
    }
}
