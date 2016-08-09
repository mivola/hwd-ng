package com.voigt.hwd.server.service;

import java.io.Serializable;
import java.util.List;

import com.voigt.hwd.domain.User;
import com.voigt.hwd.domain.exception.IdentificationException;

/**
 * Interface of the authentication service
 * 
 * @author bruno.marchesson
 * 
 */
public interface IIdentificationService extends IGenericService<User, Serializable> {

	public static final String NAME = "identificationService";

	/**
	 * Authenticates the user from its login and password, not the hash!
	 */
	public User authenticate(String login, String password) throws IdentificationException;

	/**
	 * @return the list of all users
	 */
	public List<User> loadUserList();

}