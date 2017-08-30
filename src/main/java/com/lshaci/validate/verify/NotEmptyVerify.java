package com.lshaci.validate.verify;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.NotEmpty;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 非空验证
 * 
 * @author lshaci
 */
public class NotEmptyVerify implements Verify {

	private static final Logger logger = LoggerFactory.getLogger(NotEmptyVerify.class);
	
	private static final String verify = NotEmpty.class.getSimpleName();

	@SuppressWarnings("rawtypes")
	@Override
	public ValidateMessage validate(Field field, Object value) {
		NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
		if (value == null) {
			logger.error(ISNULL, field.getName());
			return new ValidateMessage(verify, field.getName(), notEmpty.message());
		}
		if (value instanceof String) {
			if ("".equals(((String) value).trim())) {
				logger.error("This field({}) is a String, but empty!", field.getName());
				return new ValidateMessage(verify, field.getName(), notEmpty.message());
			} else {
				logger.debug("This field({}) is a String, validate success!", field.getName());
				return null;
			}
		}
		if (value instanceof String) {
			if (((List) value).isEmpty()) {
				logger.error("This field({}) is a List, but empty!", field.getName());
				return new ValidateMessage(verify, field.getName(), notEmpty.message());
			} else {
				logger.debug("This field({}) is a List, validate success!", field.getName());
				return null;
			}
		}
		if (value instanceof Map) {
			if (((Map) value).isEmpty()) {
				logger.error("This field({}) is a Map, but empty!", field.getName());
				return new ValidateMessage(verify, field.getName(), notEmpty.message());
			} else {
				logger.debug("This field({}) is a Map, validate success!", field.getName());
				return null;
			}
		}
		logger.error("This field({}) is a {}, NotEmpty authentication is not supported!", field.getName(), value.getClass().getSimpleName());
		return new ValidateMessage(verify, field.getName(), "该字段不支持NotEmpty验证");
	}

}
