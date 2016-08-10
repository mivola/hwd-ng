package com.voigt.hwd.server.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.allen_sauer.gwt.log.client.Log;
import com.voigt.hwd.client.session.LoginStatus;
import com.voigt.hwd.domain.exception.IdentificationException;
import com.voigt.hwd.server.GwtApplicationContext;

public class SessionManager {

	private static final int SESSION_TIMEOUT = 60 * 15 * 1000;// 60sec*15min

	private static Map<String, LoginStatus> LOGINS = new HashMap<String, LoginStatus>();

	private static SessionManager instance;

	private SessionManager() {
		// singleton
	}

	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}

	public LoginStatus loginUser(String userName, String password) {

		LoginStatus loginStatus = new LoginStatus(userName);

		boolean passwordCorrect = false;
		try {
			GwtApplicationContext.getInstance().getIdentificationService().authenticate(userName, password);
			passwordCorrect = true;
		} catch (IdentificationException e) {
			Log.warn("wrong credentials for user: " + userName);
			passwordCorrect = false;
		}

		if (passwordCorrect) {
			UUID sessionId = UUID.randomUUID();
			loginStatus.setSessonId(sessionId.toString());
			LOGINS.put(userName, loginStatus);
		} else {
			loginStatus.setLoggedIn(false);
			LOGINS.remove(userName);
		}
		return loginStatus;

	}

	public boolean logoffUser(String userName) {

		LOGINS.remove(userName);
		return true;

	}

	public boolean checkUserLoginStatus(String userName, String sessionId) {

		LoginStatus loginStatus = LOGINS.get(userName);

		if (loginStatus == null) {
			// user not logged in
			return false;
		} else if (!loginStatus.getSessonId().equals(sessionId)) {
			// wrong sessionId
			LOGINS.remove(userName);
			return false;
		} else if ((new Date().getTime() - loginStatus.getLastAction().getTime()) > SESSION_TIMEOUT) {
			// session outdated
			LOGINS.remove(userName);
			return false;
		} else {
			// login seems ok => update last action timestamp
			loginStatus.setLastAction(new Date());
		}

		// session is valid
		return true;

	}

	public List<LoginStatus> getSessions() {

		List<LoginStatus> list = new ArrayList<LoginStatus>();

		Iterator<String> iterator = LOGINS.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			LoginStatus loginStatus = LOGINS.get(key);
			list.add(loginStatus);
		}

		return list;
	}

}
