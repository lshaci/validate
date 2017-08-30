package com.lshaci.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * validate Integer min and max value
 * 
 * @author lshaci
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Between {
	
	/**
	 * 是否需要进行验证
	 */
	boolean require() default true;
	
	/**
	 * 数字最小值
	 */
	int min();
	
	/**
	 * 数字最大值
	 */
	int max();
	
	/**
	 * 验证未通过的提示消息
	 */
	String message() default "数字大小不在规定范围之内";

}
