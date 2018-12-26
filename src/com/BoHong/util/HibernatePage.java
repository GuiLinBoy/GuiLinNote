package com.BoHong.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

public class HibernatePage {

	private int pageCount;

	/**
	 * 
	 * <li>功能描述：按HQL查询方式分页。
	 * 
	 * @param strHql,查询的HQL语句
	 * @param first,分页起始记录
	 * @param pageSize,每页记录数
	 * 
	 * @return List 一页的数据
	 */

	public List selectHql(String strHql, int first, int pageSize) {
		Session session = HibernateSessionFactory.getSession();
		if (first <= 0) {
			first = 1;
		}
		try {
			Query hql = session.createQuery(strHql);
			hql.setFirstResult((first - 1) * pageSize);
			hql.setMaxResults(pageSize);
			List resultList = hql.list();
			return resultList;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * <li>功能描述：按SQL方式分页。
	 * 
	 * @param strSql,查询的SQL语句
	 * @param first,分页起始记录
	 * @param pageSize,每页记录数
	 * 
	 * @return List 一页的数据
	 */
	public List selectSql(String strSql, int first, int pageSize) {
		Session session = HibernateSessionFactory.getSession();
		if (first <= 0) {
			first = 1;
		}
		try {
			SQLQuery sql = session.createSQLQuery(strSql);
			sql.setFirstResult((first - 1) * pageSize);
			sql.setMaxResults(pageSize);

			List resultList = sql.list();
			return resultList;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * <li>功能描述：按SQL方式分页。
	 * 
	 * @param strSql,查询的SQL语句
	 * @param first,分页起始记录
	 * @param pageSize,每页记录数
	 * 
	 * @return List 一页的数据
	 */
	public List selectSql(String strSql, int first, int pageSize, List paramList) {
		Session session = HibernateSessionFactory.getSession();
		if (first <= 0) {
			first = 1;
		}
		try {
			SQLQuery sql = session.createSQLQuery(strSql);

			for (int i = 0; i < paramList.size(); i++) {
				sql.setParameter(i, "%" + paramList.get(i) + "%");
			}

			sql.setFirstResult((first - 1) * pageSize);
			sql.setMaxResults(pageSize);

			List resultList = sql.list();
			return resultList;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * <li>功能描述：按SQL方式分页，强制转换成指定对象类型。
	 * 
	 * @param strSql,查询的SQL语句 注意如果不是*（全选），必须加上id
	 * 
	 * @param first,分页起始记录
	 * @param pageSize,每页记录数
	 * @param obj,Class类型，带转换的vo类型
	 * 
	 * @return List 一页的数据
	 */
	public List selectSql(String strSql, int first, int pageSize, Class obj) {
		Session session = HibernateSessionFactory.getSession();
		if (first <= 0) {
			first = 1;
		}
		try {
			SQLQuery sql = session.createSQLQuery(strSql);
			sql.setFirstResult((first - 1) * pageSize);
			sql.setMaxResults(pageSize);
			// List resultList = sql.addEntity(obj).list();// 强制转化类型

			sql.setResultTransformer(Transformers.aliasToBean(obj));
			List resultList = sql.list();

			return resultList;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * <li>功能描述：按SQL方式分页，强制转换成指定对象类型。
	 * 
	 * @param strSql,查询的SQL语句 注意如果不是*（全选），必须加上id
	 * 
	 * @param first,分页起始记录
	 * @param pageSize,每页记录数
	 * @param obj,Class类型，带转换的vo类型
	 * 
	 * @return List 一页的数据
	 */
	public List selectSql(String strSql, int first, int pageSize, Class obj, List paramList) {
		Session session = HibernateSessionFactory.getSession();
		if (first <= 0) {
			first = 1;
		}
		try {
			SQLQuery sql = session.createSQLQuery(strSql);

			for (int i = 0; i < paramList.size(); i++) {
				sql.setParameter(i, "%" + paramList.get(i) + "%");
			}

			sql.setFirstResult((first - 1) * pageSize);
			sql.setMaxResults(pageSize);
			// List resultList = sql.addEntity(obj).list();// 强制转化类型

			sql.setResultTransformer(Transformers.aliasToBean(obj));
			List resultList = sql.list();

			return resultList;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * <li>功能描述：按SQL方式分页，强制转换成指定对象类型(两个)。
	 * 
	 * @param strSql,查询的SQL语句
	 * @param first,分页起始记录
	 * @param pageSize,每页记录数
	 * @param obj1,Class类型，带转换的vo1类型。
	 * @param alias1,String类型，带转换的vo1的别名。
	 * @param obj2,Class类型，带转换的vo2类型。
	 * @param alias2,String类型，带转换的vo2的别名。
	 * 
	 * @return List 一页的数据
	 */
	public List selectSql(String strSql, int first, int pageSize, Class obj1, String alias1, Class obj2, String alias2) {
		Session session = HibernateSessionFactory.getSession();
		if (first <= 0) {
			first = 1;
		}
		try {

			SQLQuery sql = session.createSQLQuery(strSql);
			sql.setFirstResult((first - 1) * pageSize);
			sql.setMaxResults(pageSize);
			List resultList = sql.addEntity(alias1, obj1).addEntity(alias2, obj2).list();// 强制转化类型

			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * <li>功能描述：使用HQL方式查找总记录数。
	 * 
	 * @param strHql
	 * @param pageSize
	 * @return int
	 */
	public int selectRows(String strHql, int pageSize) {
		Session session = HibernateSessionFactory.getSession();
		try {
			String realHql = "select count(*)" + strHql;
			Query hql = session.createQuery(realHql);
			List pages = hql.list();
			int recordRows = Integer.parseInt(pages.get(0).toString());
			if (recordRows % pageSize == 0) {
				pageCount = recordRows / pageSize;
			} else {
				pageCount = recordRows / pageSize + 1;
			}
			return recordRows;
		} catch (Exception e) {
			return -1;
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * <li>功能描述：使用HQL方式查找总记录数。
	 * 
	 * @param strHql
	 * @param pageSize
	 * @return int
	 */
	public int selectRowsZR(String strHql, int pageSize, List paramList) {
		Session session = HibernateSessionFactory.getSession();
		try {
			String realHql = "select count(*)" + strHql;
			Query hql = session.createQuery(realHql);

			for (int i = 0; i < paramList.size(); i++) {
				hql.setParameter(i, "%" + paramList.get(i) + "%");
			}

			List pages = hql.list();
			int recordRows = Integer.parseInt(pages.get(0).toString());
			if (recordRows % pageSize == 0) {
				pageCount = recordRows / pageSize;
			} else {
				pageCount = recordRows / pageSize + 1;
			}
			return recordRows;
		} catch (Exception e) {
			return -1;
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * <li>功能描述：使用SQL方式查找总记录数。
	 * 
	 * @param strSql
	 * @param pageSize
	 * @return int
	 */
	public int selectRowsBySql(String strSql, int pageSize) {
		Session session = HibernateSessionFactory.getSession();
		try {
			String realSql = "select count(*) from (" + strSql + ") as baserecord";
			SQLQuery sql = session.createSQLQuery(realSql);
			List pages = sql.list();
			int recordRows = Integer.parseInt(pages.get(0).toString());
			if (recordRows % pageSize == 0) {
				pageCount = recordRows / pageSize;
			} else {
				pageCount = recordRows / pageSize + 1;
			}
			return recordRows;
		} catch (Exception e) {
			return -1;
		} finally {
			session.close();
		}

	}

	public int selectRowsBySql2(String realSql, int pageSize) {
		Session session = HibernateSessionFactory.getSession();
		try {
			SQLQuery sql = session.createSQLQuery(realSql);
			List pages = sql.list();
			int recordRows = Integer.parseInt(pages.get(0).toString());
			if (recordRows % pageSize == 0) {
				pageCount = recordRows / pageSize;
			} else {
				pageCount = recordRows / pageSize + 1;
			}
			return recordRows;
		} catch (Exception e) {
			return -1;
		} finally {
			session.close();
		}

	}

	public int selectRowsBySql2(String realSql, int pageSize, List paramList) {
		Session session = HibernateSessionFactory.getSession();
		try {
			SQLQuery sql = session.createSQLQuery(realSql);
			for (int i = 0; i < paramList.size(); i++) {
				sql.setParameter(i, "%" + paramList.get(i) + "%");
			}
			List pages = sql.list();
			int recordRows = Integer.parseInt(pages.get(0).toString());
			if (recordRows % pageSize == 0) {
				pageCount = recordRows / pageSize;
			} else {
				pageCount = recordRows / pageSize + 1;
			}
			return recordRows;
		} catch (Exception e) {
			return -1;
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * <li>功能描述：使用SQL方式查找总记录数(预处理)。
	 * 
	 * @param strSql
	 * @param pageSize
	 * @return int
	 */
	public int selectRowsBySqlZR(String strSql, int pageSize, List paramList) {
		Session session = HibernateSessionFactory.getSession();
		try {
			String realSql = "select count(*) from (" + strSql + ") as baserecord";
			SQLQuery sql = session.createSQLQuery(realSql);

			for (int i = 0; i < paramList.size(); i++) {
				sql.setParameter(i, "%" + paramList.get(i) + "%");
			}

			List pages = sql.list();
			int recordRows = Integer.parseInt(pages.get(0).toString());
			if (recordRows % pageSize == 0) {
				pageCount = recordRows / pageSize;
			} else {
				pageCount = recordRows / pageSize + 1;
			}
			return recordRows;
		} catch (Exception e) {
			return -1;
		} finally {
			session.close();
		}

	}

	public static int getPageCount(int recordRows, int pageSize) {
		if (recordRows % pageSize == 0) {
			return recordRows / pageSize;
		} else {
			return recordRows / pageSize + 1;
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
