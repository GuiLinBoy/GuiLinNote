package com.tairui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.opensymphony.xwork2.ActionSupport;

public class DateTools extends ActionSupport {

	private static final String Success = null;

	/**
	 * 
	 * 功能描述：日期转数字。
	 * 日期格式：yyyy/mm/dd hh/mm/ss
	 * 			yyyy年mm月dd日 hh时mm分ss秒  
	 *			yyyy/mm/dd  
	 *			yyyy年mm月dd日
	 * @return long类型的数值: 转换成功；  0: 转换失败； 
	 * @author 琳
	 * 
	 */
	public static long dateToLong (String in) {
        try {
        	Calendar cal = null;
        	SimpleDateFormat format = null;
        	String replaceString = null;
        	if (in.length() == 10) {//补位
        		in = in + " 00/00/00";
			}else if (in.length() == 11) {
				in = in + " 00/00/00";
			}
        	replaceString = in.substring(4, 5);//统一日期格式
        	in = in.replaceAll(replaceString, "/");
        	replaceString = in.substring(7, 8);
    		in = in.replaceAll(replaceString, "/");
    		if (in.length()==20 || in.length() == 21) {
    			replaceString = in.substring(10, 11);
	    		in = in.replaceAll(replaceString, "");
			}
    		replaceString = in.substring(13, 14);
    		in = in.replaceAll(replaceString, "/");
    		replaceString = in.substring(16, 17);
    		in = in.replaceAll(replaceString, "/");
        	if (in.length() == 21) {
	    		replaceString = in.substring(19, 20);
	    		in = in.replaceAll(replaceString, "");
			}
        	format = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");//格式化
			Date date = format.parse(in);
			cal = Calendar.getInstance();
			cal.setTime(date);
        	return cal.getTimeInMillis();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 
	 * 功能描述：数字转日期。
	 * 日期格式：yyyy/MM/dd HH:mm:ss
	 * 
	 * @return string类型的日期: 转换成功；  error: 转换失败； 
	 * @author 琳
	 * 
	 */
	public String longToDate(long on) {
		try {
			Calendar cal = null;
			SimpleDateFormat format = null;
			Date dat=new Date(on);  
			//GregorianCalendar gc = new GregorianCalendar();   
			cal = Calendar.getInstance();
			cal.setTime(dat);  
			format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			String timeString = format.format(cal.getTime());  
			//System.out.println(sb);  
			return timeString;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 
	 * 功能描述：毫秒转小时。
	 * 格式：long
	 * 
	 * @return long类型数值: 转换成功；  0: 转换失败； 
	 * @author 琳
	 * 
	 */
	public long longToDays(long in) {
		try {
			Long on = in /1000 /60 /60;
			return on;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
