package com.zoe.phip.infrastructure.myvalidator.annotation;


import com.zoe.phip.infrastructure.myvalidator.actualize.IDCardValidator;

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
@Constraint(validatedBy = {IDCardValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@ReportAsSingleViolation
@ValidatePattern(regexp = "")
/**
 *
 * @author by hyf
 * @version 1.0
 * @date 2016/5/12
 * bean验证：被注释的元素必须是符合身份证号码规则
 */
public @interface ValidateIDCard {
    String message() default "身份证号码不符合规则";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    @OverridesAttribute(constraint = ValidatePattern.class, name = "regexp") String regexp() default ".*";


    @OverridesAttribute(constraint = ValidatePattern.class, name = "flags") ValidatePattern.Flag[] flags() default {};


    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        ValidateIDCard[] value();
    }
}
