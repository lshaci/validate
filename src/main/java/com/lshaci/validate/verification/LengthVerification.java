package com.lshaci.validate.verification;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.Length;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 长度验证
 * 
 * @author lshaci
 */
public class LengthVerification implements Verification {
	
	private static final Logger logger = LoggerFactory.getLogger(LengthVerification.class);

	@Override
	public ValidateMessage validate(Field field, Object value) {
		Length length = field.getAnnotation(Length.class);
		if (value == null) {
			logger.error(ISNULL, field.getName());
			return new ValidateMessage(field.getName(), length.message());
		}
		
		if (value.toString().length() < length.min() || value.toString().length() > length.max()) {
			logger.error("The length of this field({}) exceeds the limit!", field.getName());
			return new ValidateMessage(field.getName(), length.message());
		}
		logger.debug(SUCCESS, field.getName());
		return null;
	}

}
