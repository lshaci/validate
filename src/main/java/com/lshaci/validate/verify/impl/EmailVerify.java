package com.lshaci.validate.verify.impl;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.Email;
import com.lshaci.validate.model.ValidateMessage;
import com.lshaci.validate.verify.Verify;

/**
 * 邮箱验证
 * 
 * @author lshaci
 */
public class EmailVerify implements Verify {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailVerify.class);
	
	private static final String verify = Email.class.getSimpleName();
	
	/**
	 * 邮箱正则表达式
	 */
	private static final String EMAIL_REGEX = "^[a-z_\\d]+(\\.[a-z_\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$";

	@Override
	public ValidateMessage validate(Field field, Object value) {
		Email email = field.getAnnotation(Email.class);

		if (value == null) {
			if (email.require()) {
				logger.error(ISNULL, field.getName());
				return new ValidateMessage(verify, field.getName(), email.message());
			} else {
				logger.warn(NOTNEED, field.getName());
				return null;
			}
		}
		if (value instanceof String) {
			if (!((String) value).matches(EMAIL_REGEX)) {
				logger.error("This field({}) is not an email!", field.getName());
				return new ValidateMessage(verify, field.getName(), email.message());
			}
		} else {
			logger.error("This field({}) is not String!", field.getName());
			return new ValidateMessage(verify, field.getName(), "该字段不是字符串类型");
		}
		logger.debug(SUCCESS, field.getName());
		return null;
	}

}
