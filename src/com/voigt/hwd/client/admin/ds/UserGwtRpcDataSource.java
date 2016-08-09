package com.voigt.hwd.client.admin.ds;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.voigt.hwd.client.service.IDataSourceServiceAsync;
import com.voigt.hwd.client.service.UserService;
import com.voigt.hwd.client.service.UserServiceAsync;
import com.voigt.hwd.domain.Role;
import com.voigt.hwd.domain.User;

public class UserGwtRpcDataSource extends AbstractGenericGwtRpcDataSource<User> {
	protected static final String LAST_NAME = "lastName";
	protected static final String FIRST_NAME = "firstName";
	protected static final String NICK_NAME = "nickName";
	protected static final String ROLES = "roles";

	private final UserServiceAsync service;

	public UserGwtRpcDataSource() {
		super();

		service = UserService.Util.getInstance();

		DataSourceTextField firstName = new DataSourceTextField(FIRST_NAME, "Vorname");
		DataSourceTextField lastName = new DataSourceTextField(LAST_NAME, "Nachname");
		DataSourceTextField nickName = new DataSourceTextField(NICK_NAME, "Nachname");
		// DataSourceField roles= new DataSourceField(ROLES, "roles");
		// see GridCustomEditorsSample
		// http://www.smartclient.com/smartgwt/showcase/#grid_editing_custom_editors

		DataSourceTextField roleIdField = new DataSourceTextField("roleId", "RoleID");
		roleIdField.setRequired(true);

		DataSourceTextField roleNameField = new DataSourceTextField("roleName", "Rollen");
		roleNameField.setRequired(true);

		setFields(idField, firstName, lastName, nickName, roleIdField, roleNameField);
		setClientOnly(true);

	}

	@Override
	protected void copyValues(ListGridRecord from, User to) {
		to.setId(from.getAttributeAsInt(idField.getName()));
		to.setFirstName(from.getAttributeAsString(FIRST_NAME));
		to.setLastName(from.getAttributeAsString(LAST_NAME));
		to.setNickName(from.getAttributeAsString(NICK_NAME));
		to.setRoles((List<Role>) from.getAttributeAsObject(ROLES));

		Log.debug("to: " + to.getRoles());
		// // TODO: at the moment getAttributeAsDate is not working here,
		// // we simply get it as an object and compare its runtime class
		// against java.util.Date (thanks Alius!)
		// Object data=from.getAttributeAsObject(dateField.getName());
		// if(data instanceof Date)
		// to.setDate((Date) data);

	}

	@Override
	protected Object getValueOf(User from, String fieldName) {
		Log.debug("from: " + from.getRoles());
		if (idField.getName().equals(fieldName)) {
			return from.getId();
		} else if (LAST_NAME.equals(fieldName)) {
			return from.getLastName();
		} else if (FIRST_NAME.equals(fieldName)) {
			return from.getFirstName();
		} else if (NICK_NAME.equals(fieldName)) {
			return from.getNickName();
		} else if (ROLES.equals(fieldName)) {
			return from.getRoles();
		}
		return null;
	}

	@Override
	protected IDataSourceServiceAsync<User> getDataSourceServiceAsync() {
		return service;
	}

	@Override
	protected User getEmptyRecord() {
		return new User();
	}

}
