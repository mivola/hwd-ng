package com.voigt.hwd.server.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.voigt.hwd.domain.IDomainObject;
import com.voigt.hwd.domain.exception.BaseException;
import com.voigt.hwd.server.dao.IGenericDao;
import com.voigt.hwd.server.util.ComparatorUtils;
import com.voigt.hwd.server.util.Utils;

/**
 * Hibernate specific implementation of an generic DAO. Offers type safety and
 * the basic CRUD operations
 * 
 * @author mvoigt
 * 
 * @param <T>
 *                Type/Class
 * @param <PK>
 *                Primary Key
 */
public class GenericDaoHibernateImpl<T, PK extends Serializable> extends HibernateDaoSupport implements
	IGenericDao<T, PK> {

    final Log log = LogFactory.getLog(this.getClass());

    private final Class<T> type;

    public GenericDaoHibernateImpl(Class<T> type) {
	this.type = type;
    }

    public void flushSession() {
	this.getSession().flush();
    }

    public void delete(Object object) {
	this.getHibernateTemplate().delete(object);
    }

    public void deleteById(PK id) {
	this.getHibernateTemplate().delete(getById(id));
    }

    @SuppressWarnings("unchecked")
    public T getById(PK id) {
	T t = null;
	if (id instanceof String) {
	    final String idString = (String) id;
	    final int idInt = Integer.valueOf(idString);
	    t = (T) this.getHibernateTemplate().load(type, idInt);
	} else {
	    t = (T) this.getHibernateTemplate().get(type, id);
	}
	return t;
    }

    @SuppressWarnings("unchecked")
    public PK save(Object newObject) {
	return (PK) this.getHibernateTemplate().save(newObject);
    }

    public void saveOrUpdate(Object object) {
	this.getHibernateTemplate().saveOrUpdate(object);
    }

    public void update(Object object) {
	this.getHibernateTemplate().update(object);
    }

    public List<T> getAll() {
	Criteria crit = getDefaultCriteria();
	List<T> list = Utils.cast(crit.list());
	return list;
    }

    public List<T> getAllDistinct() {
	Criteria crit = getDefaultCriteria();
	crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	List<T> list = Utils.cast(crit.list());
	return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.dao.IGenericDao#getAll(java.util.Comparator)
     */
    public List<T> getAll(Comparator<T> comparator) {

	List<T> list = this.getAll();

	// try to sort the list by their name
	list = ComparatorUtils.sortList(list, comparator);
	return list;
    }

    public List<T> getAll(Order... order) {
	Criteria crit = getDefaultCriteria();

	for (Order o : order) {
	    crit.addOrder(o);
	}

	return Utils.cast(crit.list());
    }

    public <E extends T> List<E> getAllByConcreteClass(E clazz, String orderProperty) {
	Criteria crit = this.getSession().createCriteria(clazz.getClass());
	crit = addDefaultCriteria(crit);
	// add sort order if defined
	if ((orderProperty != null) && (!("").equalsIgnoreCase(orderProperty.trim()))) {
	    crit.addOrder(Order.asc(orderProperty));
	}
	return Utils.cast(crit.list());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.itcampus.rpl.key.model.dao.IGenericDao#getAllByConcreteClass(java.lang.Object,
     *      java.util.Comparator)
     */
    public <E extends T> List<E> getAllByConcreteClass(E clazz, Comparator<E> comparator) {
	Criteria crit = this.getSession().createCriteria(clazz.getClass());
	crit = addDefaultCriteria(crit);
	// add sort order if defined
	List<E> list = Utils.cast(crit.list());

	// sort the list of elements
	list = ComparatorUtils.sortList(list, comparator);

	return list;
    }

    public List<T> findByExample(T exampleInstance) {
	return findByCriteria(Example.create(exampleInstance));
    }

    public List<T> findByCriteria(Criterion... criterion) {
	Criteria crit = getDefaultCriteria();

	for (Criterion c : criterion) {
	    crit.add(c);
	}

	return Utils.cast(crit.list());
    }

    public List<T> findByCriteria(Criteria criteria) {
	Criteria crit = addDefaultCriteria(criteria);
	return Utils.cast(crit.list());
    }

    public void markAsDeleted(Object object) throws BaseException {

	try {
	    Class<?> clazz = Class.forName(type.getName());

	    // get all methods
	    Method[] methods = clazz.getMethods();

	    for (Method method : methods) {
		if (method.getName().equals("setDeleted")) {
		    // we assume T has a boolean property with the name
		    // "deleted"
		    Object arglist[] = new Object[1];
		    arglist[0] = new Boolean(true);
		    // call the method
		    method.invoke(object, arglist);

		    // update the altered object
		    this.update(object);
		    log.debug("property 'deleted' set to true: " + object);
		    break;
		}
	    }
	} catch (ClassNotFoundException cnfe) {
	    String msg = "coudnt find class with name: " + type.getName();
	    log.warn(msg, cnfe);
	    throw new BaseException(msg, cnfe);
	} catch (IllegalArgumentException e) {
	    String msg = "wrong arguments while setting deleted to true!";
	    log.warn(msg, e);
	    throw new BaseException(msg, e);
	} catch (IllegalAccessException e) {
	    String msg = "illegal access while setting deleted to true!";
	    log.warn(msg, e);
	    throw new BaseException(msg, e);
	} catch (InvocationTargetException e) {
	    String msg = "InvocationTargetException while setting deleted to true!";
	    log.warn(msg, e);
	    throw new BaseException(msg, e);
	}

    }

    /**
     * see addDefaultCriteria(Criteria criteria)
     * 
     * @return
     */
    private Criteria getDefaultCriteria() {
	return addDefaultCriteria(null);
    }

    /**
     * adds default criterias - entries that are marked as deleted (with the
     * boolean property "deleted") should not be included; uses reflections to
     * determine if the property deleted exists
     * 
     * @param criteria
     *                the base on which the return criteria should be build upon
     * @return the given criteria + some default criterias
     */
    private Criteria addDefaultCriteria(Criteria criteria) {

	Criteria crit = this.getSession().createCriteria(type);
	if (criteria != null) {
	    crit = criteria;
	}

	try {
	    Class<?> clazz = Class.forName(type.getName());

	    // get all methods
	    Method[] methods = clazz.getMethods();

	    for (Method method : methods) {
		if (method.getName().equals("isDeleted")) {
		    // we assume T has a boolean property with the name
		    // "deleted"
		    crit.add(Restrictions.eq("deleted", false));
		    // log.debug("adding criteria to exclude entries marked as
		    // deleted for class: "
		    // + type.getName());
		}
	    }
	} catch (ClassNotFoundException cnfe) {
	    log.warn("coudnt find class with name: " + type.getName(), cnfe);
	} catch (Exception e) {
	    log.error("exception while trying to add the deleted restriction: ", e);
	}

	return crit;

    }

    /**
     * gibt die Liste der Objekte zurück die den Suchparametern entsprechen
     * 
     * @param p_mSortColumns
     *                Spalten und Richtung nach denen sortiert werden soll
     * @param p_mSeachColumns
     *                Spalten und Suchtext nach dem gesucht werden soll
     * @return Liste der Objekte
     */
    public List<T> getList(Map<String, Boolean> p_mSortColumns, Map<String, String> p_mSeachColumns,
	    Map<String, ? extends IDomainObject> p_mSeachObjectColumns) {
	Criteria criteria = getSession().createCriteria(type);

	if (p_mSortColumns != null) {
	    Set<String> keys = p_mSortColumns.keySet();
	    Iterator<String> it = keys.iterator();
	    while (it.hasNext()) {
		String sColumn = it.next();
		boolean bAscending = p_mSortColumns.get(sColumn).booleanValue();
		Order order;
		if (bAscending) {
		    order = Order.asc(sColumn);
		} else {
		    order = Order.desc(sColumn);
		}
		criteria.addOrder(order);
	    }
	}

	if (p_mSeachColumns != null) {
	    Set<String> keys = p_mSeachColumns.keySet();
	    Iterator<String> it = keys.iterator();
	    while (it.hasNext()) {
		String sColumn = it.next();
		String sSearch = p_mSeachColumns.get(sColumn);
		if (sSearch != null && sSearch.length() > 0) {
		    sSearch = sSearch.replace("*", "%");
		    criteria.add(Restrictions.like(sColumn, sSearch).ignoreCase());
		}
	    }
	}

	if (p_mSeachObjectColumns != null) {
	    Set<String> keys = p_mSeachObjectColumns.keySet();
	    Iterator<String> it = keys.iterator();
	    while (it.hasNext()) {
		String sColumn = it.next();
		IDomainObject patternObject = p_mSeachObjectColumns.get(sColumn);
		if (patternObject != null) {
		    criteria.add(Restrictions.like(sColumn, patternObject));
		}
	    }
	}

	criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	// get all
	return findByCriteria(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.voigt.hwd.server.dao.IGenericDao#truncate()
     */
    public void truncate(String tableName) throws BaseException {
	SQLQuery truncateSQLQuery = getSession().createSQLQuery("truncate table " + tableName);

	truncateSQLQuery.executeUpdate();
    }

}
