package com.member.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by wzhz on 2016/10/31.
 */
public class ResPropertiesUtil {

	private Properties _prop = new Properties();

	/**
	 * 根据key读取对应的value
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return _prop.getProperty(key);
	}

	/**
	 * 读取配置文件(默认的)读取etc.properties
	 */
	public ResPropertiesUtil() {
		try {
			// 读取配置文件
			InputStream in = ResPropertiesUtil.class.getResourceAsStream("/config/res.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			_prop.load(bf);
			in.close();
			bf.close();
		} catch (IOException e) {
		}
	}
}