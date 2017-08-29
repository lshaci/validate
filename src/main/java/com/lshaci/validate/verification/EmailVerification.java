package com.lshaci.validate.verification;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.Email;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 邮箱验证
 * 
 * @author lshaci
 */
public class EmailVerification implements Verification {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailVerification.class);
	
	/**
	 * 邮箱正则表达式
	 */
	private static final String EMAIL_REGEX = "^[a-z_\\\\d]+(\\\\.[a-z_\\\\d]+)*@([\\\\da-z](-[\\\\da-z])?)+(\\\\.{1,2}[a-z]+)+$";

	@Override
	public ValidateMessage validate(Field field, Object value) {
		Email email = field.getAnnotation(Email.class);
		if (value == null) {
			logger.error(ISNULL, field.getName());
			return new ValidateMessage(field.getName(), email.message());
		}
		if (value instanceof String) {
			if (!((String) value).matches(EMAIL_REGEX)) {
				logger.error("This field({}) is not an email!", field.getName());
				return new ValidateMessage(field.getName(), email.message());
			}
		} else {
			logger.error("This field({}) is not String!", field.getName());
			return new ValidateMessage(field.getName(), "该字段不是字符串类型");
		}
		logger.debug(SUCCESS, field.getName());
		return null;
	}

}
