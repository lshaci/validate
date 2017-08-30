package com.lshaci.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * validate Object length
 * 
 * @author lshaci
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Length {
	
	/**
	 * 字符串长度最小值
	 */
	int min() default 0;
	
	/**
	 * 字符串长度最大值
	 */
	int max();
	
	/**
	 * 验证未通过的提示消息
	 */
	String message() default "字段长度不符合要求";

}
