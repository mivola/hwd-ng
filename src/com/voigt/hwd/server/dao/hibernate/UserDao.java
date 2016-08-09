/**
 * 
 */
package com.voigt.hwd.server.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.voigt.hwd.domain.User;
import com.voigt.hwd.server.dao.IUserDao;

/**
 * DAO for user beans.
 * 
 * @author bruno.marchesson
 * 
 */
public class UserDao extends GenericDaoHibernateImpl<User, Serializable> implements IUserDao {

	public UserDao() {
		super(User.class);
	}

	/**
	 * Load the user with the argument login
	 */
	public User searchUserByLogin(String login) {

		Criteria criteria = getSession().createCriteria(User.class);
		// TODO: do the real search!
		criteria.add(Restrictions.eq("nickName", login));
		// criteria.add(Restrictions.lt("id", 3));

		return (User) criteria.uniqueResult();

	}

	/**
	 * Count all the users
	 */
	public int countAll() {
		// Create query
		//
		// Query query = _entityManager.createQuery("select count(*) from User
		// user");

		return this.getAll().size();

		// Execute query
		//
		// return ((Long) query.getSingleResult()).intValue();
	}

}