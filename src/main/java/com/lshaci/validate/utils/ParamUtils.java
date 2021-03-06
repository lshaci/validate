package com.lshaci.validate.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 验证注解参数配置工具
 * 
 * @author lshaci
 */
public abstract class ParamUtils {

	private static final Logger logger = LoggerFactory.getLogger(ParamUtils.class);

	/**
	 * 验证参数配置文件名
	 */
	private static final String FILE_NAME = "validate_param.properties";

	private static Properties properties = new Properties();

	static {
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME);
			properties.load(is);
		} catch (Exception e) {
			logger.warn("This file({}) in classpath not found!", FILE_NAME);
		}
	}

	/**
	 * 从验证参数配置中获取key对应的Integer值
	 * 
	 * @param key
	 * @return	若key不为占位符则返回该key解析后的Integer，若key为占位符则返回properties中key对应的Integer值
	 */
	public static Integer getInt(String key) {
		String value = PropertiesHelper.get(properties, key);
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			logger.error("This value({}) is not Integer!", value, e);
			return null;
		}
	}

}
