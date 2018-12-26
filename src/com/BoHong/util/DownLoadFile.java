package com.BoHong.util;

import java.io.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 下载文件类。
 * 
 * @author lehuo
 * @version v1.00 2014-05-20
 */

public class DownLoadFile {

	private Log log = LogFactory.getLog(getClass());

	public DownLoadFile() {
		m_strErrMsg = null;
	}

	public String getFileContentType(String strFileExt) {
		String strContentType = null;
		if (strFileExt == null && (strFileExt = strFileExt.trim().toLowerCase()).length() == 0)
			strContentType = "text/plain";
		else if (strFileExt.equals(".sql"))
			strContentType = "text/txt";
		else if (strFileExt.equals(".html"))
			strContentType = "text/html";
		else if (strFileExt.equals(".htm"))
			strContentType = "text/html";
		else if (strFileExt.equals(".xml"))
			strContentType = "text/xml";
		else if (strFileExt.equals(".xsl"))
			strContentType = "text/xml";
		else if (strFileExt.equals(".zip"))
			strContentType = "application/zip";
		else if (strFileExt.equals(".exe"))
			strContentType = "application/exec";
		else if (strFileExt.equals(".gif"))
			strContentType = "image/gif";
		else if (strFileExt.equals(".jpe"))
			strContentType = "image/jpeg";
		else if (strFileExt.equals(".jpeg"))
			strContentType = "image/jpeg";
		else if (strFileExt.equals(".jpg"))
			strContentType = "image/jpeg";
		else if (strFileExt.equals(".pdf"))
			strContentType = "application/pdf";
		else if (strFileExt.equals(".doc") || strFileExt.equals(".rtf"))
			strContentType = "application/msword";
		else if (strFileExt.equals(".ppt"))
			strContentType = "application/vnd.ms-powerpoint";
		else if (strFileExt.equals(".xls"))
			strContentType = "application/vnd.ms-excel";
		else if (strFileExt.equals(".ceb"))
			strContentType = "application/x-ceb";
		else
			strContentType = "application/octet-stream";
		return strContentType;
	}

	public boolean sendDownLoadFile(HttpServletResponse response, int intDldOrOpen, String strServerFileName, String strDispFileName) {
		File file = new File(strServerFileName);
		if (!file.exists() || file.isDirectory()) {
			m_strErrMsg = "\u8981\u4E0B\u8F7D\u7684\u6587\u4EF6\u4E0D\u5B58\u5728";
			return false;
		}
		String strFileExt = strServerFileName.toLowerCase();
		int intDotLoc = strFileExt.lastIndexOf('.');
		if (intDotLoc < 0)
			strFileExt = "";
		else
			strFileExt = strFileExt.substring(intDotLoc);
		for (intDotLoc = 0; strDispFileName.charAt(intDotLoc) == '.'; intDotLoc++)
			;
		if (intDotLoc > 0)
			strDispFileName = strDispFileName.substring(intDotLoc);
		if (strDispFileName.length() < strFileExt.length()
				|| !strDispFileName.substring(strDispFileName.length() - strFileExt.length()).equals(strFileExt))
			strDispFileName = strDispFileName + strFileExt;
		String strContentType = getFileContentType(strFileExt);
		response.reset();
		response.setContentType(strContentType);
		if (intDldOrOpen == 1)
			response.setHeader("Content-disposition", "attachment; filename=" + toUtf8String(strDispFileName));
		else
			response.setHeader("Content-disposition", "filename=" + toUtf8String(strDispFileName));
		try {
			byte buf[] = new byte[4096];
			FileInputStream inFile = new FileInputStream(file);
			int intRet = inFile.read(buf, 0, 4096);
			if (intRet > 0) {
				ServletOutputStream outPut = response.getOutputStream();
				for (; intRet >= 0; intRet = inFile.read(buf, 0, 4096))
					outPut.write(buf, 0, intRet);

				outPut.flush();
				outPut.close();
				outPut = null;
			}
			inFile.close();
		} catch (Exception ex) {
			log.error(ex);// 添加错误日志
			m_strErrMsg = ex.toString();
			return false;
		}
		return true;
	}

	private String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= '\377') {
				sb.append(c);
			} else {
				byte b[];
				try {
					b = (new Character(c)).toString().getBytes("utf-8");
				} catch (Exception ex) {
					//System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}

			}
		}

		return sb.toString();
	}

	public String GetErrorMsg() {
		return m_strErrMsg;
	}

	private String m_strErrMsg;
}
