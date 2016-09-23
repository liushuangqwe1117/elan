/**
 * 版权所有(C) 2015 深圳市雁联计算系统有限公司
 * 创建:lyg 2015-8-24
 */

package com.redrock.elan.common.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lyg
 * @date 2015-8-24
 * @description：TODO
 */

public class DateUtils {

	/**
	 * 获取当前日期字符串，格式为yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrentTime1() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
	}
	
	/**
	 * 获取当前日期字符串，格式为yyyyMMdd
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	/**
	 * 將yyyyMMdd轉換成yyyy-MM-dd
	 * @param String
	 * @return String
	 * @throws ParseException
	 */
	public static String stringToString(String date) throws ParseException {
		StringBuilder sb = new StringBuilder(10);
		sb.append(date.substring(0,4)).append("-").append(date.substring(4,6)).append("-").append(date.substring(6,8));
		return sb.toString();
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
	 * yyyyMMdd日期参数 获取前一天的时间 yyyyMMdd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getLastTime(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = sdf.parse(date);
		Date lastDate = new Date(nowDate.getTime() - 24 * 60 * 60 * 1000);
		return sdf.format(lastDate);
	}
	/**
	 * yyyyMMdd to Date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date changeToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = null;
		try {
			nowDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return nowDate;
	}
	/**
	 * 比较日期
	 * 
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 比较日期
	 * 
	 * @param dt1
	 * @return
	 */
	public static int compare_date(Date dt1) {

		try {
			Date dt2 = new Date();
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取某天的开始时间  比如2016-02-26 00:00:00.0
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取某天的开始时间  比如2016-02-26 23:59:59.999
	 * @param date
	 * @return
	 */
	public static Date getEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	/**
	 * 获取当前时间Date
	 * @param date
	 * @return
	 */
	public static Date getCurDate() {
		return new Date();
	}
}
