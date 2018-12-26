package com.tairui.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

/* btbear作品   欢迎光临 www.btbear.net*/
public class DateConvert extends DefaultTypeConverter {
	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
			new SimpleDateFormat("dd/MM/yyyy"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy/MM/dd")}; // 支持转换的日期格式

	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		// TODO Auto-generated method stub
		if (toType == Date.class) { // 浏览器向服务器提交时，进行String to Date的转换
			Date date = null;
			String dateString = null;
			String[] params = (String[]) value;
			dateString = params[0]; // 获取日期的字符串
			if (dateString.trim().length()>11) {
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					
					return format.parse(dateString.trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					return null;
				}
			}
			else {
				for (DateFormat format : ACCEPT_DATE_FORMATS) {
					try {
						System.out.println("========================"+format.parse(dateString.trim()));
						return format.parse(dateString.trim()); // 遍历日期支持格式，进行转换
					} catch (Exception e) {
						continue;
					}
				}
			}
			
			return null;
		} else if (toType == String.class) { // 服务器向浏览器输出时，进行Date to
												// String的类型转换
			Date date = (Date) value;
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date); // 输出的格式是yyyy-MM-dd
		}
		return null;
	}

	/*
	 * public Object convertValue(Map context,Object value,Class totype) { if
	 * (totype==Date.class) { String[] params=(String [])value; SimpleDateFormat
	 * bartDateFormat = new SimpleDateFormat("yyyy-MM-dd"); Date date = null;
	 * try { date = bartDateFormat.parse(params[0]); } catch (ParseException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return date; } else if(totype==String.class){ Date date1=(Date)value;
	 * return date1.toGMTString();
	 *  } return null; }
	 * 
	 */
}