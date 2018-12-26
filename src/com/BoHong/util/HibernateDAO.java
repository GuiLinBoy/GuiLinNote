package com.BoHong.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

/**
 * 
 * <li>项目名称: 系统架构 <li>功能描述: 封装Hibernate方法。
 * 
 * @author lehuo
 * @version v1.00 2014-05-23
 */

public class HibernateDAO extends BaseHibernateDAO {

	private Log log = LogFactory.getLog(getClass());

	private int resultRows; // 总记录数
	private int pageCount; // 总页数

	private HibernatePage hibernatePage = new HibernatePage(); // 分页类

	/**
	 * 通过SQL语句查找结果集，结果集字段全部在一个VO对象中
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * @param obj,Class类型，带转换的vo类型。
	 * 
	 * 注意如果不是*（全选），必须加上id 例子：newsList =newsDao.findBySql(mySql,MyNews.class);
	 * 
	 * @return Object类型的list列表
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List findBySql(String queryString, Class obj) {
		Session session = getSession();
		try {
			SQLQuery sql = session.createSQLQuery(queryString);
			sql.setResultTransformer(Transformers.aliasToBean(obj));
			List tempList = sql.list();

			return tempList;
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * @Description: select * 的处理方法
	 * @param queryString
	 * @param obj
	 * @return 参数
	 * 
	 * @return List 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List findBySqlStart(String queryString, Class obj) {
		Session session = getSession();
		try {
			List tempList = session.createSQLQuery(queryString).addEntity(obj).list(); // 强制转成对象列表
			return tempList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List findBySqlStart(String queryString, Class obj, int pageNo, int pageSize) {
		Session session = getSession();
		if (pageNo <= 0) {
			pageNo = 1;
		}
		try {
			SQLQuery sql = session.createSQLQuery(queryString);
			sql.setFirstResult((pageNo - 1) * pageSize);
			sql.setMaxResults(pageSize);
			List resultList = sql.addEntity(obj).list();// 强制转化类型

			String realSql = "select count(*) from (" + queryString + ") as baserecord";
			sql = session.createSQLQuery(realSql);
			List pages = sql.list();
			resultRows = Integer.parseInt(pages.get(0).toString());
			if (resultRows % pageSize == 0) {
				pageCount = resultRows / pageSize;
			} else {
				pageCount = resultRows / pageSize + 1;
			}

			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List findBySqlStart(String queryString, Class obj, int pageNo, int pageSize, List paramList) {
		Session session = getSession();
		if (pageNo <= 0) {
			pageNo = 1;
		}
		try {
			SQLQuery sql = session.createSQLQuery(queryString);
			sql.setFirstResult((pageNo - 1) * pageSize);
			sql.setMaxResults(pageSize);
			
			for (int i = 0; i < paramList.size(); i++) {
				sql.setParameter(i, "%" + paramList.get(i) + "%");
			}
			
			List resultList = sql.addEntity(obj).list();// 强制转化类型
			
			String realSql = "select count(*) from (" + queryString + ") as baserecord";
			sql = session.createSQLQuery(realSql);
			for (int i = 0; i < paramList.size(); i++) {
				sql.setParameter(i, "%" + paramList.get(i) + "%");
			}
			List pages = sql.list();
			resultRows = Integer.parseInt(pages.get(0).toString());
			if (resultRows % pageSize == 0) {
				pageCount = resultRows / pageSize;
			} else {
				pageCount = resultRows / pageSize + 1;
			}

			return resultList;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 通过SQL语句查找结果集，结果集字段全部在一个VO对象中
	 * 
	 * @param queryString,String类型，SQL查找语句 注意如果不是*（全选），必须加上id
	 * 
	 * @param obj,Class类型，带转换的vo类型。
	 * 
	 * 例子：mySql = "select c.* from MyNews c , student s where s.class_id = c.id ";
	 * 例子：newsList =newsDao.findBySql(mySql,MyNews.class);
	 * 
	 * @return Object类型的list列表
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List findBySql(String queryString, Class obj, List paramList) {
		Session session = getSession();
		try {
			SQLQuery sql = session.createSQLQuery(queryString);

			for (int i = 0; i < paramList.size(); i++) {
				sql.setParameter(i, "%" + paramList.get(i) + "%");
			}

			// List tempList = sql.addEntity(obj).list();// 强制转成对象列表

			sql.setResultTransformer(Transformers.aliasToBean(obj));
			List tempList = sql.list();

			return tempList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * 通过SQL语句查找结果集，带有分页效果，结果集字段全部在一个VO对象中
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * @param obj,Class类型，带转换的vo类型。
	 * @param pageNo,当前页号
	 * @param pageSize,每页记录数
	 * 
	 * 例子：mySql = "select c.* from MyNews c , student s where s.class_id = c.id ";
	 * 例子：newsList =newsDao.findBySql(mySql,MyNews.class);
	 * 
	 * @return Object类型的list列表
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List findBySql(String queryString, Class obj, int pageNo, int pageSize) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize, obj);// 强制转成对象列表
			resultRows = hibernatePage.selectRowsBySql(queryString, pageSize);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 通过SQL语句查找结果集，带有分页效果，结果集字段全部在一个VO对象中
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * @param obj,Class类型，带转换的vo类型。
	 * @param pageNo,当前页号
	 * @param pageSize,每页记录数
	 * @param paramList,参数list
	 * 
	 * 例子：mySql = "select c.* from MyNews c , student s where s.class_id = c.id ";
	 * 例子：newsList =newsDao.findBySql(mySql,MyNews.class);
	 * 
	 * @return Object类型的list列表
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List findBySql(String queryString, Class obj, int pageNo, int pageSize, List paramList) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize, obj, paramList);// 强制转成对象列表
			resultRows = hibernatePage.selectRowsBySqlZR(queryString, pageSize, paramList);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	public List findBySql2(String queryString, String queryString2, Class obj, int pageNo, int pageSize) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize, obj);// 强制转成对象列表
			resultRows = hibernatePage.selectRowsBySql2(queryString2, pageSize);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	public List findBySql2(String queryString, String queryString2, Class obj, int pageNo, int pageSize, List paramList) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize, obj, paramList);// 强制转成对象列表
			resultRows = hibernatePage.selectRowsBySql2(queryString2, pageSize, paramList);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 
	 * @Description: 带排序的查询
	 * @param queryString
	 * @param orderString
	 * @param obj
	 * @param pageNo
	 * @param pageSize
	 * @return 参数
	 * 
	 * @return List 返回类型
	 * @throws
	 */

