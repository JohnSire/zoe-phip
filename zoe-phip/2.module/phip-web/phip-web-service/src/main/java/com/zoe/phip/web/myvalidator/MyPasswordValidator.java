package com.zoe.phip.web.myvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangyinfu on 2016/4/11.
 */
public class MyPasswordValidator implements ConstraintValidator<MyPassword, String> {

    //6~12位的数字与字母组合
    private static Pattern pattern = Pattern.compile("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}");



    @Override
    public void initialize(MyPassword myPassword) {

    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if( value==null ){
            return false;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}