package com.cn.jp.orine.blog.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigDecimalUtil {
	private static int DEF_DIV_SCALE = 10; // 默认精确的小数位

	/**
	 * 提供精确的加法运算。
	 * @param params 参数数组
	 * @return 和
	 */
	public static double add(double... params) {
		BigDecimal b1 = new BigDecimal(0);
		for (double param : params) {
			BigDecimal b2 = new BigDecimal(Double.toString(param));
			b1 = b1.add(b2);
		}
		return b1.doubleValue();
	}
	
	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param params 参数数组
	 * @return 动态参数的积
	 */
	public static double mul(double... params) {
		BigDecimal b1 = new BigDecimal(1);
		for (double param : params) {
			BigDecimal b2 = new BigDecimal(Double.toString(param));
			b1 = b1.multiply(b2);
		}
		return b1.doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位(2位)四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字
	 * @return 四舍五入后的结果
	 */
	public static double round(double v) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字字符串
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(String v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位(2位)四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字字符串
	 * @return 四舍五入后的结果
	 */
	public static double round(String v) {
		if (StringUtils.isBlank(v)) {
			return 0;
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位处理，去掉保留位数后的数字
	 * 
	 * @param v 需要处理的数字
	 * @param scale 小数点后保留几位
	 * @return 去掉保留位数后的结果
	 */
	public static double decimal(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
     * 提供精确的加法运算。
     * 
     * @param b1
     *            被加数
     * @param b2
     *            加数
     * @return 两个参数的和
     */
    public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
        return b1.add(b2);
    }

    /**
     * 取相反数
     * 
     * @param b
     *            需要取相反数的数字
     * @return
     */
    public static BigDecimal opposite(BigDecimal b) {
        return b.multiply(new BigDecimal(-1));
    }

    /**
     * 提供精确的减法运算。
     * 
     * @param b1
     *            被减数
     * @param b2
     *            减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {
        return b1.subtract(b2);
    }
    
    /**
     * 根据字符串获取BigDecimal类型数据
     * @param str
     * @return
     */
    public static BigDecimal getBigDecimal(String str){
    	if(!isNumber(str)){
    		return BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    	}
    	BigDecimal decimal = new  BigDecimal(str);
    	return decimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    /**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern regex = Pattern.compile("(-)?\\d*(.\\d*)?");
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

}
