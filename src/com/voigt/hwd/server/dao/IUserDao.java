package com.voigt.hwd.server.dao;

import java.io.Serializable;

import com.voigt.hwd.domain.User;

public interface IUserDao extends IGenericDao<User, Serializable> {

	/**
	 * Load the user with the argument login
	 */
	public User searchUserByLogin(String login);

	/**
	 * Count all the users
	 */
	public int countAll();

}