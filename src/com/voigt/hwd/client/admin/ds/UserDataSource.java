package com.voigt.hwd.client.admin.ds;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.voigt.hwd.client.service.UserService;
import com.voigt.hwd.client.service.UserServiceAsync;
import com.voigt.hwd.domain.User;

public class UserDataSource extends DataSource {
    protected static final String LAST_NAME = "lastName";
    protected static final String FIRST_NAME = "firstName";
    protected static final String NICK_NAME = "nickName";

    public UserDataSource() {
	// setID("userDataSource");

	DataSourceTextField id = new DataSourceTextField(IDataSourceManager.ID_FIELD, "ID");
	DataSourceTextField firstName = new DataSourceTextField(FIRST_NAME, "Vorname");
	DataSourceTextField lastName = new DataSourceTextField(LAST_NAME, "Nachname");
	DataSourceTextField nickName = new DataSourceTextField(NICK_NAME, "Nachname");
	id.setHidden(true);
	id.setPrimaryKey(true);

	setFields(id, firstName, lastName, nickName);
	setClientOnly(true);

	setRecords();

    }

    @Override
    public void fetchData() {
	// TODO Auto-generated method stub
	// super.fetchData();
	Log.debug("1");
	removeRecords();
	Log.debug("2");

	setRecords();
	Log.debug("3");
    }

    private void removeRecords() {

    }

    private void setRecords() {
	Log.debug("4");
	UserServiceAsync service = UserService.Util.getInstance();
	Log.debug("5");
	service.getAllEntries(new AsyncCallback<List<User>>() {
	    public void onFailure(Throwable caught) {
		Log.debug("There was an error fetching data from the server");
	    }

	    public void onSuccess(List<User> users) {
		Log.debug("6: size: " + users.size());

		// load all users into the grid
		for (User user : users) {
		    ListGridRecord record = new ListGridRecord();
		    record.setAttribute(IDataSourceManager.ID_FIELD, user.getId());
		    record.setAttribute(FIRST_NAME, user.getFirstName());
		    record.setAttribute(LAST_NAME, user.getLastName());
		    record.setAttribute(NICK_NAME, user.getNickName());

		    addData(record);
		}
		Log.debug("7");

	    }
	});
	Log.debug("8");
    }
}
