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
import com.lshaci.validate.verification.Verification;

/**
 * 验证工具
 * 
 * @author root
 *
 */
public class Validator {
	
	/**
	 * Verification path
	 */
	private static final String VERIFICATION_PATH = "verification";
	/**
	 * 注解验证器包名
	 */
	private static final String PREFIX = Validator.class.getPackage().getName() + "." + VERIFICATION_PATH + ".";
	/**
	 * 验证器后缀
	 */
	private static final String SUFFIX = "Verification";
	/**
	 * 注解验证器缓存
	 */
	private static final Map<String, Verification> verifications = new HashMap<>();
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
								Verification verification = getVerification(a);
								if (verification != null) {
									try {
										return verification.validate(f, f.get(obj));
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
	private Verification getVerification(Annotation annotation) {
		String className = PREFIX + annotation.annotationType().getSimpleName() + SUFFIX;
		try {
			Verification verification = verifications.get(className);
			if (verification == null) {
				verification = (Verification) Class.forName(className).newInstance();
				verifications.put(className, verification);
			}
			return verification;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
