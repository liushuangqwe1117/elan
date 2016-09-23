package com.redrock.elan.mgm.util;

import java.util.Properties;

public class PropertyUtil {
	
	private static final String FILE_BASE_DIR = "FILE_BASE_DIR";
	
	private static Properties properties;
	static {
		// 加载属性文件
		PropertiesLoader props = new PropertiesLoader("classpath:properties/config.properties");
		properties = props.getProperties();
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
	
	public static String getFileBaseDir(){
		String fileBaseDir = getProperty(FILE_BASE_DIR);
		if(fileBaseDir != null && !fileBaseDir.endsWith("/")) {
			fileBaseDir = fileBaseDir + "/";
		}
		return fileBaseDir;
	}
}
