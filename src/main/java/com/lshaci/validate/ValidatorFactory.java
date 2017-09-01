package com.lshaci.validate;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lshaci.validate.utils.ScannerPackageUtils;

/**
 * Validator Factory
 * 
 * @author lshaci
 */
public class ValidatorFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(ValidatorFactory.class);
	
	/**
	 * The default validate annotation package name
	 */
	private static final String ANNOTATION_PACKAGE_NAME = "com.lshaci.validate.annotation";
	
	/**
	 * The default validate verify package name
	 */
	private static final String VERIFY_PACKAGE_NAME = "com.lshaci.validate.verify.impl";
	
	/**
	 * The default validate annotation class names
	 */
	private static final Set<String> DEFAULT_ANNOTATION_CLASS_NAMES = getAnnotationClassNames(ANNOTATION_PACKAGE_NAME);
	
	/**
	 * The default validate verify class names
	 */
	private static final Map<String, String> DEFAULT_VERIFY_CLASS_NAMES = getVerifyClassNames(VERIFY_PACKAGE_NAME);
	
	
	/**
	 * Build a default Validator<br>
	 * 	The validate annotation in "com.lshaci.validate.annotation"<br>
	 *  The validate verify in "com.lshaci.validate.verify.impl"
	 * 
	 * @return The default Validator
	 */
	public static Validator buildDefaultValidator() {
		return new Validator(DEFAULT_ANNOTATION_CLASS_NAMES, DEFAULT_VERIFY_CLASS_NAMES);
	}
	
	/**
	 * Build a Validator
	 * 
	 * @param annotationPackageName		The validate annotation package name
	 * @param verifyPackageName			The validate verify package name
	 * @return 	Validator instance
	 */
	public static Validator buildValidator(String annotationPackageName, String verifyPackageName) {
		Set<String> annotationClassNames = getAnnotationClassNames(annotationPackageName);
		annotationClassNames.addAll(DEFAULT_ANNOTATION_CLASS_NAMES);
		Map<String, String> verifyClassNames = getVerifyClassNames(verifyPackageName);
		verifyClassNames.putAll(DEFAULT_VERIFY_CLASS_NAMES);
		
		return new Validator(annotationClassNames, verifyClassNames);
	}
	
	/**
	 * Get validate annotation class names
	 * 
	 * @param annotationPackageName		annotation package name
	 * @return	The Set with validate annotation  class names
	 */
	private static Set<String> getAnnotationClassNames(String annotationPackageName) {
		Set<String> classNames = ScannerPackageUtils.getClassNames(annotationPackageName, false);
		if (classNames == null || classNames.isEmpty()) {
			logger.error("No validate annotation!!!");
			throw new RuntimeException("No validate annotation!!!");
		}
		return classNames;
	}
	
	/**
	 * Get validate verify class names
	 * 
	 * @param annotationPackageName		annotation package name
	 * @return	The Map with validate verify(key: simple class name, value: classname)
	 */
	private static Map<String, String> getVerifyClassNames(String verifyPackageName) {
		Set<String> classNames = ScannerPackageUtils.getClassNames(verifyPackageName, false);
		if (classNames == null || classNames.isEmpty()) {
			logger.error("No validate verify!!!");
			throw new RuntimeException("No validate verify!!!");
		}
		return classNames.stream()
				.collect(toMap(a -> ((String) a).substring(((String) a).lastIndexOf(".") + 1), a -> a , (k1, k2) -> k2));
	}
}
