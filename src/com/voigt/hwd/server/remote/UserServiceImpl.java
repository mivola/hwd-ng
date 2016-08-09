package com.voigt.hwd.server.remote;

import java.io.Serializable;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.data.SortSpecifier;
import com.voigt.hwd.client.admin.ds.FetchResult;
import com.voigt.hwd.client.admin.ds.criteria.GWTCriterion;
import com.voigt.hwd.client.service.UserService;
import com.voigt.hwd.client.session.LoginStatus;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Role;
import com.voigt.hwd.domain.SimplePojo;
import com.voigt.hwd.domain.User;
import com.voigt.hwd.server.service.IGenericService;
import com.voigt.hwd.server.service.IIdentificationService;
import com.voigt.hwd.server.session.SessionManager;

/**
 * User remote service
 * 
 * @author bruno.marchesson
 * 
 */
public class UserServiceImpl extends AbstractGwtDataSourceRemoteService<User> implements UserService {

	private static final long serialVersionUID = 5921199904102343567L;

	private IIdentificationService identificationService;

	public UserServiceImpl() {
		super();
		identificationService = getContext().getIdentificationService();
	}

	public IIdentificationService getIdentificationService() {
		return identificationService;
	}

	public void setIdentifitcationService(IIdentificationService identificationService) {
		this.identificationService = identificationService;
	}

	@Override
	public FetchResult<User> fetch(GWTCriterion criterion, SortSpecifier[] sortSpecifiers, int startRow, int endRow) {
		FetchResult<User> users = super.fetch(criterion, sortSpecifiers, startRow, endRow);
		for (User user : users.getFetchedList()) {
			Log.debug("user: " + user + "; roles: " + user.getRoles());
		}
		return users;
	}

	/**
	 * Return the user list
	 */
	public List<User> getUserList() {
		List<User> list = identificationService.loadUserList();
		return list;
	}

	@Override
	public LoginStatus loginUser(String username, String password) {

		// TODO: check the DB for the correct password

		LoginStatus loginStatus = SessionManager.getInstance().loginUser(username, password);
		Log.debug("tried to login: " + loginStatus);
		return loginStatus;
	}

	@Override
	public boolean logoffUser(String userName) {
		boolean logoffUser = SessionManager.getInstance().logoffUser(userName);
		Log.debug("tried to logoff: " + logoffUser);
		return logoffUser;
	}

	@Override
	public List<LoginStatus> getSessions() {
		List<LoginStatus> loginStatus = SessionManager.getInstance().getSessions();
		return loginStatus;
	}

	// tmp
	public SimplePojo getSimplePojo() {
		SimplePojo pojo = new SimplePojo();
		pojo.setLastName("!sad");
		pojo.setLastName("last");
		pojo.setId(5465);

		return pojo;
	}

	// tmp
	public Role getSampleRole() {
		Role role = new Role();
		role.setId(545);
		role.setName("rolle");
		return role;
	}

	// tmp
	public MatchDay getSampleMatchDay() {
		MatchDay matchDay = new MatchDay();
		matchDay.setId(5496574);
		matchDay.setFinished(true);
		matchDay.setOLId(9999);
		return matchDay;
	}

	public List<User> getAllEntries() {
		return identificationService.getAll();
	}

	public User getOneEntry(int id) {
		return identificationService.getById(id);
	}

	public boolean saveOneEntry(User user) {
		identificationService.saveOrUpdate(user);
		return true;
	}

	public boolean deleteOneEntry(int id) {
		identificationService.deleteById(id);
		return true;
	}

	@Override
	protected IGenericService<User, Serializable> getService() {
		return identificationService;
	}

}
