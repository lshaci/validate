package com.lshaci.validate.verify;

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
public class LengthVerify implements Verify {
	
	private static final Logger logger = LoggerFactory.getLogger(LengthVerify.class);
	
	private static final String verify = Length.class.getSimpleName();

	@Override
	public ValidateMessage validate(Field field, Object value) {
		Length length = field.getAnnotation(Length.class);
		if (value == null) {
			if (length.require()) {
				logger.error(ISNULL, field.getName());
				return new ValidateMessage(verify, field.getName(), length.message());
			} else {
				logger.warn(NOTNEED, field.getName());
				return null;
			}
		}
		if (value.toString().length() < length.min() || value.toString().length() > length.max()) {
			logger.error("The length of this field({}) exceeds the limit [{}-{}]!", field.getName(), length.min(), length.max());
			return new ValidateMessage(verify, field.getName(), length.message());
		}
		
		logger.debug(SUCCESS, field.getName());
		return null;
	}

}
