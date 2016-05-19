package com.zoe.phip.infrastructure.myvalidator.annotation;


import com.zoe.phip.infrastructure.myvalidator.actualize.MaxValidatorForNumber;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MaxValidatorForNumber.class})
/**
 *
 * @author by hyf
 * @version 1.0
 * @date 2016/4/14
 * bean验证：被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 */

public @interface ValidateMax {
    String message() default "{javax.validation.constraints.Max.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long value();

    @Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface List {
        ValidateMax[] value();
    }
}



