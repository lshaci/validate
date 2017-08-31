package com.lshaci.validate.verify;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.Between;
import com.lshaci.validate.model.ValidateMessage;
import com.lshaci.validate.utils.ParamUtils;

/**
 * int数值范围验证
 * 
 * @author lshaci
 */
public class BetweenVerify implements Verify {
	
	private static final Logger logger = LoggerFactory.getLogger(BetweenVerify.class);
	
	private static final String verify = Between.class.getSimpleName();
	
	private static Map<String, Integer> cache = new HashMap<>();

	@Override
	public ValidateMessage validate(Field field, Object value) {
		Between between = field.getAnnotation(Between.class);
		if (value == null) {
			if (between.require()) {
				logger.error(ISNULL, field.getName());
				return new ValidateMessage(verify, field.getName(), between.message());
			} else {
				logger.warn(NOTNEED, field.getName());
				return null;
			}
		}
		if (value instanceof Integer) {
			int min = getMin(between);
			int max = getMax(between);
			if ((int) value < min || (int) value  > max) {
				logger.error("The value of this field({}) exceeds the limit [{}-{}]!", field.getName(), min, max);
				return new ValidateMessage(verify, field.getName(), between.message() + "[" + min + "-" + max + "]");
			}
		} else {
			logger.error("This field({}) is not Integer!", field.getName());
			return new ValidateMessage(verify, field.getName(), "该字段不是Integer类型");
		}
		
		logger.debug(SUCCESS, field.getName());
		return null;
	}
	
	/**
	 * 获取Between注解的最小值
	 * 
	 * @param between	between.min()
	 * @return		注解between min对应的int值
	 */
	private static int getMin(Between between) {
		return getValue(between.min(), Between.MIN_DEFAULT);
	}
	/**
	 * 获取Between注解的最大值
	 * 
	 * @param between	between.max()
	 * @return		注解between max对应的int值
	 */
	private static int getMax(Between between) {
		return getValue(between.max(), Between.MAX_DEFAULT);
	}
	
	/**
	 * 获取Between注解的值
	 * 
	 * @param key
	 * @param defaultValue	默认int值
	 * @return		key对应的int值
	 */
	private static int getValue(String key, String defaultValue) {
		Integer value = cache.get(key);
		if (value == null) {
			value = ParamUtils.getInt(key);
			value = value != null ? value : Integer.parseInt(defaultValue);
			cache.put(key, value);
		}
		return value.intValue();
	}

}
