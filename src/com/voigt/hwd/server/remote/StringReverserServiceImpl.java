package com.voigt.hwd.server.remote;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.voigt.hwd.client.service.StringReverserService;

/**
 * StringReverserServiceImpl
 * 
 * Server side implementation of the String Reverser Service
 * 
 * @author Masud Idris
 * @version 1.0
 * @since Dec 6, 2007, 11:35:32 AM
 * 
 */
public class StringReverserServiceImpl extends RemoteServiceServlet implements StringReverserService {

	private static final long serialVersionUID = 1656825488007884532L;

	/**
	 * This method is used to reverse a string
	 * 
	 * @param stringToReverse
	 *            The string that you want reversed
	 * @return The reversed string
	 */
	public String reverseString(String stringToReverse) {
		StringBuffer reverse = new StringBuffer(stringToReverse.length());

		for (int i = (stringToReverse.length() - 1); i >= 0; i--)
			reverse.append(stringToReverse.charAt(i));
		return reverse.toString();
	}
}
