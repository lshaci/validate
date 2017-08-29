package com.lshaci.validate.verification;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.NotNull;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 非空验证
 * 
 * @author lshaci
 */
public class NotNullVerification implements Verification {
	
	private static final Logger logger = LoggerFactory.getLogger(NotNullVerification.class);

	@Override
	public ValidateMessage validate(Field field, Object value) {
		NotNull notNull = field.getAnnotation(NotNull.class);
		if (value == null) {
			logger.error(ISNULL, field.getName());
			return new ValidateMessage(field.getName(), notNull.message());
		}
		logger.debug(SUCCESS, field.getName());
		return null;
	}

}
