package com.tairui.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 检测某个值是否在表中唯一。
 * 
 * @author lehuo
 * @version v1.00 2014-05-24
 */
public class EncryptMD5 {

	public static String MD5(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString().toUpperCase();
			/*System.out.println("MD5(" + sourceStr + ",32) = " + result);
			System.out.println("MD5(" + sourceStr + ",16) = "
					+ buf.toString().substring(8, 24));*/
		} catch (NoSuchAlgorithmException e) {
			//System.out.println(e);
		}
		return result;
	}
	public static void main(String[] args) {
		MD5("123456");
	}
}