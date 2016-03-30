package com.agri.platform.captcha;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 配置参数
 * 
 * @author yu zhang
 * 
 */
public class CaptchaCommon {

	public static Logger logger = Logger.getLogger(CaptchaCommon.class);
	
	/**
	 * 登录验证码最小长度
	 */
	public static int valid_code_length_min = 4;
	
	/**
	 * 登录验证码最大长度
	 */
	public static int valid_code_length_max = 4;
	
	/**
	 * 登录验证码最大长度
	 */
	public static int valid_code_font_size = 70;
	
	/**
	 * 服务启动时加载参数配置
	 */
	static {
		Properties properties = null;
		try {
			properties = PropertiesLoaderUtils
					.loadAllProperties("conf/captcha/config.properties");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		// 从配置文件中读取参数值
		if (properties != null) {
			valid_code_length_min = Integer.parseInt(properties.getProperty("valid_code_length_min", "4"));
			valid_code_length_max = Integer.parseInt(properties.getProperty("valid_code_length_max", "4"));
			valid_code_font_size = Integer.parseInt(properties.getProperty("valid_code_font_size", "70"));
		}
		properties.clear();
		properties = null;
	}
}
