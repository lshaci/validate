package com.lshaci.validate.verify;

import java.lang.reflect.Field;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.annotation.Json;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 长度验证
 * 
 * @author lshaci
 */
public class JsonVerify implements Verify {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonVerify.class);
	
	private static final ScriptEngine se = new ScriptEngineManager().getEngineByName("js");
	
	private static final String verify = Json.class.getSimpleName();

	@Override
	public ValidateMessage validate(Field field, Object value) {
		Json json = field.getAnnotation(Json.class);
		if (value == null) {
			if (json.require()) {
				logger.error(ISNULL, field.getName());
				return new ValidateMessage(verify, field.getName(), json.message());
			} else {
				logger.warn(NOTNEED, field.getName());
				return null;
			}
		}
		if (value instanceof String) {
			if (isJson((String) value)) {
				logger.debug(SUCCESS, field.getName());
				return null;
			} else {
				logger.error("The value of this field({}) not in json format!", field.getName());
				return new ValidateMessage(verify, field.getName(), json.message());
			}
		} else {
			logger.error("The value of this field({}) is not String!", field.getName());
			return new ValidateMessage(verify, field.getName(), json.message());
		}
	}
	
	/**
	 * 判断一个字符是否是json格式
	 * 
	 * @param jsonStr	需要进行判断的字符串
	 * @return
	 */
	private static boolean isJson(String jsonStr) {
		try {
			se.eval(jsonStr);
			return true;
		} catch (ScriptException e) {
			return false;
		}
	}

}
