package com.voigt.hwd.server.session;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.voigt.hwd.client.session.LoginStatus;

public class SessionManagerTest {

	private static final String VALID_USER = "micha";
	private static final String VALID_PASSWD = "password";

	@Test
	public void testValidLogin() {

		SessionManager sessionManager = SessionManager.getInstance();
		LoginStatus loginStatus = sessionManager.loginUser(VALID_USER, VALID_PASSWD);
		boolean loginUser = loginStatus.isLoggedIn();
		assertTrue(loginUser);

		boolean checkUserLoginStatus = sessionManager.checkUserLoginStatus(VALID_USER, loginStatus.getSessonId());
		assertTrue(checkUserLoginStatus);

	}

	@Test
	public void testInValidLogin() {

		SessionManager sessionManager = SessionManager.getInstance();
		LoginStatus loginStatus = sessionManager.loginUser(VALID_USER, VALID_PASSWD);
		boolean loginUser = loginStatus.isLoggedIn();
		assertTrue(loginUser);

		boolean checkUserLoginStatus = sessionManager.checkUserLoginStatus("bla2", loginStatus.getSessonId());
		assertTrue(!checkUserLoginStatus);

	}

	@Test
	public void testInvalidSessionId() {

		SessionManager sessionManager = SessionManager.getInstance();
		boolean loginUser = sessionManager.loginUser(VALID_USER, VALID_PASSWD).isLoggedIn();
		assertTrue(loginUser);

		boolean checkUserLoginStatus = sessionManager.checkUserLoginStatus(VALID_USER, "dsjkflsdkjf");
		assertTrue(!checkUserLoginStatus);

	}

	@Test
	public void testLogoff() {

		SessionManager sessionManager = SessionManager.getInstance();
		LoginStatus loginStatus = sessionManager.loginUser(VALID_USER, VALID_PASSWD);
		boolean loginUser = loginStatus.isLoggedIn();
		assertTrue(loginUser);

		boolean logoffUser = sessionManager.logoffUser(VALID_USER);
		assertTrue(logoffUser);

		boolean checkUserLoginStatus = sessionManager.checkUserLoginStatus(VALID_USER, loginStatus.getSessonId());
		assertTrue(!checkUserLoginStatus);

	}

}
