/**
 * 
 */
package com.voigt.hwd.server.dao.hibernate;

import java.io.Serializable;

import com.voigt.hwd.domain.Role;
import com.voigt.hwd.server.dao.IRoleDao;

/**
 * DAO for role beans.
 * 
 * @author bruno.marchesson
 * 
 */
public class RoleDao extends GenericDaoHibernateImpl<Role, Serializable> implements IRoleDao {

	public RoleDao() {
		super(Role.class);
	}

}