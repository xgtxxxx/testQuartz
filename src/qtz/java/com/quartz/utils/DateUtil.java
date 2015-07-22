package com.quartz.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author xgt
 * 日期处理工具类
 * 2013-01-13 晚
 */
public final class DateUtil {

	public static final long SECOND = 1000;
	public static final long MINUTE = SECOND * 60;
	public static final long HOUR = MINUTE * 60;
	public static final long DAY = HOUR * 24;
	public static final long WEEK = DAY * 7;
	public static final long YEAR365 = DAY * 365;
	public static final long YEAR366 = DAY * 366;

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String DATE_MINUTE = "yyyy-MM-dd HH:mm";

	/**
	 * private constructor
	 */
	private DateUtil() {
	}
	/**
	 * 日期ID
	 * @return
	 */
	public static String getUUID(){
		Date d = new Date();
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(d);
	}
	
	/**
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String formatDate(Date date, String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(date);
	}
	/**
	 * default yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}
	/**
	 * default yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date){
		return new SimpleDateFormat(DATETIME_FORMAT).format(date);
	}

	/**
	 * @param s1
	 *            and s2 is like "yyyy-MM-dd";
	 * @return : 日期比较 s1与s2相隔几天 即s2-s1 =?
	 */
	public static int compareDate(String sDate, String bDate) {
		Date d1 = parseDate(sDate,DATE_FORMAT);
		Date d2 = parseDate(bDate,DATE_FORMAT);
		return compareDate(d1,d2);
	}
	/**
	 * 
	 * @param sMonth yyyyMM
	 * @param bMonth
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int compareMonth(String sMonth, String bMonth){
		Date d1 = parseDate(sMonth,"yyyyMM");
		Date d2 = parseDate(bMonth,"yyyyMM");
		int s = d1.getMonth()+1;
		int b = d2.getMonth()+1;
		int y1 = d1.getYear();
		int y2 = d2.getYear();
		if(y1<y2){
			b += (y2-y1)*12;
		}else if(y1>y2){
			s += (y1-y2)*12;
		}
		return b-s;
	}
	
	/**
	 * @return : 日期比较 s1与s2相隔几天 即s2-s1 =?
	 */
	public static int compareMinute(String sDate, String bDate) {
		Date d1 = parseDate(sDate,DATETIME_FORMAT);
		Date d2 = parseDate(bDate,DATETIME_FORMAT);
		return compareMinute(d1,d2);
	}

	/**
	 * 
	 * @param sDate
	 *            小日期
	 * @param bDate
	 *            大日期
	 * @return
	 */
	public static int compareDate(Date sDate, Date bDate) {
		int day = (int) ((bDate.getTime() - sDate.getTime()) / DAY);
		return day;

	}
	/**
	 * 判定时间是否在区间内
	 * @param now 目标时间
	 * @param max 最大时间
	 * @param min 最小时间
	 * @return
	 */
	public static boolean isBetween(Date now,Date max,Date min){
		long l1 = now.getTime();
		long l2 = max.getTime();
		long l3 = min.getTime();
		if(l1>=l3 && l1<=l2){
			return true;
		}
		return false;
	}
	
	public static int compareMinute(Date sDate, Date bDate){
		int minute = (int) ((bDate.getTime() - sDate.getTime()) / MINUTE);
		return minute;
	}

	/**
	 * @return now
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	/**
	 * today
	 * @return
	 */
	public static String getCurrentDay(){
		return formatDate(new Date());
	}
	/**
	 * now day+time
	 * @return
	 */
	public static String getCurrentDayTime(){
		return formatDateTime(new Date());
	}
	/**
	 * now day+time,精确到分钟
	 * @return
	 */
	public static String getCurrentDayMinute(){
		return formatDate(new Date(),DATE_MINUTE);
	}
	/**
	 * now 时分秒
	 * @return
	 */
	public static String getCurrentTime(){
		return formatDate(new Date(),TIME_FORMAT);
	}
	/**
	 * 根据指定格式解析日期
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String date, String format) {
		Date d = null;
		try {
			DateFormat df = new SimpleDateFormat(format);
			d = (Date) df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	/**
	 * 默认yyyy-MM-dd解析日期
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date){
		return parseDate(date,DATE_FORMAT);
	}
	/**
	 * 默认yyyy-MM-dd HH:mm:ss解析日期
	 * @param datetime
	 * @return
	 */
	public static Date parseDateTime(String datetime){
		return parseDate(datetime,DATETIME_FORMAT);
	}
	/**
	 * 将字符串解析成Timestamp格式日期
	 * @param date
	 * @return
	 */
	public static Timestamp parseTimestamp(String date){
		Date d = null;
		if(date!=null&&date.length()<=10){
			date = date+" 00:00:00";
		}
		d = parseDateTime(date);
		return new Timestamp(d.getTime());
	}
	/**
	 * 获取当前年
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  return c.get(Calendar.YEAR);
	}
	/**
	 * 获取当前月
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  return c.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获当前月的上一月(yyyy-MM)
	 * @return
	 */
	public static String getPreViewMonth() {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(new Date());
	     cal.add(Calendar.MONTH, -1);
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	     return sdf.format(cal.getTime());
	}
	/**
	 * 获取上月
	 * @param now 当前月
	 * @param format 格式，一般为yyyyMM和yyyy-MM
	 * @return
	 */
	public static String getPreViewMonth(String now,String format) {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(parseDate(now, format));
	     cal.add(Calendar.MONTH, -1);
	     SimpleDateFormat sdf = new SimpleDateFormat(format);
	     return sdf.format(cal.getTime());
	}
	
