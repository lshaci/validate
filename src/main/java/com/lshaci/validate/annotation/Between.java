package com.lshaci.validate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * validate int value min and max
 * 
 * @author lshaci
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Between {
	
	public static final String MIN_DEFAULT = "3";
	public static final String MAX_DEFAULT = "10";
	
	/**
	 * 是否需要进行验证
	 */
	boolean require() default true;
	
	/**
	 * 数字最小值
	 */
	String min() default MIN_DEFAULT;
	
	/**
	 * 数字最大值
	 */
	String max() default MAX_DEFAULT;
	
	/**
	 * 验证未通过的提示消息
	 */
	String message() default "数字大小不在规定范围之内";

}
