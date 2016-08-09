/**
 * 
 */
package com.voigt.hwd.server.service.impl;

import com.voigt.hwd.domain.User;
import com.voigt.hwd.server.dao.IUserDao;
import com.voigt.hwd.server.service.IStartupService;

/**
 * Service used to initialize the underlying data for demo application
 * 
 * @author bruno.marchesson
 * 
 */
public class StartupService implements IStartupService {

	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.gilead.sample.server.service.implementation.IStartupService#isInitialized()
	 */
	public boolean isInitialized() {
		return (userDao.countAll() > 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.gilead.sample.server.service.implementation.IStartupService#initialize()
	 */
	public void initialize() {
		// Create guest user (no password)
		//
		User guestUser = new User();
		// guestUser.setLogin("guest");
		guestUser.setFirstName("No");
		guestUser.setLastName("name");

		// create welcome message
		// Message guestMessage = new Message();
		// guestMessage.setMessage("Welcome in Gilead sample application");
		// guestMessage.setDate(new Date());
		// guestUser.addMessage(guestMessage);

		// save user (message is cascaded)
		userDao.save(guestUser);

		// Create JUnit user
		//
		User junitUser = new User();
		// junitUser.setLogin("junit");
		// junitUser.setPassword("junit");
		junitUser.setFirstName("Unit");
		junitUser.setLastName("Test");

		// create message
		// Message junitMessage = new Message();
		// junitMessage.setMessage("JUnit first message");
		// junitMessage.setDate(new Date());
		// junitUser.addMessage(junitMessage);

		// save user (message is cascaded)
		userDao.save(junitUser);
	}
}
