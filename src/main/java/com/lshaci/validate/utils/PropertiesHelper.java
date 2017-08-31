package com.lshaci.validate.utils;

import java.util.Objects;
import java.util.Properties;

/**
 * 配置文件帮助工具
 * 
 * @author lshaci
 */
public abstract class PropertiesHelper {
	
	/**
	 * 获取key对应的值
	 * 
	 * @param properties	properties
	 * @param key			key
	 * @return	若key不为占位符则返回该key，若key为占位符则反射properties中key对应的值
	 */
	public static String get(Properties properties, String key) {
		String value = key;
		if (PropertiesHelper.isPlaceholder(key)) {
			key = PropertiesHelper.getPlaceholderKey(key);
			Objects.requireNonNull(properties);
			value = properties.getProperty(key);
		}
		return value;
	}

	/**
	 * 判断该key是否是占位符格式
	 * 
	 * @param key	需要判断的key
	 * @return	是否是占位符
	 */
	private static boolean isPlaceholder(String key) {
		return key != null && key.trim().startsWith("${") && key.trim().endsWith("}");
	}
	
	/**
	 * 从占位符key中提取原始的key
	 * 
	 * @param key	占位符key
	 * @return	原始的key
	 */
	private static String getPlaceholderKey(String key) {
		Objects.requireNonNull(key);
		return key.trim().substring(2, key.trim().length() - 1);
	}
}