	public List findBySql(String queryString, String orderString, Class obj, int pageNo, int pageSize) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize, obj);// 强制转成对象列表
			resultRows = hibernatePage.selectRowsBySql(orderString, pageSize);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 
	 * @Description: 带排序的查询
	 * @param queryString
	 * @param orderString
	 * @param obj
	 * @param pageNo
	 * @param pageSize
	 * @return 参数
	 * 
	 * @return List 返回类型
	 * @throws
	 */

	public List findBySql(String queryString, String orderString, Class obj, int pageNo, int pageSize, List paramList) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize, obj, paramList);// 强制转成对象列表
			resultRows = hibernatePage.selectRowsBySqlZR(orderString, pageSize, paramList);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 
	 * @Description: 带排序的查询
	 * @param queryString
	 * @param pageNo
	 * @param pageSize
	 * @return 参数
	 * 
	 * @return List 返回类型
	 * @throws
	 */

	public List findBySql(String queryString, int pageNo, int pageSize, List paramList) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize, paramList);// 强制转成对象列表
			resultRows = hibernatePage.selectRowsBySqlZR(queryString, pageSize, paramList);
			pageCount = hibernatePage.getPageCount();
			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 通过SQL语句查找结果集，结果集在两个VO对象中
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * @param obj1,Class类型，带转换的vo1类型。
	 * @param alias1,String类型，带转换的vo1的别名。
	 * @param obj2,Class类型，带转换的vo2类型。
	 * @param alias2,String类型，带转换的vo2的别名。
	 * 
	 * 例子：mySql="SELECT {c.*},{s.*} FROM myBook c,company s WHERE s.id =c.id";
	 * testList=testDao.findBySql(mySql2,MyBook.class,"c", Company.class, "s");
	 * Object obj[]= (Object[]) testList.get(0); //得到的对象是个数组
	 * book=(MyBook)obj[0];//取出对象分量，并进行强制类型转换
	 * myCompany=(Company)obj[1];//取出对象分量，并进行强制类型转换
	 * 
	 * @return Object类型的list列表
	 * 
	 */

	public List findBySql(String queryString, Class obj1, String alias1, Class obj2, String alias2) {
		Session session = getSession();
		try {
			// 强制转成对象列表
			List tempList = session.createSQLQuery(queryString).addEntity(alias1, obj1).addEntity(alias2, obj2).list();

			return tempList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * 通过SQL语句查找结果集，带有分页效果，结果集在两个VO对象中
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * @param obj1,Class类型，带转换的vo1类型。
	 * @param alias1,String类型，带转换的vo1的别名。
	 * @param obj2,Class类型，带转换的vo2类型。
	 * @param alias2,String类型，带转换的vo2的别名。
	 * @param pageNo,当前页号
	 * @param pageSize,每页记录数
	 * @param mySql,String类型，计算记录条数的sql
	 * 
	 * 例子：sql="SELECT {c.*},{s.*} FROM myBook c,company s WHERE s.id =c.id";
	 * sql2 = "SELECT c.* FROM myBook c , company s WHERE s.id = c.id";
	 * testList=testDao.findBySql(sql,MyBook.class,"c",Company.class,"s",1,3,sql2);
	 * Object obj[]= (Object[]) testList.get(0); //得到的对象是个数组
	 * book=(MyBook)obj[0];//取出对象分量，并进行强制类型转换
	 * myCompany=(Company)obj[1];//取出对象分量，并进行强制类型转换
	 * 
	 * @return Object类型的list列表
	 * 
	 */

	public List findBySql(String queryString, Class obj1, String alias1, Class obj2, String alias2, int pageNo,
			int pageSize, String countSql) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize, obj1, alias1, obj2, alias2);

			resultRows = hibernatePage.selectRowsBySql(countSql, pageSize);
			pageCount = hibernatePage.getPageCount();
			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 通过SQL语句查找结果集（该方法返回非vo对象的list）
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * 
	 * @return Object类型的list列表
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List findBySql(String queryString) {
		Session session = getSession();
		try {
			SQLQuery queryObject = session.createSQLQuery(queryString);
			List tempList = queryObject.list();
			return tempList;
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * 通过SQL语句查找结果集，带有分页效果（该方法返回非vo对象的list）
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * @param pageNo,当前页号
	 * @param pageSize,每页记录数
	 * 
	 * @return Object类型的list列表
	 * 
	 */

	public List findBySql(String queryString, int pageNo, int pageSize) {
		try {
			List resultList = hibernatePage.selectSql(queryString, pageNo, pageSize);
			resultRows = hibernatePage.selectRowsBySql(queryString, pageSize);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 
	 * 通过SQL语句查找结果集，带有分页效果（该方法返回非vo对象的list）
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * @param pageNo,当前页号
	 * @param pageSize,每页记录数
	 * @param orderbySql,排序字符串，排序时拼接到queryString 后面，求总页数时不拼接
	 * @return Object类型的list列表
	 * 
	 */

	public List findBySql(String queryString, String orderbySql, int pageNo, int pageSize) {
		try {
			List resultList = hibernatePage.selectSql(queryString + orderbySql, pageNo, pageSize);
			resultRows = hibernatePage.selectRowsBySql(queryString, pageSize);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 
	 * 通过SQL语句查找结果集，带有分页效果（该方法返回非vo对象的list）
	 * 
	 * @param queryString,String类型，SQL查找语句
	 * @param pageNo,当前页号
	 * @param pageSize,每页记录数
	 * @param orderbySql,排序字符串，排序时拼接到queryString 后面，求总页数时不拼接
	 * @return Object类型的list列表
	 * 
	 */

	public List findBySql(String queryString, String orderbySql, int pageNo, int pageSize, List paramList) {
		try {
			List resultList = hibernatePage.selectSql(queryString + orderbySql, pageNo, pageSize, paramList);
			resultRows = hibernatePage.selectRowsBySqlZR(queryString, pageSize, paramList);
			pageCount = hibernatePage.getPageCount();

			return resultList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		}
	}

	/**
	 * 执行不返回结果集的SQL语句，如：增加、修改和删除
	 * 
	 * @param queryString，查询SQL语句
	 * 
	 */
	public int executeBySql(String queryString) {

		int iResult = -1;
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			SQLQuery queryObject = session.createSQLQuery(queryString);
			iResult = queryObject.executeUpdate();
			tx.commit();
			return iResult;
		} catch (RuntimeException re) {
			tx.rollback();
			log.error(re);// 记录错误日志
			return iResult;

		} finally {
			session.close();
		}
	}

	public int executeSqlList(List<String> strList, List<Object> objList) {
		int iResult = -1;
		Session session = getSession();
		SQLQuery queryObject;
		Transaction tx = session.beginTransaction();
		try {
			if (null != strList && strList.size() > 0) {
				for (int i = 0; i < strList.size(); i++) {
					queryObject = session.createSQLQuery(strList.get(i).toString());

					iResult = queryObject.executeUpdate();

				}
			}
			if (null != objList && objList.size() > 0) {
				for (int i = 0; i < objList.size(); i++) {
					session.merge(objList.get(i));
				}

			}
			tx.commit();
			return 0;

		} catch (RuntimeException re) {
			tx.rollback();
			log.error(re);// 记录错误日志
			return 1;

		} finally {
			session.close();
		}
	}

	/**
	 * 通过HQL语句查找结果集
	 * 
	 * @param queryString,String类型，HQL查找语句
	 * 
	 * @return Object类型的list列表
	 * 
	 */

	public List findByHql(String queryString) {
		Session session = getSession();
		try {
			Query queryObject = session.createQuery(queryString);
			List tempList = queryObject.list();
			return tempList;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * 通过主键在数据库表中查找对象
	 * 
	 * @param className,Class类型，对象完整类名
	 * @param id,int类型，对象主键。
	 * 
	 * 例子：news = (MyNews) newsDao.findById(MyNews.class, myId);
	 * 
	 * @return Object类型，对应主键的实体对象
	 * 
	 */

	public Object findById(Class className, java.lang.Integer id) {
		Session session = getSession();
		try {
			Object objectE = session.get(className, id);
			return objectE;
		} catch (RuntimeException re) {
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * 通过主键在数据库表中查找对象（该方法不可用）
	 * 
	 * @param className,String类型，对象完整类名
	 * @param id,int类型，对象主键
	 * 
	 * @return Object类型，对应主键的实体对象
	 * 
	 */
	public Object findById(String className, java.lang.Integer id) {
		Session session = getSession();
		try {
			Object objectE = session.get(className, id);
			return objectE;
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * 修改库中一条记录
	 * 
	 * @param detachedInstance,Object类型，待修改对象的实体
	 * 
	 * @return Object，修改后的对象实体
	 */
	public Object merge(Object detachedInstance) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Object objectE;
		try {
			objectE = session.merge(detachedInstance);
			tx.commit();
			return objectE;
		} catch (RuntimeException re) {
			if (tx != null) {
				tx.rollback();
			}
			re.printStackTrace();
			log.error(re);// 记录错误日志
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 同时保存多个对象
	 * 
	 * @param detachedInstance
	 * @return
	 */
	public int mergeList(List<Object> detachedInstance) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Object objectE;
		try {
			for (int i = 0; i < detachedInstance.size(); i++) {
				objectE = detachedInstance.get(i);
				session.merge(objectE);
			}
			tx.commit();
			return 0;
		} catch (RuntimeException re) {
			if (tx != null) {
				tx.rollback();
			}
			re.printStackTrace();
			log.error(re);// 记录错误日志
			return 1;
		} finally {
			session.close();
		}
	}

	/**
	 * 同时保存多个对象,并执行多条sql语句
	 * 
	 * @param detachedInstance
	 * @return
	 */
	public int mergeList(List<Object> detachedInstance, List<String> sqlList) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Object objectE;
		Query queryObject;
		int iResult = -1;
		try {
			for (int i = 0; i < detachedInstance.size(); i++) {
				objectE = detachedInstance.get(i);
				session.merge(objectE);
			}

			for (int i = 0; i < sqlList.size(); i++) {
				if (!sqlList.get(i).equals("")) {
					queryObject = session.createSQLQuery(sqlList.get(i));
					iResult = queryObject.executeUpdate();
				}
			}

			tx.commit();
			return 0;
		} catch (RuntimeException re) {
			if (tx != null) {
				tx.rollback();
			}
			re.printStackTrace();
			log.error(re);// 记录错误日志
			return 1;
		} finally {
			session.close();
		}
	}

	/**
	 * 同时执行多条sql
	 * 
	 * @param list 多条Sql语句
	 * @return
	 */
	public int executeSqlList(List<String> list) {
		Session session = getSession();
		int iResult = -1;
		Query queryObject; // = session.createQuery(queryString);
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < list.size(); i++) {
				if (!list.get(i).equals("")) {
					queryObject = session.createSQLQuery(list.get(i));
					iResult = queryObject.executeUpdate();
				}

			}
			tx.commit();
			return iResult;
		} catch (HibernateException e) {
			e.printStackTrace();
			return iResult;
		} finally {
			session.close();
		}
	}

	/**
	 * 根据不同的操作类型，同时处理多个对象
	 * 
	 * @param detachedInstance
	 * @return
	 */
	public int operateList(Map<Object, String> instanceOperate) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			for (Map.Entry<Object, String> entry : instanceOperate.entrySet()) {
				if (entry.getValue().equals("merge")) {
					session.merge(entry.getKey());
				} else if (entry.getValue().equals("delete")) {
					session.delete(entry.getKey());
				}
			}
			tx.commit();
			return 0;
		} catch (RuntimeException re) {
			if (tx != null) {
				tx.rollback();
			}
			re.printStackTrace();
			log.error(re);// 记录错误日志
			return 1;
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * <li>功能描述：修改信息。
	 * 
	 * @param instance
	 * @return int
	 */
	public Object save(Object detachedInstance) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Object objectE;
		try {
			objectE = session.save(detachedInstance);
			tx.commit();
			return objectE;
		} catch (RuntimeException re) {
			if (tx != null) {
				tx.rollback();
			}
			re.printStackTrace();
			log.error(re);// 记录错误日志
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 通过HQL删除对象记录（真删）
	 * 
	 * @param persistentInstance,Object类型，待删除的对象记录
	 * 
	 */
	public void delete(Object persistentInstance) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			log.error(re);// 记录错误日志
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * <li>功能描述：修改信息。
	 * 
	 * @param instance
	 * @return int
	 */
	public int update(Object detachedInstance) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Object objectE;
		try {
			session.update(detachedInstance);
			tx.commit();
			return 1;
		} catch (RuntimeException re) {
			if (tx != null) {
				tx.rollback();
			}
			log.error(re);// 记录错误日志
			re.printStackTrace();
			return 2;
		} finally {
			session.close();
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getResultRows() {
		return resultRows;
	}

	public void setResultRows(int resultRows) {
		this.resultRows = resultRows;
	}
}
