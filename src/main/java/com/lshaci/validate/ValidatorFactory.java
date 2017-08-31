package com.lshaci.validate;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Validator Factory
 * 
 * @author lshaci
 */
public class ValidatorFactory {
	
	/**
	 * validate annotation path
	 */
	private static final String ANNOTATION_PATH = "annotation";
	
	/**
	 * Build a Validator, The validate annotation in ./annotation 
	 * 
	 * @return The default Validator
	 */
	public static Validator buildDefaultValidator() {
		String packageName = ValidatorFactory.class.getPackage().getName() + "." + ANNOTATION_PATH + ".";
		String currentPath = ValidatorFactory.class.getResource(".").getPath();
		File file = new File(currentPath, ANNOTATION_PATH);
		
		List<String> annotationClassNames = Arrays.stream(file.listFiles())
			.map(File::getName)
			.filter(e -> e.endsWith(".class"))
			.map(e -> packageName + e.substring(0, e.indexOf(".")))
			.collect(toList());
		
		return new Validator(annotationClassNames);
	}
}
