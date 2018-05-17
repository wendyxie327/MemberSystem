package com.member.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类，判断字符串是否为空或null
 * @author wzhz
 * @date 2016-10-25
 */
public class StringUtil {

	private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);

	/** 如果String为Null或空，返回true */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str);
	}

	/** 如果Object为Null或空，返回true */
	public static boolean isEmpty(Object obj) {
		return null == obj || "".equals(obj);
	}

	/** 如果String为Null或为空或仅包含空格和\t，则返回true */
	public static boolean isBlank(String str) {
		return isEmpty(str) || str.trim().equals("");
	}

	/** 如果String为Null或为空或者等于NONE，返回true */
	public static boolean isNone(String str) {
		return isEmpty(str) || "NONE".equals(str);
	}

	/**
	 * 替换占位符：支持'?'和{\d}两种占位符。
	 * @param str
	 * @param arr
	 * @return
	 */
	public static String formStr(String str, String[] arr) {
		Matcher m;
		if (str.contains("?")) {
			String a[] = str.split("[?]");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < a.length; i++) {
				sb.append(a[i]);
				if (i < arr.length)
					sb.append(arr[i].trim());
			}
			return sb.toString();
		} else {
			m = Pattern.compile("\\{\\d\\}").matcher(str);
			while (m.find()) {
				str = str.replace(m.group(), arr[Integer.parseInt(m.group(0))]);
			}
		}

		return str;
	}

	/**
	 * 如果judgeString为Null或空，返回true
	 * @param judgeString
	 * @return
	 */
	public static boolean isBlank(String... judgeString) {

		for (String str : judgeString) {
			if (str == null || "".equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否是手机号码/验证手机号是否合法的方法
	 * @param mobiles 手机号码
	 * @return true 是
	 */
	public static boolean isMobileNo(String mobiles) {
		if (mobiles == null) return false;
		// ^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4])|(18[0-3,5-9])|(17[0-8])|(147))\\d{8}$");
		Matcher matcher = pattern.matcher(mobiles);
		return matcher.matches();
	}
	/**
	 * 过滤替换字符串中的<>$"..
	 * @param message
	 * @return
	 */
	public static String stringFilter(String message) {

		if (message == null)
			return (null);

		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default: {
				if (i % 68 == 67) {
					result.append("<br/>");
				}
				result.append(content[i]);
			}
			}
		}
		return (result.toString());
	}
	
	/**
	 * 判断季度
	 * @param month
	 * @return
	 */
	public static String isQuarter(String month) {
		int m = Integer.parseInt(month);
		String q = "";
		if (m >= 1 && m <= 3) {
			q = "1";
		} else if (m >= 4 && m <= 6) {
			q = "2";
		} else if (m >= 7 && m <= 9) {
			q = "3";
		} else if (m >= 10 && m <= 12) {
			q = "4";
		}
		return q;
	}

}
