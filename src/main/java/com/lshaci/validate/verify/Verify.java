package com.lshaci.validate.verify;

import java.lang.reflect.Field;

import com.lshaci.validate.model.ValidateMessage;

/**
 * 验证统一接口
 * 
 * @author lshaci
 */
public interface Verify {
	
	/**
	 * 验证成功日志信息
	 */
	String SUCCESS = "This field({}) validate successed!";
	/**
	 * 值为null日志信息
	 */
	String ISNULL = "This field({}) is null!";
	
	/**
	 * 值为null但是不需要验证日志信息
	 */
	String NOTNEED = "This field({}) is null, but not need validate!";
	
	/**
	 * 对一个对象的字段进行验证
	 * 
	 * @param field		需要验证的字段
	 * @param value		改字段的值
	 * 
	 * @return	返回验证的结果信息
	 */
	ValidateMessage validate(Field field, Object value);

}
