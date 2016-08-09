package com.voigt.hwd.comm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.voigt.hwd.domain.User;
import com.voigt.hwd.server.GwtApplicationContext;
import com.voigt.hwd.server.service.IStartupService;
import com.voigt.hwd.server.service.impl.IdentificationService;

public class ServiceTest {

	@Test
	public void testServiceInvocation() {

		GwtApplicationContext applicationContext = GwtApplicationContext.getInstance();

		IStartupService startupService = (IStartupService) applicationContext.getBean(IStartupService.NAME);
		assertNotNull(startupService);

		startupService.initialize();

		boolean initialized = startupService.isInitialized();
		assertTrue(initialized);

		IdentificationService identificationService = (IdentificationService) applicationContext
				.getBean("identificationService");
		List<User> allUsers = identificationService.getAll();
		assertNotNull(allUsers);
		assertNotNull(allUsers.get(0));
		List<User> allUsers2 = identificationService.loadUserList();
		assertEquals(allUsers.size(), allUsers2.size());

	}

}
