package com.zoe.phip.infrastructure.myvalidator.annotation;


import com.zoe.phip.infrastructure.myvalidator.actualize.PatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.PARAMETER})

@Retention(RetentionPolicy.RUNTIME)

@Documented

@Constraint(validatedBy = {PatternValidator.class})
/**
 *
 * @author by hyf
 * @version 1.0
 * @date 2016/4/14
 * bean验证：被注释的元素必须符合指定的正则表达式
 */
public @interface ValidatePattern {
    String regexp();


    Flag[] flags() default {};


    String message() default "{javax.validation.constraints.Pattern.message}";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

    @Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.PARAMETER})

    @Retention(RetentionPolicy.RUNTIME)

    @Documented
    public static @interface List {
        ValidatePattern[] value();

    }


    public static enum Flag {
        UNIX_LINES(1), CASE_INSENSITIVE(2), COMMENTS(4), MULTILINE(8), DOTALL(32), UNICODE_CASE(64), CANON_EQ(128);

        private final int value;


        private Flag(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}



