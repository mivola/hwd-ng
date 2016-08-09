/**
 * 
 */
package com.voigt.hwd.server.service.impl;

import java.io.Serializable;
import java.util.List;

import com.voigt.hwd.domain.User;
import com.voigt.hwd.domain.exception.IdentificationException;
import com.voigt.hwd.server.dao.IUserDao;
import com.voigt.hwd.server.service.IIdentificationService;
import com.voigt.hwd.server.session.BCrypt;

/**
 * Implementation of the identification service
 * 
 * @author bruno.marchesson
 * 
 */
public class IdentificationService extends GenericServiceImpl<User, Serializable> implements IIdentificationService {

	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
		this.dao = userDao;
	}

	/**
	 * Authenticates the user from its login and password
	 */
	public User authenticate(String login, String password) throws IdentificationException {

		// Search the user
		User user = userDao.searchUserByLogin(login);
		if (user == null) {
			throw new IdentificationException(login);
		}

		String userPwdHash = user.getPasswordHash();

		if (!BCrypt.checkpw(password, userPwdHash)) {
			// password is not correct
			throw new IdentificationException(login);
		}

		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.gilead.sample.server.service.IIdentificationService#loadUserList()
	 */
	public List<User> loadUserList() {
		List<User> allUsers = userDao.getAll();
		return allUsers;
	}

}
