package com.lshaci.validate.verification;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.lshaci.validate.annotation.NotEmpty;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 非空验证
 * 
 * @author lshaci
 */
public class NotEmptyVerification implements Verification {

	@SuppressWarnings("rawtypes")
	@Override
	public ValidateMessage validate(Field field, Object value) {
		NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
		if (value == null) {
			return new ValidateMessage(field.getName(), notEmpty.message());
		} else if (value instanceof String && "".equals(((String) value).trim())) {
			return new ValidateMessage(field.getName(), notEmpty.message());
		} else if (value instanceof List && ((List) value).isEmpty()) {
			return new ValidateMessage(field.getName(), notEmpty.message());
		} else if (value instanceof Map && ((Map) value).isEmpty()) {
			return new ValidateMessage(field.getName(), notEmpty.message());
		}
		return null;
	}

}
