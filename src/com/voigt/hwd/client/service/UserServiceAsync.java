/**
 * 
 */
package com.voigt.hwd.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voigt.hwd.client.session.LoginStatus;
import com.voigt.hwd.domain.User;

/**
 * User update
 * 
 * @author bruno.marchesson
 * 
 */
public interface UserServiceAsync extends IDataSourceServiceAsync<User> {
	/**
	 * @return the user list
	 */
	public void getUserList(AsyncCallback callback);

	/**
	 * try to login a user
	 * 
	 * @param username
	 * @param password
	 * @param callback
	 */
	public void loginUser(String username, String password, AsyncCallback<LoginStatus> callback);

	/**
	 * try to logoff the given user
	 * 
	 * @param username
	 * @param callback
	 */
	public void logoffUser(String username, AsyncCallback callback);

	/**
	 * @return a list of sessions that represent the users currently online
	 */
	public void getSessions(AsyncCallback<List<LoginStatus>> callback);

	/**
	 * test method
	 * 
	 * @return
	 */
	public void getSimplePojo(AsyncCallback callback);

	/**
	 * test method2
	 * 
	 * @return
	 */
	public void getSampleRole(AsyncCallback callback);

	/**
	 * test method3
	 * 
	 * @return
	 */
	public void getSampleMatchDay(AsyncCallback callback);

}
