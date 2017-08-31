package com.lshaci.validate.utils;

import java.util.HashSet;
import java.util.Set;

import com.lshaci.validate.Validator;
import com.lshaci.validate.ValidatorFactory;
import com.lshaci.validate.model.ValidateMessage;

/**
 * 
 * 验证工具
 * 
 * @author lshaci
 */
public class ValidateUtils {
	
	private static final Validator validator = ValidatorFactory.buildDefaultValidator();
	
	/**
	 * 简单验证一个对象
	 * 
	 * @param obj	需要验证的对象
	 * @return	是否通过验证
	 */
	public static <T> boolean validate(T obj) {
		return detailValidate(obj).isEmpty();
	}
	
	/**
	 * 详细验证一个对象
	 * 
	 * @param obj		需要验证的对象
	 * @return	返回验证详细信息
	 */
	public static <T> Set<ValidateMessage> detailValidate(T obj) {
		Set<ValidateMessage> result = validator.validate(obj);
		if (result != null) {
			return result;
		}
		return new HashSet<>();
	}

}
