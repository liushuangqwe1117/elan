/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:LS 2013-11-15
 */

package com.redrock.elan.portal.taglib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redrock.elan.common.core.constant.BaseEnum;
import com.redrock.elan.portal.util.BigDecimalUtil;

/**
 * @author LS
 * @date 2013-11-15
 * @description：TODO
 */

public class CustomFunctionTag {

	private static Logger _log = LoggerFactory
			.getLogger(CustomFunctionTag.class);

	private static boolean isBlank(String value) {
		if (value == null || "".equals(value.trim())
				|| "null".equals(value.trim()))
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

	public static String substrby15(String cardNo) {
		if (isBlank(cardNo))
			return "";
		if (cardNo.length() > 15) {
			StringBuilder builder = new StringBuilder();
			builder.append(cardNo.substring(0, 15)).append("......");
			return builder.toString();
		} else {
			return cardNo;
		}
	}

	public static String substrby10(String cardNo) {
		if (isBlank(cardNo))
			return "";
		if (cardNo.length() > 10) {
			StringBuilder builder = new StringBuilder();
			builder.append(cardNo.substring(0, 10)).append("......");
			return builder.toString();
		} else {
			return cardNo;
		}
	}

	public static String substrby4(String cardNo) {
		if (isBlank(cardNo))
			return "";
		if (cardNo.length() > 4) {
			StringBuilder builder = new StringBuilder();
			builder.append(cardNo.substring(0, 4)).append("......");
			return builder.toString();
		} else {
			return cardNo;
		}
	}

	public static String substrby6(String cardNo) {
		if (isBlank(cardNo))
			return "";
		if (cardNo.length() > 6) {
			StringBuilder builder = new StringBuilder();
			builder.append(cardNo.substring(0, 6)).append("......");
			return builder.toString();
		} else {
			return cardNo;
		}
	}

	public static String substrby8(String cardNo) {
		if (isBlank(cardNo))
			return "";
		if (cardNo.length() > 8) {
			StringBuilder builder = new StringBuilder();
			builder.append(cardNo.substring(0, 8)).append("......");
			return builder.toString();
		} else {
			return cardNo;
		}
	}

	public static String substr(String str, int len) {
		if (isBlank(str))
			return null;
		String regEx = "[\\u4e00-\\u9fa5]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find()) {
			if (str.length() > len) {
				StringBuilder builder = new StringBuilder();
				builder.append(str.substring(0, len)).append("...");
				return builder.toString();
			} else {
				return str;
			}
		} else {
			if (str.length() > 2 * len) {
				StringBuilder builder = new StringBuilder();
				builder.append(str.substring(0, 2 * len)).append("...");
				return builder.toString();
			} else {
				return str;
			}
		}
	}

	public static String parseMoneyWith2Point(String money) {
		if (isBlank(money))
			return "0.00";
		Long m = null;
		try {
			m = Long.parseLong(money);
		} catch (Exception e) {
			try {
				m = new Double(money).longValue();
			} catch (Exception ex) {
				_log.error("转换金额失败,money=" + money, ex);
			}
		}
		return BigDecimalUtil.parseMoneyWith2Point(m);
	}

	public static String getDisplayNameByEnumClass(
			Class<? extends BaseEnum<?, ?>> enumClass, String value) {
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
		return formatYYMMDD(value, "yyyyMMdd", "yyyy-MM-dd");
	}

	public static String formatYMD2(String value) {
		if (isBlank(value))
			return "";
		String str = null;
		if (8 == value.length()) {
			str = "yyyyMMdd";
		} else if (14 == value.length()) {
			str = "yyyyMMddHHmmss";
		}
		return formatYYMMDD(value, str, "yyyy/MM/dd HH:mm:ss");
	}

	public static String formatYMD3(String value) {
		return formatYYMMDD(value, "yyyyMMdd", "yyyy/MM/dd");
	}

	public static String formatYYMMDD(String value, String parsePattern,
			String formatPattern) {
		if (isBlank(value))
			return "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(parsePattern);
		try {
			Date date = dateFormat.parse(value.trim());
			return new SimpleDateFormat(formatPattern).format(date);
		} catch (ParseException e) {
			_log.error("格式化日期失败value:" + value, e);
			return "";
		}
	}

	public static String substrBankCard(String value, int start, int end) {
		if (isBlank(value)) {
			return "";
		}
		int length = value.length();
		if (length <= start + end) {
			return value;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if (i >= start && i < length - end) {
				str.append('*');
			} else {
				str.append(value.charAt(i));
			}
		}
		return str.toString();
	}

	public static String tail(String value, int num) {
		if (isBlank(value)) {
			return null;
		}
		int length = value.length();
		if (length <= num) {
			return value;
		}
		return value.substring(value.length() - num);
	}

	public static String bankCard(String value, int bit) {
		if (isBlank(value)) {
			return null;
		}
		int length = value.length();
		int part = length / bit;
		if (length <= part) {
			return value;
		}
		int remainder = length % bit;
		if (remainder == 0) {
			part -= 1;
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < part; i++) {
			for (int x = 0; x < bit; ++x) {
				str.append('*');
			}
			str.append(' ');
		}
		str.append(value.substring(part * bit));
		return str.toString();
	}

	public static void main(String[] args) {
		System.out.println(formatYMD("20131012"));
	}
}
