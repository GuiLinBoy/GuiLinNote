package com.BoHong.util;

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
 * <li>技术支持:河北凝讯科技有限公司 <br/> <li>功能描述: 读取xml系统配置<br/> <li>创建时间：2016年5月14日 下午9:32:16<br/>
 * 
 * @author fudong
 * @version [v1.00]
 */

public class SysConfig {
	private Log log = LogFactory.getLog(getClass());

	/** xml文档对象 */
	private Document document = null;

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
			document = reader.read(ins);
		} catch (DocumentException e) {
			log.error(e);// 添加错误日志
		}
	}

	/**
	 * 描述: 获取指定的节点的Elements
	 * 
	 * @param nodePath String类型，节点路径
	 * @return List<Element> 返回类型
	 */
	@SuppressWarnings("unchecked")
	public List<Element> getDocumentElements(String nodePath) {
		List<Element> elements = document.selectNodes("/SysConfig/" + nodePath);
		return elements;
	}

	/**
	 * 描述: 获取指定节点的指定值
	 * 
	 * @param path String类型，节点路径
	 * @param attr String类型，节点属性
	 * @return String 返回类型
	 */
	public static String getValue(String path, String attr) {
		replace(path, ".", "/");// 字符串替换

		SysConfig sysConfig = new SysConfig();
		List<Element> elements = sysConfig.getDocumentElements(path);
		if ((elements == null) || (elements.size() < 1)) {
			return "";
		}
		return ((Element) elements.get(0)).attributeValue(attr);
	}

	/**
	 * 描述: 获取连接数据库采用的类型
	 * 
	 * @return String 返回类型
	 */
	public static String getDriverName() {
		SysConfig sysConfig = new SysConfig();
		List<Element> elements = sysConfig.getDocumentElements("DataSource");
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，连接数据库采用的类型。");
		}
		return ((Element) elements.get(0)).attributeValue("driverName");
	}

	/**
	 * 描述: 获取连接数据库的url
	 * 
	 * @return String 返回类型
	 */
	public static String getDBUrl() {
		SysConfig sysConfig = new SysConfig();
		List<Element> elements = sysConfig.getDocumentElements("DataSource");
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，连接数据库的url。");
		}
		return ((Element) elements.get(0)).attributeValue("dbUrl");
	}

	/**
	 * 描述: 获取连接数据库的用户名
	 * 
	 * @return String 返回类型
	 */
	public static String getUserName() {
		SysConfig sysConfig = new SysConfig();
		List<Element> elements = sysConfig.getDocumentElements("DataSource");
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，连接数据库的用户名。");
		}
		return ((Element) elements.get(0)).attributeValue("userName");
	}

	/**
	 * 描述: 获取连接数据库的密码
	 * 
	 * @return String 返回类型
	 */
	public static String getPassWord() {
		SysConfig sysConfig = new SysConfig();
		List<Element> elements = sysConfig.getDocumentElements("DataSource");
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，连接数据库的密码。");
		}
		return ((Element) elements.get(0)).attributeValue("passWord");
	}

	/**
	 * 描述: 动态获取配置文件中的数据，动态参数
	 * 
	 * @param parentNode String类型，保存信息的父节点名
	 * @param sonNode String类型，保存信息的子点名
	 * @param description String类型，抛出异常的描述信息
	 * @return String 返回类型
	 */
	public static String getConfigData(String parentNode, String sonNode, String description) {
		SysConfig sysConfig = new SysConfig();
		List<Element> elements = sysConfig.getDocumentElements(parentNode);
		if (elements == null) {
			throw new RuntimeException("运行时错误，系统未设定，" + description + "。");
		}
		return ((Element) elements.get(0)).attributeValue(sonNode);
	}

	/**
	 * 描述: 动态获取配置文件中的数据，动态参数
	 * 
	 * @param parentNode String类型，保存信息的父节点名
	 * @param sonNode String类型，保存信息的子点名
	 * @return String 返回类型
	 */
	public static String getConfigData(String parentNode, String sonNode) {
		SysConfig sysConfig = new SysConfig();
		List<Element> elements = sysConfig.getDocumentElements(parentNode);
		if (elements == null) {
			throw new RuntimeException("运行时错误。");
		}
		return ((Element) elements.get(0)).attributeValue(sonNode);
	}

	/**
	 * 描述: 读取IP地址和端口号，端口号如果为空只返回IP地址或域名
	 * 
	 * @return String 返回类型
	 */
	public static String getIPAndPort() {
		String urlString;
		String portString = getConfigData("ServerHost", "port", "端口读取错误");
		if (portString.trim().equals("")) {
			urlString = getConfigData("ServerHost", "ip", "ip读取错误");
		} else {
			urlString = getConfigData("ServerHost", "ip", "ip读取错误") + ":" + getConfigData("ServerHost", "port", "端口读取错误");
		}
		return urlString;
	}

	/**
	 * 描述: 字符串替换方法
	 * 
	 * @param Str String类型，待处理符号串
	 * @param initStr String类型，初始字符
	 * @param replaceStr String类型，要替换的字符
	 * @return String 返回类型
	 */
	public static String replace(String Str, String initStr, String replaceStr) {
		if (initStr.equals(replaceStr)) {
			return Str;
		}
		if ((Str == null) || (Str.equals(""))) {
			return "";
		}
		if ((initStr == null) || (initStr.equals(""))) {
			return Str;
		}
		if (replaceStr == null) {
			replaceStr = "";
		}
		String dst = Str;
		int idx = dst.indexOf(initStr);

		while (idx >= 0) {
			dst = dst.substring(0, idx) + replaceStr + dst.substring(idx + initStr.length(), dst.length());
			idx = dst.indexOf(initStr, idx + replaceStr.length());
		}
		return dst;
	}
}