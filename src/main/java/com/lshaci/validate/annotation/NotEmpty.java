package com.lshaci.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * validate Object not empty
 * 
 * @author lshaci
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface NotEmpty {
	
	/**
	 * 验证未通过的提示消息
	 */
	String message() default "字段内容不能为空";

}
