package com.member.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类，判断对象，数组，集合是否为空
 * @author wzhz
 * @date 2016-10-25
 */
@SuppressWarnings("rawtypes")
public class BeanUtil {

	private final static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

	/**
	 * 判断Object是否为空
	 * @param obj
	 * @return null-true; false
	 */
	public static boolean isBlank(Object obj) {
		if (null == obj) {
			return true;
		}
		return false;
	}

	/**
	 * 判断List是否为空
	 * @param list
	 * @return null-true; false
	 */
	public static boolean isBlank(List list) {
		if (null == list || list.size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Map是否为空
	 * @param map
	 * @return null-true; false
	 */
	public static boolean isBlank(Map map) {
		if (null == map || map.size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Object[]是否为空
	 * @param obj
	 * @return null-true; false
	 */
	public static boolean isBlank(Object[] obj) {
		if (null == obj || obj.length <= 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		logger.error(isBlank(list) + "");
	}

}
