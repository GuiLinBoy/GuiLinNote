package com.BoHong.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
/**
 * 
 * <li>功能描述: 下载文件，提供了URL传参和属性传值两种获取下载文件名的方法。
 *
 * @author lehuo
 * @version v 1.01 2012-02-02
 */
public class DownloadAction extends BaseSupportAction
{

	private String serverFileName;//服务器保存的文件名，系统名
	private String fileRealName;  //下载对话框中展示给用户的文件名
	
	/**
	 * 
	 * <li>功能描述：此方法对应的是struts.xml文件中的：<param name="contentDisposition">attachment;filename="${downloadFileName}" </param>
	 * 这个属性设置的是下载工具下载文件时显示的文件名， 要想正确的显示中文文件名，我们需要对fileName再次编码
	 * 否则中文名文件将出现乱码，或无法下载的情况。
	 * @return 下载工具下载文件时显示的文件名
	 * @throws UnsupportedEncodingException
	 * String
	 */
	public String getFileRealName() throws UnsupportedEncodingException	{		
		fileRealName = toUtf8String(getRequest(),fileRealName);		
		return fileRealName;
	}
	
	/**
	 * 
	 * <li>功能描述：此方法对应的是struts.xml文件中的： <param name="inputName">downloadFile</param> 
	 * 返回下载文件的流。
	 * @return
	 * InputStream
	 */
	public InputStream getInputStream() {
		try {

/*			String realPathString = "/"+getText("uploadFilePath","").trim()+"/" + serverFileName;// 不适合绝对地址
			return ServletActionContext.getServletContext().getResourceAsStream(realPathString);*/
			String realPathString = "D:/"+getText("uploadFilePath","").trim()+"/" + serverFileName;		
			return new FileInputStream(realPathString);

		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public String execute() throws Exception {
		
		try {
			//String realPathString = "/"+getText("uploadFilePath","").trim()+"/" + serverFileName;// 不适合绝对地址
			//InputStream in = ServletActionContext.getServletContext().getResourceAsStream(realPathString);
			String realPathString = "D:/"+getText("uploadFilePath","").trim()+"/" + serverFileName;		
			InputStream in = new FileInputStream(realPathString);
			if(null == in){
				return "fileNotFound";
			}else {
				return SUCCESS;			
			}
		} catch (FileNotFoundException e) {
			return "fileNotFound";
		}
	}

	/**
	 * 
	 * <li>功能描述：设置下载对话框中展示给用户的文件名。
	 * @param fileRealName
	 * void
	 */
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	/**
	 * 
	 * <li>功能描述：设置服务器保存的文件名。
	 * @param fileName
	 * void
	 */
	public void setServerFileName(String serverFileName) {
		this.serverFileName = serverFileName;
	}
	//页面调用顺序：1.setter方法，2.execute方法，3.使用getter给struts.xml中的参数赋值。
	
	
	public String toUtf8String(String s) { 
	     
        StringBuffer sb = new StringBuffer(); 
        for (int i = 0; i < s.length(); i++) { 
            char c = s.charAt(i); 
            if (c >= 0 && c <= 255) { 
                sb.append(c); 
            } else { 
                byte[] b; 
                try { 
                    b = Character.toString(c).getBytes("utf-8"); 
                } catch (Exception ex) { 
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
 
    /** 
     * 根据不同浏览器将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名. 
     *  
     * @param fileRealName 
     *            原文件名 
     * @return 重新编码后的文件名 
     */ 
    public String toUtf8String(HttpServletRequest request, String fileRealName) { 
        String agent = request.getHeader("User-Agent"); 
        try { 
            if ((agent != null && agent.toLowerCase().indexOf("firefox") != -1)) { 
            	fileRealName = new String(fileRealName.getBytes("UTF-8"), "ISO8859-1"); 
            }else if (agent != null && agent.indexOf("MSIE") != -1) {
            	fileRealName = new String(fileRealName.getBytes("gbk"), "ISO8859-1"); 
			}else if (agent != null && agent.indexOf("Chrome") != -1) {
				fileRealName = new String(fileRealName.getBytes("UTF-8"), "ISO8859-1"); 
			}else { 
				fileRealName = new String(fileRealName.getBytes("UTF-8"), "ISO8859-1"); 
            } 
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace(); 
        } 
        return fileRealName; 
    }
}