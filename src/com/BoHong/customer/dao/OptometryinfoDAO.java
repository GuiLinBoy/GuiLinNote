package com.BoHong.customer.dao;

import com.BoHong.customer.vo.Optometryinfo;
import com.BoHong.util.BaseHibernateDAO;
import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Optometryinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.BoHong.customer.dao.Optometryinfo
 * @author MyEclipse Persistence Tools
 */
public class OptometryinfoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(OptometryinfoDAO.class);
	// property constants
	public static final String UID = "uid";
	public static final String ITEM_NAME = "itemName";
	public static final String RQIUJING = "rqiujing";
	public static final String RZHUJING = "rzhujing";
	public static final String RZHOUWEI = "rzhouwei";
	public static final String RJIAOZHENG = "rjiaozheng";
	public static final String LQIUJING = "lqiujing";
	public static final String LZHUJING = "lzhujing";
	public static final String LZHOUWEI = "lzhouwei";
	public static final String LJIAOZHENG = "ljiaozheng";
	public static final String TONGJU = "tongju";
	public static final String COST = "cost";
	public static final String CREATOR = "creator";
	public static final String MODIFIER = "modifier";
	public static final String DELETOR = "deletor";
	public static final String DEL_STATE = "delState";

	public void save(Optometryinfo transientInstance) {
		log.debug("saving Optometryinfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Optometryinfo persistentInstance) {
		log.debug("deleting Optometryinfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Optometryinfo findById(java.lang.Integer id) {
		log.debug("getting Optometryinfo instance with id: " + id);
		try {
			Optometryinfo instance = (Optometryinfo) getSession().get(
					"com.BoHong.customer.vo.Optometryinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Optometryinfo instance) {
		log.debug("finding Optometryinfo instance by example");
		try {
			List results = getSession()
					.createCriteria("com.BoHong.customer.dao.Optometryinfo")
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
		log.debug("finding Optometryinfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Optometryinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	public List findByItemName(Object itemName) {
		return findByProperty(ITEM_NAME, itemName);
	}

	public List findByRqiujing(Object rqiujing) {
		return findByProperty(RQIUJING, rqiujing);
	}

	public List findByRzhujing(Object rzhujing) {
		return findByProperty(RZHUJING, rzhujing);
	}

	public List findByRzhouwei(Object rzhouwei) {
		return findByProperty(RZHOUWEI, rzhouwei);
	}

	public List findByRjiaozheng(Object rjiaozheng) {
		return findByProperty(RJIAOZHENG, rjiaozheng);
	}

	public List findByLqiujing(Object lqiujing) {
		return findByProperty(LQIUJING, lqiujing);
	}

	public List findByLzhujing(Object lzhujing) {
		return findByProperty(LZHUJING, lzhujing);
	}

	public List findByLzhouwei(Object lzhouwei) {
		return findByProperty(LZHOUWEI, lzhouwei);
	}

	public List findByLjiaozheng(Object ljiaozheng) {
		return findByProperty(LJIAOZHENG, ljiaozheng);
	}

	public List findByTongju(Object tongju) {
		return findByProperty(TONGJU, tongju);
	}

	public List findByCost(Object cost) {
		return findByProperty(COST, cost);
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
		log.debug("finding all Optometryinfo instances");
		try {
			String queryString = "from Optometryinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Optometryinfo merge(Optometryinfo detachedInstance) {
		log.debug("merging Optometryinfo instance");
		try {
			Optometryinfo result = (Optometryinfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Optometryinfo instance) {
		log.debug("attaching dirty Optometryinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Optometryinfo instance) {
		log.debug("attaching clean Optometryinfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}