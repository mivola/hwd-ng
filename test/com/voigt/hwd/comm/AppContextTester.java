package com.voigt.hwd.comm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.voigt.hwd.domain.Role;
import com.voigt.hwd.domain.User;
import com.voigt.hwd.server.GwtApplicationContext;
import com.voigt.hwd.server.dao.IRoleDao;
import com.voigt.hwd.server.dao.IUserDao;

public class AppContextTester {

	@Test
	public void testAppContextLoading() {
		GwtApplicationContext applicationContext = GwtApplicationContext.getInstance();
		assertNotNull(applicationContext);

		IUserDao userDao = (IUserDao) applicationContext.getBean("userDao");
		assertNotNull(userDao);

		// User user = new User();
		// user.setLastName("v");
		// user.setFirstName("michael");
		// user.setNickName("micha");
		// userDao.save(user);

		List<User> users = userDao.getAll();
		assertNotNull(users);
		System.out.println(users);

		User user = userDao.searchUserByLogin("micha");
		assertNotNull(user);
		assertEquals("michael11", user.getFirstName());

		IRoleDao roleDao = (IRoleDao) applicationContext.getBean("roleDao");
		assertNotNull(roleDao);

		Role role = new Role();
		role.setName("blaha");
		roleDao.save(role);

		List<Role> roles = roleDao.getAll();
		assertNotNull(roles);
		System.out.println(roles);

		// Object bean = applicationContext.getBean("userServiceSpring");
		// assertNotNull(bean);

	}
}