	/**
	 * 获取当前星期几
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int d = c.get(Calendar.DAY_OF_WEEK);
		if(d==1){
			return 7;
		}else{
			return d-1;
		}
	}

		 /**
		  * 
		  * @Title: getDay
		  * @Description:返回当前日
		  * @param date
		  * @return
		  */
	public static int getDay(Date date) {
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  return c.get(Calendar.DAY_OF_MONTH);
	}

		 /**
		  * 
		  * @Title: getHour
		  * @Description: 返回当前小时
		  * @param date
		  * @return
		  */
	public static int getHour(Date date) {
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  return c.get(Calendar.HOUR_OF_DAY);
	}

		 /**
		  * 
		  * @Title: getMinute
		  * @Description: 返回当前分钟
		  * @param date
		  * @return
		  */
	public static int getMinute(Date date) {
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  return c.get(Calendar.MINUTE);
	}

		 /**
		  * 
		  * @Title: getSecond
		  * @Description: 返回当前秒
		  * @param date
		  * @return
		  */
	public static int getSecond(Date date) {
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  return c.get(Calendar.SECOND);
	}

		 /**
		  * 
		  * @Title: getMillis
		  * @Description: 返回当前毫秒
		  * @param date
		  * @return
		  */
	public static long getMillis(Date date) {
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  return c.get(Calendar.MILLISECOND);
	}
	/**
	  * 
	  * @Title: addMonths
	  * @Description: 增加正数减去负数 几月后的日期
	  * @param date yyyyMM
	  * @param month
	  * @return
	  */
	public static String addMonths(String date, int month) {
		 String y = date.substring(0,4);
		 String m = date.substring(4);
		 int y1 = Integer.parseInt(y);
		 int m1 = Integer.parseInt(m);
		 int m2 = m1+month;
		 int m3 = m2%12;
		 int y2 = 0;
		 if(m3==0){
			 y2 = m2/12-1;
			 m3=12;
		 }else{
			 y2 = m2/12;
		 }
		 String m4 = "";
		 if(m3<10){
			 m4 = "0"+m3;
		 }else{
			 m4 = ""+m3;
		 }
		 String r = (y1+y2)+""+m4;
		 return r;
	}
	public static void main(String[] args) {
		String date = "201403";
		System.out.println(addMonths(date,-3));
	}
	 /**
	  * 
	  * @Title: addDate
	  * @Description: 增加正数减去负数 几天后的日期
	  * @param date
	  * @param day
	  * @return
	  */
	public static Date addDates(Date date, int day) {
	     Calendar c = Calendar.getInstance();
	     c.setTimeInMillis(date.getTime() + ((long) day) * DAY);
	     return c.getTime();
	}
	/**
	  * 
	  * @Title: addDate
	  * @Description: 增加正数减去负数 几天后的日期
	  * @param date
	  * @param day
	  * @return
	  */
	public static String addDates(String date, int day) {
	     Calendar c = Calendar.getInstance();
	     Date d = parseDate(date);
	     c.setTimeInMillis(d.getTime() + ((long) day) * DAY);
	     return formatDate(c.getTime());
	}
	/**
	  * 
	  * @Title: addHour
	  * @Description: 增加正数减去负数 几小时后的时间
	  * @param date
	  * @param day
	  * @return
	  */
	public static Date addHours(Date date, int hour) {
	     Calendar c = Calendar.getInstance();
	     c.setTimeInMillis(date.getTime() + ((long) hour) * HOUR);
	     return c.getTime();
	}
	/**
	  * 
	  * @Title: addHour
	  * @Description: 增加正数减去负数 N分钟后的时间
	  * @param date
	  * @param day
	  * @return
	  */
	public static Date addMinutes(Date date, int minute){
		Calendar c = Calendar.getInstance();
	    c.setTimeInMillis(date.getTime() + ((long) minute) * MINUTE);
	    return c.getTime();
	}
	
	 /**
	  * 
	  * @Title: getMonthBegin
	  * @Description: 返回某天所属月份的第一天
	  * @param strdate
	  * @return
	  */
	 public static String getMonthBegin(String date) {
	  String d = formatDate(parseDate(date, DATE_FORMAT), "yyyy-MM");
	  return d + "-01";
	 }
	 
	 /**
	  * 
	  * @Title: getMonthEnd
	  * @Description: 返回某天所属月份的最后一天
	  * @param strdate
	  * @return
	  */
	 public static String getMonthEnd(String date) {
	      Date d = parseDate(getMonthBegin(date),DATE_FORMAT);
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(d);
		  calendar.add(Calendar.MONTH, 1);
		  calendar.add(Calendar.DAY_OF_YEAR, -1);
		  return formatDate(calendar.getTime());
	 }
	 /**
	  * 获取当前月最后一天
	  * @return
	  */
	 public static String getCurrentMonthEnd(){
		 return getMonthEnd(getCurrentDay());
	 }

}