package com.BoHong.customer.dao;

import com.BoHong.customer.vo.Users;
import com.BoHong.util.BaseHibernateDAO;
import com.BoHong.util.HibernateDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.BoHong.customer.dao.Users
 * @author MyEclipse Persistence Tools
 */
public class UsersDAO extends BaseHibernateDAO {
	HibernateDAO hibernateDAO = new HibernateDAO();
	private static final Log log = LogFactory.getLog(UsersDAO.class);
	// property constants
	public static final String OPEN_ID = "openId";
	public static final String UNION_ID = "unionId";
	public static final String USER_NAME = "userName";
	public static final String USER_SEX = "userSex";
	public static final String USER_NUMBER = "userNumber";
	public static final String HEAD_PHOTO = "headPhoto";
	public static final String CREATOR = "creator";
	public static final String MODIFIER = "modifier";
	public static final String DELETOR = "deletor";
	public static final String DEL_STATE = "delState";

	public void save(Users transientInstance) {
		log.debug("saving Users instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Users findById(java.lang.Integer id) {
		log.debug("getting Users instance with id: " + id);
		try {
			Users instance = (Users) getSession().get(
					"com.BoHong.customer.vo.Users", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			List results = getSession()
					.createCriteria("com.BoHong.customer.dao.Users")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Users instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Users as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOpenId(Object openId) {
		return findByProperty(OPEN_ID, openId);
	}

	public List findByUnionId(Object unionId) {
		return findByProperty(UNION_ID, unionId);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByUserSex(Object userSex) {
		return findByProperty(USER_SEX, userSex);
	}

	public List findByUserNumber(Object userNumber) {
		return findByProperty(USER_NUMBER, userNumber);
	}

	public List findByHeadPhoto(Object headPhoto) {
		return findByProperty(HEAD_PHOTO, headPhoto);
	}

	public List findByCreator(Object creator) {
		return findByProperty(CREATOR, creator);
	}

	public List findByModifier(Object modifier) {
		return findByProperty(MODIFIER, modifier);
	}

	public List findByDeletor(Object deletor) {
		return findByProperty(DELETOR, deletor);
	}

	public List findByDelState(Object delState) {
		return findByProperty(DEL_STATE, delState);
	}

	public List findAll() {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<Users> findUsers(String startTime, String endTime,String name) {
		String timeString = "";
		if ((null == startTime || startTime.equals(""))
				&& (null == endTime || endTime.equals(""))) {
			timeString = "";
		} else if ((null != startTime || !startTime.equals(""))
				&& ((null == endTime || endTime.equals("")))) {
			timeString = " and createTime >='" + startTime + " 00:00:00'";
		} else if ((null == startTime || startTime.equals(""))
				&& (null != endTime || !endTime.equals(""))) {
			timeString = " and createTime <= '" + endTime + " 23:59:59'";
		} else {
			timeString = " and(createTime between '" + startTime
					+ " 00:00:00 ' and '" + endTime + " 23:59:59')";

		}

		String username = "";
		if (null != name && !name.trim().equals("")) {
			username = " and userName like '%" + name + "%'";
		}
		String queryString = "select * from users where delState=0 " + timeString + username + "order by id desc";
		List< Users> uList=new ArrayList<Users>();
		try {
			uList=hibernateDAO.findBySqlStart(queryString, Users.class);
			return uList;
		} catch (RuntimeException re) {
			// TODO: handle exception
			throw re;
		}
		

	}
}