package com.cn.jp.orine.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BeanUitl {

	private static Logger logger = LoggerFactory.getLogger(BeanUitl.class);

	/**
	 * 属性copy
	 * 
	 * @param source
	 *            来源
	 * @param target
	 */
	public static void copyProperties(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			logger.error("属性拷贝异常", e);
		}
	}

	/**
	 * 属性copy
	 *
	 * @param source
	 *            来源
	 * @param target
	 */
	public static void copyPropertiesIgnoreNull(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
		} catch (Exception e) {
			logger.error("属性拷贝异常", e);
		}
	}

	public static String[] getNullPropertyNames (Object source) {
		Set<String> emptyNames = new HashSet<String>();

		Class clazz = source.getClass();

		for (Field f : clazz.getDeclaredFields()){
			f.setAccessible(true);
			try {
				if(null == f.get(source)){
					emptyNames.add(f.getName());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**
	 * @方法名:transBean2Map
	 * @描述:bean转map
	 * @创建者:wk
	 * @创建时间:2017年5月23日 下午4:25:21
	 * @参数:@param obj
	 * @参数:@return
	 * @return:Map<String,Object>
	 */
	public static HashMap<String, Object> transBean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
}
