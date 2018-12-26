package com.tairui.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 检测某个值是否在表中唯一。
 * 
 * @author lehuo
 * @version v1.00 2014-05-24
 */
public class DateUtils {
	/**
	 * getDateStr get a string with format YYYY-MM-DD from a Date object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	static public String getDateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	//
	public static Date dateChange(String date1) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date cDate;
		Date dd = null;
		try {
			cDate = df.parse(date1);
			dd = new Date(cDate.getTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dd;
	}

	/**
	 * 获取日期中的年格式为YYYY
	 * 
	 * @param date
	 * @return YYYY
	 */
	static public String getYear(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(date);
	}

	/**
	 * 获取日期中的年格式为YYYY
	 * 
	 * @param date
	 * @return MM
	 */
	static public String getMonth(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM");
		return format.format(date);
	}

	/**
	 * 获取日期中的日期格式为“dd日”
	 * 
	 * @param date
	 * @return “dd日”
	 */
	static public String getDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd日");
		return format.format(date);
	}

	/**
	 * 获取日期中的日期格式为“dd”
	 * 
	 * @param date
	 * @return “dd”
	 */
	static public Integer getDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd");
		return Integer.parseInt(format.format(date));
	}

	/**
	 * 获取日期中的日期格式为“MMdd”
	 * 
	 * @param date
	 * @return “dd”
	 */
	static public String getMothDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MMdd");
		return format.format(date);
	}

	/**
	 * 返回当前月份在
	 * 第几个季度
	 * 
	 * @param date
	 * @return 1，2，3，4
	 */
	static public String getMonInQua(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM");
		int month = Integer.parseInt(format.format(date));
		String quear = "";
		if (month <= 3) {
			quear = "1";
		} else if (month <= 6) {
			quear = "2";
		} else if (month <= 9) {
			quear = "3";
		} else {
			quear = "4";
		}

		return quear;
	}

	/**
	 * 返回当前月份在
	 * 第几个季度
	 * 
	 * @param date
	 * @return 1，2，3，4
	 */
	static public String getMonInQuaInt(int month) {
		String quear = "";
		if (month <= 3) {
			quear = "1";
		} else if (month <= 6) {
			quear = "2";
		} else if (month <= 9) {
			quear = "3";
		} else {
			quear = "4";
		}

		return quear;
	}

	/**
	 * yyyy年MM月dd日
	 * 
	 * @param date
	 * @return yyyy年MM月dd日
	 */
	static public String getDateStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	/**
	 * yyyyMMdd
	 * 
	 * @param date
	 * @return yyyyMMdd
	 */
	static public String getDateStrCompact(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}

	/**
	 * getDateStr get a string with format YYYY-MM-DD HH:mm:ss from a Date
	 * object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	static public String getDateTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	/**
	 * getDateStr get a string with format yyyyMMddHHmmss from a Date
	 * object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	static public String getDateTimeS(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	/**
	 * yyyy年MM月dd日 HH时mm分ss秒
	 * 
	 * @param date
	 * @return String
	 */
	static public String getDateTimeStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(date);
	}

	/**
	 * 获取当前时间，并根据pattern的格式输出
	 * 
	 * @param pattern
	 * @return String
	 */
	public static String getCurDateStr(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	/**
	 * 把以 'YYYY-MM-DD' 转化成一个日期（Date）
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseDate(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = format.parse(s, pos);
		return strtodate;

	}

	/**
	 * 把以 'YYYYMMDD' 转化成一个日期（Date）
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseDateY(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.parse(s);
	}

	/**
	 * yyyy年MM月dd日——> Date
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	static public Date parseDateC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.parse(s);
	}

	/**
	 * Parses text in 'YYYY-MM-DD' format to produce a date.
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseDateTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(s);
	}

	/**
	 * yyyy年MM月dd日 HH时mm分ss秒——>Date
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	static public Date parseDateTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.parse(s);
	}

	/**
	 * Parses text in 'HH:mm:ss' format to produce a time.
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.parse(s);
	}

	static public Date parseTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
		return format.parse(s);
	}

	/**
	 * 获取Date中的年，并以int型返回
	 * 
	 * @param s
	 * @return int
	 * @throws ParseException
	 */
	static public int yearOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(0, 4));
	}

	/**
	 * 获取月
	 * 
	 * @param s
	 * @return int
	 * @throws ParseException
	 */
	static public int monthOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(5, 7));
	}

	static public int dayOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(8, 10));
	}

	/**
	 * yy-mm-dd——>yyyy-mm-dd hh:00:00
	 * 
	 * @param date
	 * @param time
	 * @return String
	 */
	static public String getDateTimeStr(java.sql.Date date, double time) {
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		String dateStr = year + "-" + month + "-" + day;
		Double d = new Double(time);
		String timeStr = String.valueOf(d.intValue()) + ":00:00";

		return dateStr + " " + timeStr;
	}

	/**
	 * Get the total month from two date.
	 * 
	 * @param sd
	 *            the start date
	 * @param ed
	 *            the end date
	 * @return int month form the start to end date
	 * @throws ParseException
	 */
	static public int diffDateM(Date sd, Date ed) throws ParseException {
		return (ed.getYear() - sd.getYear()) * 12 + ed.getMonth()
				- sd.getMonth() + 1;
	}

	static public int diffDateD(Date sd, Date ed) throws ParseException {
		return Math.round((ed.getTime() - sd.getTime()) / 86400000) + 1;
	}

	static public int diffDateM(int sym, int eym) throws ParseException {
		return (Math.round(eym / 100) - Math.round(sym / 100)) * 12
				+ (eym % 100 - sym % 100) + 1;
	}

	static public Date getNextMonthFirstDate(Date date) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.set(Calendar.DATE, 1);
		return new Date(scalendar.getTime().getTime());
	}

	static public Date getNextWeekFirstDate(Date date) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		scalendar.set(Calendar.DAY_OF_WEEK, 1);
		return new Date(scalendar.getTime().getTime());
	}

	static public Date getNextDate(Date date) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, 1);

		return new Date(scalendar.getTime().getTime());
	}

	static public java.sql.Date getFrontDateByDayCount(java.util.Date date,
			Integer dayCount) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, -dayCount);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	/**
	 *用于计算从date 开始的 dayCount 天后的日期
	 * 
	 * @param date
	 * @param dayCount
	 * @return Date型日期
	 * @throws ParseException
	 */
	static public java.sql.Timestamp getAfterDateByDayCount(
			java.sql.Timestamp date, Integer dayCount) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, dayCount);
		return new java.sql.Timestamp(scalendar.getTime().getTime());
	}

	/**
	 * 计算从date（Long）开始的 dayCount天后的日期
	 * 
	 * @param date
	 * @param dayCount
	 * @return Long型的日期
	 * @throws ParseException
	 */
	static public Long getAfterDateByDayCount(Long date, Integer dayCount)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(new Date(date));
		scalendar.add(Calendar.DATE, dayCount);
		return scalendar.getTime().getTime();
	}

	/**
	 * 计算从date（Long）开始的 dayCount天后的日期
	 * 
	 * @param date
	 * @param dayCount
	 * @return Long型的日期
	 * @throws ParseException
	 */
	static public Date getAfterDateByDayCount(Date date, Integer dayCount)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, dayCount);
		return scalendar.getTime();
	}

	static public Timestamp getTimestamp() {
		return Timestamp.valueOf(getTodayAndTime());
	}

	/**
	 * Get first day of the month.
	 * 
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return Date first day of the month.
	 * @throws ParseException
	 */
	static public Date getFirstDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getFirstDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getLastDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	static public Date getLastDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	/**
	 * getToday get todat string with format YYYY-MM-DD from a Date object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */

	static public String getTodayStr() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStr(calendar.getTime());
	}

	static public Date getToday() throws ParseException {
		return new Date(System.currentTimeMillis());
	}

	static public String getTodayAndTime() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}

	static public String getTodayC() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStrC(calendar.getTime());
	}

	static public int getThisYearMonth() throws ParseException {
		Date today = Calendar.getInstance().getTime();
		return (today.getYear() + 1900) * 100 + today.getMonth() + 1;
	}

	static public int getYearMonth(Date date) throws ParseException {
		return (date.getYear() + 1900) * 100 + date.getMonth() + 1;
	}

	// 获取相隔月数
	static public long getDistinceMonth(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long monthCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			monthCount = (d2.getYear() - d1.getYear()) * 12 + d2.getMonth()
					- d1.getMonth();
			// dayCount = (d2.getTime()-d1.getTime())/(30*24*60*60*1000);

		} catch (ParseException e) {
			//System.out.println("Date parse error!");
			// throw e;
		}
		return monthCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long dayCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			//System.out.println("Date parse error!");
			// throw e;
		}
		return dayCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(Date beforedate, Date afterdate)
			throws ParseException {
		long dayCount = 0;

		try {
			dayCount = (afterdate.getTime() - beforedate.getTime())
					/ (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			// System.out.println("Date parse error!");
			// // throw e;
		}
		return dayCount;
	}

	static public long getDistinceDay(java.sql.Date beforedate,
			java.sql.Date afterdate) throws ParseException {
		long dayCount = 0;

		try {
			dayCount = (afterdate.getTime() - beforedate.getTime())
					/ (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			// System.out.println("Date parse error!");
			// // throw e;
		}
		return dayCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(String beforedate) throws ParseException {
		return getDistinceDay(beforedate, getTodayStr());
	}

	// 获取相隔时间数
	static public long getDistinceTime(String beforeDateTime,
			String afterDateTime) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long timeCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

		} catch (ParseException e) {
			//System.out.println("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	// 获取相隔时间数
	static public long getDistinceTime(String beforeDateTime)
			throws ParseException {
		return getDistinceTime(beforeDateTime, new Timestamp(System
				.currentTimeMillis()).toLocaleString());
	}

	/**
	 * <li>功能描述：获取上个月的月份
	 * @param month
	 * @return
	 * String 
	 */
	public static String getFrontMonth(String month) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date date = null;
		try {
			date = format.parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, -1);
		date = scalendar.getTime();
		try {
			return String.valueOf(getYearMonth(date));
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 得到当前月份 yyyyMM
	 * @param date
	 * @return
	 * String
	 */
	public static String getCurrent(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(date);
	}

	/**
	 * <li>功能描述：获取上个月的月份
	 * @param month
	 * @return
	 * String 
	 */
	public static String getFrontMonths(String month) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = format.parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, -1);
		date = scalendar.getTime();
		try {
			return String.valueOf(getYearMonth(date));
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws ParseException {
		Calendar b = Calendar.getInstance();
		int lastDay = b.getActualMaximum(Calendar.DAY_OF_MONTH);
		int now = b.get(Calendar.DAY_OF_MONTH);
		String date2 = "";
		if (now == lastDay) {
			date2 = b.get(Calendar.YEAR) + "-" + (b.get(Calendar.MONTH) + 2)
					+ "-" + 1;

		}
		//System.out.println(now);
		//System.out.println(date2);

		//System.out.println(DateUtils.getThisYearMonth());
		//System.out.println(DateUtils.getMonth(new Date()));
	}
}
