package com.lshaci.validate.verification;

import java.lang.reflect.Field;

import com.lshaci.validate.annotation.Length;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 长度验证
 * 
 * @author lshaci
 */
public class LengthVerification implements Verification {

	@Override
	public ValidateMessage validate(Field field, Object value) {
		Length length = field.getAnnotation(Length.class);
		if (value == null || value.toString().length() < length.min() || value.toString().length() > length.max()) {
			return new ValidateMessage(field.getName(), length.message());
		}
		return null;
	}

}
