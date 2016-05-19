
package com.zoe.phip.infrastructure.myvalidator.actualize;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.IDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;


public class EmailValidator implements ConstraintValidator<ValidateEmail, CharSequence> {
    private static String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static String DOMAIN = ATOM + "+(\\." + ATOM + "+)*";
    private static String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";


    private Pattern localPattern = Pattern.compile(
            ATOM + "+(\\." + ATOM + "+)*", CASE_INSENSITIVE
    );

    private Pattern domainPattern = Pattern.compile(
            DOMAIN + "|" + IP_DOMAIN, CASE_INSENSITIVE
    );

    public void initialize(ValidateEmail annotation) {
    }

    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }


        String[] emailParts = value.toString().split("@");
        if (emailParts.length != 2) {
            return false;
        }


        if (emailParts[0].endsWith(".") || emailParts[1].endsWith(".")) {
            return false;
        }

        if (!matchPart(emailParts[0], localPattern)) {
            return false;
        }

        return matchPart(emailParts[1], domainPattern);
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
