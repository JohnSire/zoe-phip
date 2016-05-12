package com.zoe.phip.infrastructure.myvalidator.annotation;


import com.zoe.phip.infrastructure.myvalidator.actualize.EmailValidator;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Constraint(validatedBy = {EmailValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@ReportAsSingleViolation
@ValidatePattern(regexp = "")
/**
 *
 * @author by hyf
 * @version 1.0
 * @date 2016/5/12
 * bean验证：被注释的元素必须是电子邮箱地址
 */
public @interface ValidateEmail {
    String message() default "{org.hibernate.validator.constraints.Email.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    @OverridesAttribute(constraint = ValidatePattern.class, name = "regexp") String regexp() default ".*";


    @OverridesAttribute(constraint = ValidatePattern.class, name = "flags") ValidatePattern.Flag[] flags() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        ValidateEmail[] value();
    }
}
