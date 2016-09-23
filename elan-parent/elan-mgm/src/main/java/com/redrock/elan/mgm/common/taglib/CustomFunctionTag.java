/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:LS 2013-11-15
 */

package com.redrock.elan.mgm.common.taglib;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redrock.elan.common.core.constant.BaseEnum;
import com.redrock.elan.mgm.util.BigDecimalUtil;


/**
 * @author LS
 * @date 2013-11-15
 * @description：
 */

public class CustomFunctionTag {

	private static Logger _log = LoggerFactory.getLogger(CustomFunctionTag.class);

	private static boolean isBlank(String value) {
		if (value == null || "".equals(value.trim()) || "null".equals(value.trim()))
			return true;
		return false;
	}

	public static String formatMoney(String money) {
		if (isBlank(money))
			return "0.00";
		try {
			Long m = Long.parseLong(money);
			return BigDecimalUtil.formatMoney(m);
		} catch (Exception e) {
			_log.error("转换金额失败,money=" + money, e);
		}
		return "";
	}
	public static String format2point(String value) {
		if (isBlank(value))
			return "0.00";
		try {
			return BigDecimalUtil.formatDouble(Double.valueOf(value));
		} catch (Exception e) {
			_log.error("保留两位小数失败,money=" + value, e);
		}
		return "";
	}
	public static String format3point(String value) {
		if (isBlank(value))
			return "";
		try {
			return BigDecimalUtil.format3point(Integer.valueOf(value));
		} catch (Exception e) {
			_log.error("保留3位小数失败,value=" + value, e);
		}
		return "";
	}
	public static String formatMoneyBySearch(String money) {
		if (isBlank(money) )
			return "";
		try {
			Long m = Long.parseLong(money);
			return BigDecimalUtil.parseMoneyWith2Point(m);
		} catch (Exception e) {
			_log.error("转换金额失败,money=" + money, e);
		}
		return "";
	}
	public static String substrby15(String cardNo){
		if (isBlank(cardNo))
			return "";
		if(cardNo.length()>15){
			StringBuilder builder=new StringBuilder();
			builder.append(cardNo.substring(0,15)).append("......");
			return builder.toString();
		}else{
			return cardNo;
		}
	}
	public static String substrby10(String cardNo){
		if (isBlank(cardNo))
			return "";
		if(cardNo.length()>10){
			StringBuilder builder=new StringBuilder();
			builder.append(cardNo.substring(0,10)).append("......");
			return builder.toString();
		}else{
			return cardNo;
		}
	}
	public static String substrby4(String cardNo){
		if (isBlank(cardNo))
			return "";
		if(cardNo.length()>4){
			StringBuilder builder=new StringBuilder();
			builder.append(cardNo.substring(0,4)).append("......");
			return builder.toString();
		}else{
			return cardNo;
		}
	}
	public static String substrby6(String cardNo){
		if (isBlank(cardNo))
			return "";
		if(cardNo.length()>6){
			StringBuilder builder=new StringBuilder();
			builder.append(cardNo.substring(0,6)).append("......");
			return builder.toString();
		}else{
			return cardNo;
		}
	}
	public static String substrby8(String cardNo){
		if (isBlank(cardNo))
			return "";
		if(cardNo.length()>8){
			StringBuilder builder=new StringBuilder();
			builder.append(cardNo.substring(0,8)).append("......");
			return builder.toString();
		}else{
			return cardNo;
		}
	}
	public static String parseMoneyWith2Point(String money) {
		if (isBlank(money))
			return "0.00";
		try {
			Long m = Long.parseLong(money);
			return BigDecimalUtil.parseMoneyWith2Point(m);
		} catch (Exception e) {
			_log.error("转换金额失败,money=" + money, e);
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public static String getDisplayName(String enumClass, String value) {
		if (isBlank(enumClass))
			return "";
		try {
			Class<? extends BaseEnum<?, ?>> clz = (Class<? extends BaseEnum<?, ?>>) Class.forName(enumClass);
			if (BaseEnum.class.isAssignableFrom(clz)) {
				BaseEnum<?, ?>[] enums = clz.getEnumConstants();
				for (BaseEnum<?, ?> e : enums) {
					if (value != null && e.getValue().equals(value)) {
						return e.getDisplayName();
					}
				}
			} else {
				_log.error("枚举类" + enumClass + "没有继承BaseEnum.");
			}
		} catch (ClassNotFoundException e) {
			_log.error("枚举类" + enumClass + "没有找到.", e);
		}
		return "";
	}

	public static String getDisplayNameByEnumClass(Class<? extends BaseEnum<?, ?>> enumClass, String value) {
		if (enumClass == null)
			return "";
		if (BaseEnum.class.isAssignableFrom(enumClass)) {
			BaseEnum<?, ?>[] enums = enumClass.getEnumConstants();
			for (BaseEnum<?, ?> e : enums) {
				if (value != null && e.getValue().equals(value)) {
					return e.getDisplayName();
				}
			}
		} else {
			_log.error("枚举类" + enumClass + "没有继承BaseEnum.");
		}
		return "";
	}

	public static String formatYMD(String value) {
		if (isBlank(value))
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date dt = sdf.parse(value.trim());
			return new SimpleDateFormat("yyyy-MM-dd").format(dt);
		} catch (Exception e) {
			_log.error("格式化日期失败value:" + value, e);
		}
		return "";
	}
	public static void main(String[] args) {
		System.out.println(formatYMD("20131012"));
		
	}
}
