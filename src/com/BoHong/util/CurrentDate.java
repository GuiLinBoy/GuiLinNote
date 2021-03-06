package com.BoHong.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <li>项目名称: iWork
 * <li>功能描述: 日期和时间的处理�?
 * <li>公司: 石家庄融尚科�?��限公�?
 *
 * @author HY
 * @version 1.01
 */
public class CurrentDate {

	/**
	 * 
	 * <li>功能描述：取得系统时间�?
	 * @return
	 * Date
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 
	 * <li>功能描述：取得系统时间戳�?
	 * @return
	 * Timestamp
	 */
	public Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 
	 * <li>功能描述：将日期格式化为YYYY-MM-DD格式�?
	 * @return
	 * String
	 */
	public static String toDateString() {

		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = " ";
		try {
			dateString = simpledateformat.format(date);
		} catch (Exception exception) {
			return " ";
		}
		return dateString;
	}

	/**
	 * 将日期格式为YYYY-MM-DD格式的字符串去掉连字�?-"，并转化为INT型�?
	 * @return
	 */
	public static int toDateInt() {

		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = " ";
		try {
			dateString = simpledateformat.format(date);
		} catch (Exception exception) {
			return 0;
		}
		int dateInt = CurrentDate.formatDateToInt(dateString, "-");
		return dateInt;
	}

	/**
	 * 去掉时间字符串中的连字符，并转换为整�?
	 * @param dateString 待转换的时间字符�?
	 * @param split 连字�?
	 * @return 如果�?,则转换出�?
	 */
	public static int formatDateToInt(String dateString, String split) {

		StringBuffer stringBuffer = new StringBuffer();
		int dateInt = 0;
		try {
			String[] dateArray = dateString.split(split);
			//去掉字符串中的连字符
			for (int i = 0; i < dateArray.length; i++) {
				stringBuffer.append(dateArray[i]);
			}
			dateInt = Integer.parseInt(stringBuffer.toString());//转换为整�?
		} catch (Exception exception) {
			return 0;
		}
		return dateInt;
	}

	/**
	 * 将Integer类型格式化为yyyy-mm-dd型字符串
	 * @param dateInt 整形时间
	 * @return
	 */
	public static String formatDateToString(int dateInt) {
		try {
			//将Integer类型格式化为yyyy-mm-dd型字符串
			Integer integer = (Integer) dateInt;
			//将Integer型数据转换为字符串，构�?StringBuffer对象
			StringBuffer sb = new StringBuffer(integer.toString());
			//构�?用于保存转换结果的StringBuffer对象
			StringBuffer result = new StringBuffer();
			String temp = null;
			//当字符串长度�?个字符yyyy-mm-dd时转�?
			if (sb.length() == 8) {
				//截取�?个字�?�?个字符表示年�?
				temp = sb.substring(0, 4);
				//将截取后的字符添加到result对象�?
				result.append(temp);
				//添加连字�?-"
				result.append("-");
				//截取�?�?个字�?�?�?个字符表示月�?
				temp = sb.substring(4, 6);
				//将截取后的字符添加到result对象�?
				result.append(temp);
				//添加连字�?-"
				result.append("-");
				//截取前最�?个个字符,�?��2个字符表示日
				temp = sb.substring(6);
				//将截取后的字符添加到result对象�?
				result.append(temp);
			}
			//将格式化好的字符串返�?
			return result.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static String toDateString(Date date) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = " ";
		try {
			dateString = simpledateformat.format(date);
		} catch (Exception exception) {
			return " ";
		}
		return dateString;
	}

	public static int toDateInt(Date date) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = " ";
		try {
			dateString = simpledateformat.format(date);
		} catch (Exception exception) {
			return 0;
		}
		int dateInt = CurrentDate.formatDateToInt(dateString, "-");
		return dateInt;
	}

	/**
	 * 
	 * <li>功能描述：去掉日期字符串中的连字符�?
	 * @param dateString 日期字符�?
	 * @param split 连字�?
	 * @return
	 * String
	 */
	public static String formatDateToString(String dateString, String split) {

		StringBuffer stringBuffer = new StringBuffer();
		try {
			String[] dateArray = dateString.split(split);
			for (int i = 0; i < dateArray.length; i++) {
				stringBuffer.append(dateArray[i]);
			}
		} catch (Exception exception) {
			return "";
		}
		return stringBuffer.toString();
	}

	public static String toTimeString() {

		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm:ss");
		String timeString = " ";
		try {
			timeString = simpledateformat.format(date);
		} catch (Exception exception) {
			return " ";
		}
		return timeString;
	}

	public static String toTimeString(Date date) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm:ss");
		String timeString = " ";
		try {
			timeString = simpledateformat.format(date);
		} catch (Exception exception) {
			return " ";
		}
		return timeString;
	}

	public static String toTimeMillisString(Date date) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm:ss:SSS");
		String timeString = " ";
		try {
			timeString = simpledateformat.format(date);
		} catch (Exception exception) {
			return " ";
		}
		return timeString;
	}

	//date1和date2的时间间�?
	//date1和date2的格式均为yyyy-MM-dd，均为String类型
	public static long getGapDays(String date1, String date2) {

		Date d1=null;
        Date d2=null;
        try {
            d1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            d2=new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

		Calendar c1 = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
        c1.setTime(d1);
        c2.setTime(d2);
		long l1 = c1.getTimeInMillis();

		long l2 = c2.getTimeInMillis();

		return ((l1 - l2) / (24 * 60 * 60 * 1000));
	}

	//date1和date2的时间间�?
	//date1和date2的格式均为yyyy-MM-dd HH:mm:ss，均为Date类型
	public static double getGapDays(Date date1, Date date2) {
		Calendar c1 = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
		c1.setTime(date1);
		c2.setTime(date2);
		long l1 = c1.getTimeInMillis();
		
		long l2 = c2.getTimeInMillis();

		return ((l1 - l2)*1.0 / (24 * 60 * 60 * 1000));
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
}