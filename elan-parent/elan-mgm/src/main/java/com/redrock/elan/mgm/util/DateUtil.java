package com.redrock.elan.mgm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类.
 * 
 * @author 潘瑞峥
 * @date 2012-10-31
 */
public class DateUtil {
	/**
	 * 获取当前日期字符串，格式为yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrentTime1() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static String formatTime(String time) {
		if (time != null) {
			return time.substring(0, 2) + ":" + time.substring(2, 4);
		}
		return null;
	}

	/**
	 * 获取当前日期字符串，格式为yyyyMMdd
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public static String formatYMD(String date) {
		if (date != null) {
			String[] split = date.split("-");
			StringBuilder sb = new StringBuilder(8);
			for (String string : split) {
				sb.append(string);
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 获取当前时间的字符串，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getCurrentPrettyDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * yyyy-MM-dd转换成日期
	 * 
	 * @return Date
	 * @throws ParseException
	 */
	public static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date);
	}

	/**
	 * 将Date转换为datetime型
	 */
	public static String dateToDateTime(Date date) {

		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 将Date转换为yyyy-MM-dd
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 将yyyy-MM-dd转换为yyyyMMdd
	 */
	public static String dateStringToString(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 将yyyyMMdd转换为yyyy-MM-dd
	 */
	public static String dateStringToymd(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 将Date转换为yyyy年MM月
	 */
	public static String dateToYearMonth(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		return sdf.format(date);
	}

	/**
	 * 将yyyy年MM转换为yyyyMM
	 */
	public static String dateMonthToString(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy年MM");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMM");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 将Date转换为yyyy年MM月dd日
	 */
	public static String dateToYearMonthDay(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(date);
	}

	/**
	 * 将yyyy年MM月dd日转换为yyyyMMdd
	 */
	public static String dateYearToString(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 获取下一天凌晨时间
	 */
	public static Date findNestDateBegin(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取今日凌晨时间
	 */
	public static Date findTodayDateBegin(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取时间 yyyyMMdd格式
	 */
	public static String dateTOYYYYMMDD(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		return sdf.format(date);
	}

	/**
	 * 获取时间 根据自己指定的类型获取时间显示类型
	 */
	public static String getDateFormat(String dateFormat, Date date) {
		if (dateFormat == null || date == null)
			return null;
		return new SimpleDateFormat(dateFormat).format(date);
	}

	/**
	 * 获取指定时间 返回String
	 */
	public static String getAppointDateFormat(String dateFormat, Date date,
			Integer i) {
		if (dateFormat == null || date == null)
			return null;
		if (i == null)
			i = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, i);
		return new SimpleDateFormat(dateFormat).format(calendar.getTime());
	}

	/**
	 * 判断是否超过24小时
	 * 
	 * @param date1
	 * @param date2
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean judgmentDate(String date1, String date2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = sdf.parse(date1);
			Date end = sdf.parse(date2);
			long cha = end.getTime() - start.getTime();
			if (cha < 0) {
				return false;
			}
			double result = cha * 1.0 / (1000 * 60 * 60);
			if (result <= 24) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取指定时间 返回Date
	 */
	public static Date getAppointDateFormatDate(Date date, Integer i) {
		if (date == null)
			return null;
		if (i == null)
			i = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, i);
		return calendar.getTime();
	}

	/**
	 * 按日加减日期
	 * 
	 * @param date
	 *            ：日期
	 * @param num
	 *            ：要加减的日数
	 * @return：成功，则返回加减后的日期；失败，则返回null
	 */
	public static Date addDays(Date date, int num) {
		if (date == null) {
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, num);

		return c.getTime();
	}

	/**
	 * 按月加减日期
	 * 
	 * @param date
	 *            ：日期
	 * @param num
	 *            ：要加减的月数
	 * @return：成功，则返回加减后的日期；失败，则返回null
	 */
	public static Date addMonths(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, num);
		return c.getTime();
	}

	/**
	 * 按年加减日期
	 * 
	 * @param date
	 *            ：日期
	 * @param num
	 *            ：要加减的年数
	 * @return：成功，则返回加减后的日期；失败，则返回null
	 */
	public static Date addYears(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, num);
		return c.getTime();
	}

	/**
	 * 按秒 加减日期
	 * 
	 * @param date
	 *            ：日期
	 * @param num
	 *            ：要加减的秒
	 * @return：成功，则返回加减后的日期；失败，则返回null
	 */
	public static Date addSeconds(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, num);
		return c.getTime();
	}

	/**
	 * 按照指定格式转换日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	public static void main(String[] args) {
		// System.out.println(new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(addMonths(new
		// Date(),-1)));
		// QueryDateDto queryDateDto = getQueryDate("2");
		// System.out.println(queryDateDto.getStartTime());
		System.out.println(dateStringToString("2016-02-23"));
	}
}