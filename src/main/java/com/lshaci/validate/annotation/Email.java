package com.lshaci.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * validate String is email
 * 
 * @author lshaci
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Email {
	
	/**
	 * 是否需要进行验证
	 */
	boolean require() default true;
	
	/**
	 * 验证未通过的提示消息
	 */
	String message() default "字段不是email格式";

}
