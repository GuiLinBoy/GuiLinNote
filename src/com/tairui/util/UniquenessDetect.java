package com.tairui.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.BoHong.util.BaseHibernateDAO;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 检测某个值是否在表中唯一。
 * 
 * @author lehuo
 * @version v1.00 2014-05-24
 */

public class UniquenessDetect extends BaseHibernateDAO {

	private Log log = LogFactory.getLog(getClass());

	/**
	 * 
	 * <li>功能描述：使用HQL检测表中某列是否有某个值，不适用有删除标志的判断。
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一
	 */
	@SuppressWarnings("unchecked")
	public boolean detectByHql(String tableName, String propertyName,
			Object value) {
		Session session = getSession();
		try {
			String queryString = "from " + tableName + " as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}

	/**
	 * 
	 * <li>功能描述：使用HQL检测表中排除某条记录后，某列是否有某个值，不适用有删除标志的判断。
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param excludedID,int类型，要排除的ID
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一
	 */
	@SuppressWarnings("unchecked")
	public boolean detectExcludedIDByHql(String tableName, String propertyName,
			Object value, int excludedID) {

		Session session = getSession();
		try {
			String queryString = "from " + tableName + " as model where model."
					+ propertyName + "= ? and id != " + excludedID;
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}

	/**
	 * 
	 * <li>功能描述：使用SQL检测表中某列是否有某个值，不适用有删除标志的判断。
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一
	 */
	@SuppressWarnings("unchecked")
	public boolean detectBySql(String tableName, String propertyName,
			Object value) {

		Session session = getSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model." + propertyName + "= ?";
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}
	
	/**
	 * 
	 * <li>功能描述：使用SQL检测表中某列是否有某个值，不适用有删除标志的判断。(特殊校验，只校验统一社会信用代码)
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一
	 */
	@SuppressWarnings("unchecked")
	public boolean detectBySqlFORztb(String tableName, String propertyName,
			Object value, String symbolName, String symbolValue) {

		Session session = getSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model.suppType != 3 and  model." + propertyName + "= ?";
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}

	/**
	 * 
	 * <li>功能描述：使用SQL检测表中排除某条记录后，某列是否有某个值，不适用有删除标志的判断。
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param excludedID,int类型，要排除的ID
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一
	 */
	@SuppressWarnings("unchecked")
	public boolean detectExcludedIDBySql(String tableName, String propertyName,
			Object value, int excludedID) {
		Session session = getSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model." + propertyName
					+ "= ? and id != " + excludedID;
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}
	
	/**
	 * 
	 * <li>功能描述：使用SQL检测表中排除某条记录后，某列是否有某个值，不适用有删除标志的判断。(特殊校验，只校验统一社会信用代码)
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param excludedID,int类型，要排除的ID
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一
	 */
	@SuppressWarnings("unchecked")
	public boolean detectExcludedIDBySqlFORztb(String tableName, String propertyName,
			Object value, int excludedID, String symbolName, String symbolValue) {
		Session session = getSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model.suppType != 3 and model." + propertyName
					+ "= ? and id != " + excludedID;
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}

	/**
	 * 
	 * <li>功能描述：使用SQL检测表中某列是否有某个值，适用有删除标志的判断。
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param symbolName,String类型，标志列名称
	 * @param symbolValue,String类型，非删除标志值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一 boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean detectBySql(String tableName, String propertyName,
			Object value, String symbolName, String symbolValue) {
		Session session = getSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model." + propertyName
					+ "= ? and model." + symbolName + " = ?";
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, symbolValue);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}

	/**
	 * 
	 * <li>功能描述：功能描述：使用SQL检测表中排除某条记录后，某列是否有某个值，适用有删除标志的判断。
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param excludedID,int类型，要排除的ID
	 * @param symbolName,String类型，删除标志列名称
	 * @param symbolValue,String类型，非删除标志值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一 boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean detectExcludedIDBySql(String tableName, String propertyName,
			Object value, int excludedID, String symbolName, String symbolValue) {
		Session session = getSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model." + propertyName
					+ "= ? and id != " + excludedID + " and model."
					+ symbolName + " = ?";
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, symbolValue);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}

	/**
	 * 
	 * <li>功能描述：功能描述：使用SQL检测表中排除某条记录后，某列是否有某个值，适用有删除标志的判断。(定制方法不通用)
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param excludedID,int类型，要排除的ID
	 * @param company,String类型，所属公司
	 * @param symbolName,String类型，删除标志列名称
	 * @param symbolValue,String类型，非删除标志值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一 boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean detectExcludedIDBySql(String tableName, String propertyName,
			Object value, int excludedID, String company, String symbolName,
			String symbolValue) {
		Session session = getSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model." + propertyName
					+ "= ? and id != " + excludedID
					+ " and model.suoShuCompany = " + company + " and model."
					+ symbolName + " = ?";
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, symbolValue);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}

	/**
	 * 
	 * <li>功能描述：使用SQL检测表中某列是否有某个值，适用有删除标志的判断。(定制方法不通用)
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param company,String类型，所属公司
	 * @param symbolName,String类型，标志列名称
	 * @param symbolValue,String类型，非删除标志值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一 boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean detectBySql(String tableName, String propertyName,
			Object value, String company, String symbolName, String symbolValue) {
		Session session = getSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model." + propertyName
					+ "= ? and model.suoShuCompany = " + company + "  and model." + symbolName + " = ?";
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, symbolValue);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}
	
	/**
	 * 
	 * <li>功能描述：功能描述：使用SQL检测表中排除某条记录后，某列是否有某个值，适用有删除标志的判断。(定制方法不通用)
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param excludedID,int类型，要排除的ID
	 * @param symbolName,String类型，删除标志列名称
	 * @param symbolValue,String类型，非删除标志值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一 boolean
	 */
	/*@SuppressWarnings("unchecked")
	public boolean detectExcludedIDBySqlForCH(String tableName, String propertyName,
			Object value, int excludedID, String symbolName, String symbolValue) {
		Session session = getIMSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model." + propertyName
					+ "= ? and id != " + excludedID + " and model."
					+ symbolName + " = ?";
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, symbolValue);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}
	
	*//**
	 * 
	 * <li>功能描述：使用SQL检测表中某列是否有某个值，适用有删除标志的判断。(定制方法不通用)
	 * 
	 * @param tableName,String类型，表名
	 * @param propertyName,String类型，列名
	 * @param value,Object类型，待检测值
	 * @param symbolName,String类型，标志列名称
	 * @param symbolValue,String类型，非删除标志值
	 * 
	 * @return boolean 是否唯一,true:唯一；false：不唯一 boolean
	 *//*
	@SuppressWarnings("unchecked")
	public boolean detectBySqlForCH(String tableName, String propertyName,
			Object value, String symbolName, String symbolValue) {
		Session session = getIMSession();
		try {
			String queryString = "select * from " + tableName
					+ " as model where model." + propertyName
					+ "= ? and model." + symbolName + " = ?";
			SQLQuery queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, symbolValue);
			List list = queryObject.list();
			session.close();
			if (list.size() != 0) {
				return false;
			} else {
				return true;
			}
		} catch (RuntimeException re) {
			session.close();
			log.error(re);// 添加错误日志
			throw re;
		}
	}*/
}
