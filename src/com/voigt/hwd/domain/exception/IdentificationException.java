package com.voigt.hwd.domain.exception;

/**
 * Identification exception
 * 
 * @author bruno.marchesson
 * 
 */
public class IdentificationException extends BaseException {

	private static final long serialVersionUID = -3974804157089131282L;

	private String userName;

	public IdentificationException() {
		// needed by gilead
		super();
	}

	public IdentificationException(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getMessage() {
		return "Invalid user or password for " + userName;
	}
}
