package com.zoe.phip.infrastructure.myvalidator.annotation;

/**
 * Created by huangyinfu on 2016/4/11.
 */


import com.zoe.phip.infrastructure.myvalidator.actualize.MyPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = MyPasswordValidator.class)
@Documented

/**
 *
 * @author by hyf
 * @version 1.0
 * @date 2016/4/14
 * bean验证：扩展验证
 */
public @interface MyPassword {

    String message() default "{密码必须是6~12位数字和字母组合}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}