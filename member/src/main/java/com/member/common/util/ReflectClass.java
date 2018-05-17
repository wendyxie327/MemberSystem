package com.member.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("all")
public class ReflectClass {

	private final static Logger logger = LoggerFactory.getLogger(ReflectClass.class);

	/**
	 * @param args
	 */
	// 导出 通用
	public Object[] getReflectObject(Object object, Class clasz) {
		Field[] fields = clasz.getDeclaredFields();
		Object[] objects = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			// 获得和属性对应的getXXX()方法的名字
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			// 获得和属性对应的getXXX()方法
			Method getMethod = null;
			try {
				getMethod = clasz.getMethod(getMethodName, new Class[] {});
			} catch (SecurityException e) {
				logger.error(e.toString());
			} catch (NoSuchMethodException e) {
				logger.error(e.toString());
			}
			// 调用原对象的getXXX()方法
			try {
				objects[i] = getMethod.invoke(object, new Object[] {});
			} catch (IllegalArgumentException e) {
				logger.error(e.toString());
			} catch (IllegalAccessException e) {
				logger.error(e.toString());
			} catch (InvocationTargetException e) {
				logger.error(e.toString());
			}
		}
		return objects;
	}

	/**
	 * (导出使用)<br>
	 * 传一个参数object数据；指定object类型clasz 通用
	 * @param fieldName
	 * @param object
	 * @param clasz
	 * @return
	 */
	public static String getReflectList(String fieldName, Object object, Class clasz) {
		// String fieldName = fields[j].getName();
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		// 获得和属性对应的getXXX()方法的名字
		String methodName = "get" + firstLetter + fieldName.substring(1);
		// 获得和属性对应的getXXX()方法
		Method method = null;
		String text = "";
		try {
			method = clasz.getMethod(methodName, new Class[] {});
		} catch (SecurityException e) {
			logger.error(e.toString());
		} catch (NoSuchMethodException e) {
			logger.error(e.toString());
		}
		// 调用原对象的getXXX()方法
		try {
			text = (String) method.invoke(object, new Object[] {});
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
		} catch (IllegalAccessException e) {
			logger.error(e.toString());
		} catch (InvocationTargetException e) {
			logger.error(e.toString());
		}
		return text;
	}

	public Object[] getReflectFieldName(Field[] fields) {
		Object[] objects = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			objects[i] = fieldName;
		}
		return objects;
	}

}
