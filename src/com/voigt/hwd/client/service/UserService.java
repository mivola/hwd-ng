/**
 * 
 */
package com.voigt.hwd.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.voigt.hwd.client.session.LoginStatus;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Role;
import com.voigt.hwd.domain.SimplePojo;
import com.voigt.hwd.domain.User;

/**
 * User update
 * 
 * @author bruno.marchesson
 * 
 */
public interface UserService extends IDataSourceService<User> {
	/**
	 * Utility class for simplifing access to the instance of async service.
	 */
	public static class Util {
		private static UserServiceAsync instance;

		public static UserServiceAsync getInstance() {
			if (instance == null) {
				instance = (UserServiceAsync) GWT.create(UserService.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "/UserService");
			}
			return instance;
		}
	}

	/**
	 * @return the user list
	 */
	public List<User> getUserList();

	/**
	 * try to login a user
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public LoginStatus loginUser(String username, String password);

	/**
	 * try to logoff the given user
	 * 
	 * @param username
	 * @return
	 */
	public boolean logoffUser(String username);

	/**
	 * @return a list of sessions that represent the users currently online
	 */
	public List<LoginStatus> getSessions();

	/**
	 * test method
	 * 
	 * @return
	 */
	public SimplePojo getSimplePojo();

	/**
	 * test method2
	 * 
	 * @return
	 */
	public Role getSampleRole();

	/**
	 * test method3
	 * 
	 * @return
	 */
	public MatchDay getSampleMatchDay();

}
