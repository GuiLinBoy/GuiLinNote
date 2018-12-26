package com.tairui.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 读取xml系统配置。
 * 
 * @author lehuo
 * @version v1.00 2014-05-20
 */

public class SysConfig {
	private Log log = LogFactory.getLog(getClass());

	private Document document = null;// xml文档对象

	public SysConfig() {
		// 利用流，打开指定文件
		InputStream ins = getClass().getClassLoader().getResourceAsStream("sysConfig.xml");
		if (ins == null) {
			try {
				ins = new FileInputStream("sysConfig.xml");
			} catch (FileNotFoundException e) {
				log.error("获取系统配置文件失败，系统找不到如下配置文件：sysConfig.xml");
				log.error(e);// 添加错误日志
			}
		}

		// 用解析器，读取xml文件
		SAXReader reader = new SAXReader();
		try {
			this.document = reader.read(ins);
		} catch (DocumentException e) {
			log.error(e);// 添加错误日志
		}
	}

	/**
	 * <li>功能描述：获取指定的节点的Elements
	 * 
	 * @param nodePath,String类型，节点路径
	 * 
	 * @return List<Element>
	 */

	@SuppressWarnings( { "unused", "unchecked" })
	public List<Element> getDocumentElements(String nodePath) {
		List<Element> elements = document.selectNodes("/SysConfig/" + nodePath);
		return elements;
	}

	/**
	 * <li>功能描述：获取指定节点的指定值
	 * 
	 * @param path,String类型，节点路径
	 * @param attr,String类型，节点属性
	 * 
	 * @return
	 */
	public static String getValue(String path, String attr) {
		replace(path, ".", "/");// 字符串替换

		List<Element> elements = new SysConfig().getDocumentElements(path);
		if ((elements == null) || (elements.size() < 1)) {
			return "";
		}
		return ((Element) elements.get(0)).attributeValue(attr);
	}

	// -----------------------------------------------------
	// ------------------读取数据库配置信息----------------------
	// -----------------------------------------------------

	/**
	 * <li>功能描述：获取连接数据库采用的类型
	 * 
	 * @return String
	 */
	public static String getDriverName() {
		SysConfig config = new SysConfig();
		List<Element> elements = config.getDocumentElements("DataSource");
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，连接数据库采用的类型。");
		}
		return ((Element) elements.get(0)).attributeValue("driverName");
	}

	/**
	 * <li>功能描述：获取连接数据库的url
	 * 
	 * @return String
	 */
	public static String getDBUrl() {
		SysConfig config = new SysConfig();
		List<Element> elements = config.getDocumentElements("DataSource");
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，连接数据库的url。");
		}
		return ((Element) elements.get(0)).attributeValue("dbUrl");
	}

	/**
	 * <li>功能描述：获取连接数据库的用户名
	 * 
	 * @return String
	 */
	public static String getUserName() {
		SysConfig config = new SysConfig();
		List<Element> elements = config.getDocumentElements("DataSource");
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，连接数据库的用户名。");
		}
		return ((Element) elements.get(0)).attributeValue("userName");
	}

	/**
	 * <li>功能描述：获取连接数据库的密码
	 * 
	 * @return String
	 */
	public static String getPassWord() {
		SysConfig config = new SysConfig();
		List<Element> elements = config.getDocumentElements("DataSource");
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，连接数据库的密码。");
		}
		return ((Element) elements.get(0)).attributeValue("passWord");
	}

	// -----------------------------------------------------
	// ------------------读取可变配置参数----------------------
	// -----------------------------------------------------

	/**
	 * <li>功能描述：动态获取配置文件中的数据
	 * 
	 * @param parentNode,String类型，保存信息的父节点名
	 * @param sonNode,String类型，保存信息的子点名
	 * @param description,String类型，抛出异常的描述信息
	 * 
	 * @return String
	 */
	public static String getConfigData(String parentNode, String sonNode, String description) {
		SysConfig config = new SysConfig();
		List<Element> elements = config.getDocumentElements(parentNode);
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，" + description + "。");
		}
		return ((Element) elements.get(0)).attributeValue(sonNode);
	}

	// -----------------------------------------------------
	// -------------------------字符串方法-------------------
	// -----------------------------------------------------

	/**
	 * 字符串替换
	 * 
	 * @param Str
	 * @param a
	 * @param b
	 * @return
	 */
	public static String replace(String Str, String a, String b) {
		if (a.equals(b)) {
			return Str;
		}
		if ((Str == null) || (Str.equals(""))) {
			return "";
		}
		if ((a == null) || (a.equals(""))) {
			return Str;
		}
		if (b == null) {
			b = "";
		}
		String dst = Str;
		int idx = dst.indexOf(a);

		while (idx >= 0) {
			dst = dst.substring(0, idx) + b + dst.substring(idx + a.length(), dst.length());
			idx = dst.indexOf(a, idx + b.length());
		}
		return dst;
	}

	/**
	 * <li>功能描述：读取IP地址和端口号
	 * 端口号如果为空只返回IP地址或域名
	 * @return String
	 */
	public static String getIPAndPort() {

		String urlString;
		String portString = getConfigData("TestServerHost", "port", "端口读取错误");
		if (portString.trim().equals("")) {
			urlString = getConfigData("TestServerHost", "ip", "ip读取错误");
		} else {
			urlString = getConfigData("TestServerHost", "ip", "ip读取错误") + ":"
					+ getConfigData("TestServerHost", "port", "端口读取错误");
		}
		return urlString;
	}
	
	// -----------------------------------------------------
	// -------------------------测试方法----------------------
	// -----------------------------------------------------

	public static void main(String[] args) {
		String aa = SysConfig.getConfigData("SysTest", "test1", "测试数据1");
		String bb = SysConfig.getConfigData("SysTest", "test2", "测试数据2");

		SysConfig ff = new SysConfig();
		ff.log.error("dasfdasfd");// 测试错误日志

		//System.out.println(aa + "====" + bb);
	}

}