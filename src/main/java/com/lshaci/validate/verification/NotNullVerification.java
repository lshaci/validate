package com.lshaci.validate.verification;

import java.lang.reflect.Field;

import com.lshaci.validate.annotation.NotNull;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 非空验证
 * 
 * @author lshaci
 */
public class NotNullVerification implements Verification {

	@Override
	public ValidateMessage validate(Field field, Object value) {
		NotNull notNull = field.getAnnotation(NotNull.class);
		if (value == null) {
			return new ValidateMessage(field.getName(), notNull.message());
		}
		return null;
	}

}
