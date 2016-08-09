package com.voigt.hwd.client.admin;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.FlowPanel;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.service.DefaultCallback;
import com.voigt.hwd.client.service.UserService;
import com.voigt.hwd.client.session.LoginStatus;

public class UsersOnlineManager extends AbstractBasePanel {

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			UsersOnlineManager panel = new UsersOnlineManager();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return "";
		}
	}

	// private static final String NO_USERS_ONLINE = "Keiner online ...";
	//
	// private Label usersLabel;

	private FlowPanel listPanel;

	@Override
	public Canvas getViewPanel() {

		VLayout layout = new VLayout();

		// usersLabel = new Label(NO_USERS_ONLINE);
		// layout.addMember(usersLabel);

		IButton refreshButton = new IButton("refresh");
		refreshButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				refreshUsersList();
			}

		});
		// layout.addMember(refreshButton);

		listPanel = new FlowPanel();

		// listPanel.setWidth("100%");
		layout.addMember(listPanel);

		refreshUsersList();

		return layout;
	}

	private void refreshUsersList() {
		UserService.Util.getInstance().getSessions(new DefaultCallback<List<LoginStatus>>() {

			public void onSuccess(List<LoginStatus> result) {
				setSessions(result);
			}

		});
	}

	protected void setSessions(List<LoginStatus> result) {

		StringBuffer sb = new StringBuffer();
		sb.append("Users online: ");

		listPanel.clear();

		for (LoginStatus loginStatus : result) {

			sb.append(loginStatus.getUserName() + " - " + loginStatus.getLastAction() + "; ");

			Log.debug("user online: " + loginStatus);

			Label userLabel = new Label(loginStatus.getUserName() + " - " + loginStatus.getLastAction());
			// userLabel.setAutoWidth();
			userLabel.setBorder("2px solid blue");
			listPanel.add(userLabel);

		}

		// if (result.size() > 0) {
		// usersLabel.setContents(sb.toString());
		// }

		// this.redraw();
	}
}
