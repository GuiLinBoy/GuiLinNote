package com.tairui.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.BoHong.util.BaseHibernateDAO;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 添加操作日志和登录日志。
 * 
 * @author lehuo
 * @version v1.00 2014-05-20
 */

public class AddLog {
	private Log log = LogFactory.getLog(getClass());

	private static BaseHibernateDAO baseDAO = new BaseHibernateDAO();

	/**
	 * 
	 * <li>功能描述：添加操作日志。注意：配置的表结构必须与参数顺序保持一致。
	 * 
	 * @param userName,String类型，操作账号
	 * @param realName,String类型，操作人真实姓名
	 * @param company,String类型，操作人公司
	 * @param content,String类型，操作内容
	 * @param operation,String类型，操作类型
	 * 
	 * @return boolean
	 */
	public boolean addOperateLog(String userName, String realName, String company, String content, String operation) {
		// 从配置文件获取操作日志表结构
		String sql = "insert into operationLog (userName,realName,suoShuCompany,operationType,operationContent,operationTime) values('"
				+ userName
				+ "','"
				+ realName
				+ "','"
				+ company
				+ "','"
				+ operation
				+ "','"
				+ content
				+ "','"
				+ getDateTimeStr(new Date()) + "')";

		try {
			if (this.executeSql(sql)) {
				// System.out.println("添加操作日志成功了！");
				return true;
			} else {
				// System.out.println("添加操作日志失败了！");
				return false;
			}
		} catch (RuntimeException e) {
			log.error(e);// 添加错误日志
			e.printStackTrace();
			// System.out.println("添加操作日志失败了！在添加过程中发生了异常！！！");
			return false;
		}
	}

	/**
	 * 
	 * <li>功能描述：添加登录日志。注意：配置的表结构必须与参数顺序保持一致。
	 * 
	 * @param userName,String类型，登录人账号
	 * @param realName,String类型，登录人真实姓名
	 * @param company,String类型，分公司
	 * @param ydordw,int类型，
	 * @param request,HttpServletRequest类型，当前的请求对象
	 * 
	 * @return boolean
	 */
	public boolean addLoginLog(String userName, String realName, String company, String loginType, HttpServletRequest request) {
		// 从配置文件获取登录日志表结构
		String sql = "insert into loginLog (userName,realName,suoShuCompany,loginTime,loginIp,loginType) values ('"
				+ userName + "','" + realName + "','" + company + "','" + getDateTimeStr(new Date()) + "','"
				+ getIpAddr(request) + "','" + loginType + "')";
		try {
			if (this.executeSql(sql)) {
				return true;
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			log.error(e);// 添加错误日志
			e.printStackTrace();
			return false;
		}

	}

	// -----------------------------------------------------
	// -------------------------工具方法----------------------
	// -----------------------------------------------------

	/**
	 * <li>功能描述：用于执行传入SQL语句。
	 * 
	 * @param sql,String类型，待执行的SQL语句。例如：String
	 *            sql = "insert into caozuo (caozuo) values('" + caozuo + "')";
	 * 
	 * @return boolean
	 */
	public boolean executeSql(String sql) {
		Transaction tx = null;// 事务处理开始，对表进行插入、删除、更新操作必须放入事务中
		Session session = baseDAO.getSession();
		tx = session.beginTransaction();
		int num = 0;
		Query query = session.createSQLQuery(sql);
		num = query.executeUpdate();
		tx.commit();
		session.flush();
		session.close();
		// 事务处理结束

		// 如果导入成功返回true，否则返回false
		if (num > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * <li>功能描述：得到当前的IP地址。
	 * 
	 * @param request,HttpServletRequest类型，当前的请求对象
	 * 
	 * @return String
	 */

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 
	 * <li>功能描述：得到时间戳
	 * 
	 * @param date,Date类型，传入当前的系统时间。
	 * 
	 * @return String
	 */
	public String getDateTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
