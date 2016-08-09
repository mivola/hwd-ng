package com.voigt.hwd.client.admin.ds;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.voigt.hwd.client.service.UserService;
import com.voigt.hwd.client.service.UserServiceAsync;
import com.voigt.hwd.domain.User;

public class UserDataSourceManager extends AbstractDataSourceManager<User> {

	private final UserServiceAsync userService;

	public UserDataSourceManager() {

		super(new UserDataSource());
		// super(new UserGwtRpcDataSource());

		// set the service that will be used for loading and saving objects
		userService = UserService.Util.getInstance();
	}

	public boolean updateExisting(int pk, final FormItem[] fields) {

		userService.getOneEntry(pk, new AsyncCallback<User>() {

			public void onFailure(Throwable caught) {
				Log.error("FAIL");

			}

			public void onSuccess(User user) {
				// set the new properties of the user
				user = setUserDetails(fields, user);

				Log.debug("changed user: " + user);

				userService.saveOneEntry(user, new AsyncCallback<Object>() {

					public void onFailure(Throwable caught) {
						Log.error("FAIL");

					}

					public void onSuccess(Object result) {
						// TODO Auto-generated method stub
						Log.debug("YESSSS: saved");

					}

				});
			}

		});

		return true;
	}

	private User setUserDetails(final FormItem[] fields, User user) {
		String firstName = getStringValue(fields, UserDataSource.FIRST_NAME);
		if (firstName != null) {
			user.setFirstName(firstName);
		}
		String lastName = getStringValue(fields, UserDataSource.LAST_NAME);
		if (lastName != null) {
			user.setLastName(lastName);
		}
		String nickName = getStringValue(fields, UserDataSource.NICK_NAME);
		if (nickName != null) {
			user.setNickName(nickName);
		}
		return user;
	}

	public boolean saveNew(FormItem[] fields) {

		boolean success = false;

		User user = new User();
		user = setUserDetails(fields, user);

		userService.saveOneEntry(user, new AsyncCallback<Object>() {

			public void onFailure(Throwable caught) {
				Log.error("FAIL");

			}

			public void onSuccess(Object result) {
				Log.debug("YESSSS: save new: " + result);
			}

		});
		return true;
	}

	public boolean delete(int pk) {

		userService.deleteOneEntry(pk, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				Log.error("FAIL");

			}

			public void onSuccess(Object result) {
				Log.debug("YESSSS: deleted: " + result);
			}

		});

		return true;

	}

}
