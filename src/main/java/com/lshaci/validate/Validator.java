package com.lshaci.validate;

import static java.util.stream.Collectors.toSet;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.lshaci.validate.model.ValidateMessage;
import com.lshaci.validate.verify.Verify;

/**
 * Validator Tool
 * 
 * @author lshaci
 */
public class Validator {
	
	/**
	 * Verify suffix
	 */
	private static final String SUFFIX = Verify.class.getSimpleName();
	/**
	 * Verifies cache
	 */
	private static final Map<String, Verify> verifies = new HashMap<>();
	/**
	 * The Set with validate annotation  class names
	 */
	private Set<String> annotationClassNames;
	
	/**
	 * The Map with validate verify(key: simple class name, value: classname)
	 */
	private Map<String, String> verifyClassNames;
	
	/**
	 * Create a Validator with annotation class names
	 * 
	 * @param annotationClassNames	The Set with validate annotation  class names
	 * @param verifyClassNames		The Map with validate verify(key: simple class name, value: classname)
	 */
	protected Validator(Set<String> annotationClassNames, Map<String, String> verifyClassNames) {
		Objects.requireNonNull(verifyClassNames);
		Objects.requireNonNull(annotationClassNames);
		
		this.annotationClassNames = annotationClassNames;
		this.verifyClassNames = verifyClassNames;
	}
	
	/**
	 * Validate object
	 * 
	 * @param obj	The need validate object
	 * @return	The validate detail result
	 */
	public Set<ValidateMessage> validate(Object obj) {
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
	 * Get the Verify Object with validate annotation
	 * 
	 * @param annotation	The validate annotation
	 * @return	The Verify instance
	 */
	private Verify getVerify(Annotation annotation) {
		String key = annotation.annotationType().getSimpleName() + SUFFIX;
		String className = verifyClassNames.get(key);
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
