
package com.help.server.common;

import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取配置文件工具类
 * 
 * @author LY
 * @version 1.0
 */
public class CommonConfigUtil {

	private static final Properties PRO = new Properties();
	private static String charSet = "UTF-8";
	private static final Logger LOG = LoggerFactory.getLogger(CommonConfigUtil.class);

	static {
		try (
			InputStreamReader applicationProperties = new InputStreamReader(
				CommonConfigUtil.class.getClassLoader().getResourceAsStream("application.properties"), charSet);
			) {
			PRO.load(applicationProperties);
		} catch (Exception e) {
			LOG.error("加载配置文件a.properties异常，请检查文件是否存在！", e);
		}
	}

	public static String getValue(String key) {
		if (PRO.containsKey(key) == false) {
			return "";
		}
		return ((String) PRO.get(key)).trim();
	}

}
