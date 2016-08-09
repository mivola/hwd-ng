package com.voigt.hwd.client.session;

import java.io.Serializable;
import java.util.Date;

public class LoginStatus implements Serializable {

	private static final long serialVersionUID = 8514673672269443883L;

	private String userName;
	private boolean loggedIn;
	private Date lastAction;
	private String sessonId;

	public LoginStatus() {
		this.loggedIn = true;
		this.lastAction = new Date();
	}

	public LoginStatus(String userName) {
		this();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Date getLastAction() {
		return lastAction;
	}

	public void setLastAction(Date lastAction) {
		this.lastAction = lastAction;
	}

	public String getSessonId() {
		return sessonId;
	}

	public void setSessonId(String sessonId) {
		this.sessonId = sessonId;
	}

	@Override
	public String toString() {
		return "userName: " + userName + "; loggedIn: " + loggedIn + "; lastAction: " + lastAction + "; sessonId: "
				+ sessonId;
	}

}
