package com.lshaci.validate.verify;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.Between;
import com.lshaci.validate.model.ValidateMessage;
import com.lshaci.validate.utils.ParamUtils;

/**
 * int数值范围验证
 * 
 * @author lshaci
 */
public class BetweenVerify implements Verify {
	
	private static final Logger logger = LoggerFactory.getLogger(BetweenVerify.class);
	
	private static final String verify = Between.class.getSimpleName();

	@Override
	public ValidateMessage validate(Field field, Object value) {
		Between between = field.getAnnotation(Between.class);
		if (value == null) {
			if (between.require()) {
				logger.error(ISNULL, field.getName());
				return new ValidateMessage(verify, field.getName(), between.message());
			} else {
				logger.warn(NOTNEED, field.getName());
				return null;
			}
		}
		if (value instanceof Integer) {
			Integer min = ParamUtils.getInt(between.min());
			Integer max = ParamUtils.getInt(between.max());
			min = min != null ? min : Integer.parseInt(Between.MIN_DEFAULT);
			max = max != null ? max : Integer.parseInt(Between.MAX_DEFAULT);
			if ((int) value < min || (int) value  > max) {
				logger.error("The value of this field({}) exceeds the limit [{}-{}]!", field.getName(), min, max);
				return new ValidateMessage(verify, field.getName(), between.message() + "[" + min + "-" + max + "]");
			}
		} else {
			logger.error("This field({}) is not Integer!", field.getName());
			return new ValidateMessage(verify, field.getName(), "该字段不是Integer类型");
		}
		
		logger.debug(SUCCESS, field.getName());
		return null;
	}

}
