package com.lshaci.validate.verify.impl;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.NotNull;
import com.lshaci.validate.model.ValidateMessage;
import com.lshaci.validate.verify.Verify;

/**
 * 非空验证
 * 
 * @author lshaci
 */
public class NotNullVerify implements Verify {
	
	private static final Logger logger = LoggerFactory.getLogger(NotNullVerify.class);
	
	private static final String verify = NotNull.class.getSimpleName();

	@Override
	public ValidateMessage validate(Field field, Object value) {
		NotNull notNull = field.getAnnotation(NotNull.class);
		if (value == null) {
			logger.error(ISNULL, field.getName());
			return new ValidateMessage(verify, field.getName(), notNull.message());
		}
		logger.debug(SUCCESS, field.getName());
		return null;
	}

}
