package com.lshaci.validate;

import static java.util.stream.Collectors.toSet;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.lshaci.validate.model.ValidateMessage;
import com.lshaci.validate.verify.Verify;

/**
 * 验证工具
 * 
 * @author root
 *
 */
public class Validator {
	
	/**
	 * 注解验证器包名
	 */
	private static final String PREFIX = Verify.class.getPackage().getName() + ".";
	/**
	 * 验证器后缀
	 */
	private static final String SUFFIX = Verify.class.getSimpleName();
	/**
	 * 注解验证器缓存
	 */
	private static final Map<String, Verify> verifies = new HashMap<>();
	/**
	 * 需要验证的注解
	 */
	private List<String> annotationClassNames = new ArrayList<>();
	
	/**
	 * Create a Validator with annotation class names
	 * 
	 * @param annotationClassNames
	 */
	protected Validator(List<String> annotationClassNames) {
		this.annotationClassNames = annotationClassNames;
	}
	
	/**
	 * 对指定对象进行验证
	 * 
	 * @param obj	需要验证的对象
	 * @return	验证的结果信息
	 */
	public <T> Set<ValidateMessage> validate(T obj) {
		Objects.requireNonNull(obj, "需要验证的对象不能为空");
		
		try {
			return Arrays.stream(obj.getClass().getDeclaredFields())
					.flatMap(f -> {
						f.setAccessible(true);
						return Arrays.stream(f.getAnnotations())
							.filter(a -> annotationClassNames.contains(a.annotationType().getName()))
							.map(a -> {
								Verify verify = getVerify(a);
								if (verify != null) {
									try {
										return verify.validate(f, f.get(obj));
									} catch (IllegalArgumentException | IllegalAccessException e) {
										e.printStackTrace();
									}
								}
								return null;
							})
							.filter(v -> v != null);
			}).collect(toSet());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据注解, 获取该注解的验证对象
	 * 
	 * @param annotation	需要获取验证器的注解
	 * @return	该注解的验证对象
	 */
	private Verify getVerify(Annotation annotation) {
		String className = PREFIX + annotation.annotationType().getSimpleName() + SUFFIX;
		try {
			Verify verification = verifies.get(className);
			if (verification == null) {
				verification = (Verify) Class.forName(className).newInstance();
				verifies.put(className, verification);
			}
			return verification;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
