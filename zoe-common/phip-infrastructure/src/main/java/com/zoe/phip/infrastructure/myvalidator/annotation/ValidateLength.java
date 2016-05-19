package com.zoe.phip.infrastructure.myvalidator.annotation;


import com.zoe.phip.infrastructure.myvalidator.actualize.LengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Constraint(validatedBy = {LengthValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
/**
 *
 * @author by hyf
 * @version 1.0
 * @date 2016/4/14
 * bean验证：被注释的字符串的大小必须在指定的范围内
 */
public @interface ValidateLength {
    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String message() default "{org.hibernate.validator.constraints.Length.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        ValidateLength[] value();
    }
}
