package com.zoe.phip.infrastructure.myvalidator.annotation;


import com.zoe.phip.infrastructure.myvalidator.actualize.ScriptAssertValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ScriptAssertValidator.class})
@Documented
/**
 *
 * @author by hyf
 * @version 1.0
 * @date 2016/4/14
 * bean验证：被注释的元素必须为 false
 */
public @interface ValidateScriptAssert {

	String message() default "{org.hibernate.validator.constraints.ScriptAssert.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };


	String lang();


	String script();


	String alias() default "_this";


	@Target({ TYPE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		ValidateScriptAssert[] value();
	}
}
