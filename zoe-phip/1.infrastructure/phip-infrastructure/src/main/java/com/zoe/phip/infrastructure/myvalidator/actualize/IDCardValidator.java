
package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateIDCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.IDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;


public class IDCardValidator implements ConstraintValidator<ValidateIDCard, CharSequence> {

    private static String pattern = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";  //15或者18


    private Pattern toPattern = Pattern.compile(
            pattern, CASE_INSENSITIVE
    );

    public void initialize(ValidateIDCard annotation) {
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
