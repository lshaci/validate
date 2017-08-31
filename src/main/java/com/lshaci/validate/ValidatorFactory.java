package com.lshaci.validate;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator Factory
 * 
 * @author lshaci
 */
public class ValidatorFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(ValidatorFactory.class);
	
	/**
	 * validate annotation path
	 */
	private static final String ANNOTATION_PATH = "annotation";
	
	/**
	 * Build a Validator, The validate annotation in ./annotation 
	 * 
	 * @return The default Validator
	 * @throws IOException 
	 */
	public static Validator buildDefaultValidator() {
		String packageName = ValidatorFactory.class.getPackage().getName() + "." + ANNOTATION_PATH + ".";
		String path = packageName.replace(".", "/");
		List<File> files = new ArrayList<>();
		Enumeration<URL> resources;
		try {
			resources = Thread.currentThread().getContextClassLoader().getResources(path);
		} catch (IOException e) {
			logger.warn("Ignore this exception!");
			throw new RuntimeException();
		}
		while (resources.hasMoreElements()) {
			URL url = (URL) resources.nextElement();
			files.add(new File(url.getPath()));
		}
		List<String> annotationClassNames = files.stream()
				.filter(File::isDirectory)
				.map(File::getName)
				.filter(e -> e.endsWith(".class"))
				.map(e -> packageName + e.substring(0, e.indexOf(".")))
				.collect(toList());
		return new Validator(annotationClassNames);
		
	}
}
