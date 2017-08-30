package com.lshaci.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * validate Object not null
 * 
 * @author lshaci
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface NotNull {
	
	/**
	 * 验证未通过的提示消息
	 */
	String message() default "字段不能为空";
}
