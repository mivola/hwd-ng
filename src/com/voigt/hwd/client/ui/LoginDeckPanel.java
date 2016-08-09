package com.voigt.hwd.client.ui;

import java.util.HashMap;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.service.UserService;
import com.voigt.hwd.client.service.UserServiceAsync;
import com.voigt.hwd.client.session.LoginStatus;

public class LoginDeckPanel extends Canvas {

	/* a list of canvas that could be displayed */
	private final HashMap<Object, Canvas> cards = new HashMap<Object, Canvas>();

	/* which card is currently displayed? */
	private Canvas activeCard = null;

	private static final int LOGIN = 0;
	private static final int LOGGED_IN = 1;

	private LoginStatus loginStatus = new LoginStatus();

	public LoginDeckPanel() {

		final Label helloUserLabel = new Label("Hello " + loginStatus.getUserName());

		// login page
		DynamicForm loginForm = new DynamicForm();
		loginForm.setNumCols(2);
		loginForm.setPadding(3);
		loginForm.setMargin(3);
		loginForm.setGroupTitle("Login");
		loginForm.setIsGroup(true);
		loginForm.setTitleSuffix("");
		loginForm.setSaveOnEnter(true);

		final TextItem nameItem = new TextItem("Username");
		nameItem.setWidth("100%");
		nameItem.setAlign(Alignment.RIGHT);

		final PasswordItem passwordItem = new PasswordItem("Passwort");
		passwordItem.setWidth("100%");
		passwordItem.setAlign(Alignment.RIGHT);

		ButtonItem submitButton = new ButtonItem("Login");
		submitButton.setAlign(Alignment.RIGHT);
		submitButton.setColSpan(2);
		submitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				SC.showPrompt("test");

				// TODO do the login
				String name = (String) nameItem.getValue();
				String password = (String) passwordItem.getValue();

				UserServiceAsync userService = UserService.Util.getInstance();
				userService.loginUser(name, password, new AsyncCallback<LoginStatus>() {

					public void onSuccess(LoginStatus loginStatus) {
						setLoginStatus(loginStatus);
						helloUserLabel.setContents("Hello " + loginStatus.getUserName());
						// helloUserLabel.draw();
						showCard(LOGGED_IN);
					}

					public void onFailure(Throwable caught) {
						// TODO display error message
						Log.error("error while login");
					}

				});

				SC.clearPrompt();

			}

		});

		loginForm.setFields(nameItem, passwordItem, submitButton);

		VLayout loginLayout = new VLayout();
		loginLayout.addMember(loginForm);

		// logged-in page
		VLayout loggedinLayout = new VLayout();

		IButton logoutButton = new IButton("Logout");
		logoutButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {

				// TODO do the logout
				String userName = loginStatus.getUserName();

				UserServiceAsync userService = UserService.Util.getInstance();
				userService.logoffUser(userName, new AsyncCallback<Boolean>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					public void onSuccess(Boolean loggedOff) {
						// helloUserLabel.setTitle("Hello " +
						// loginStatus.getUserName());
						showLogin();
					}

				});

			}

		});
		loggedinLayout.setPadding(3);
		loggedinLayout.setMargin(3);
		loggedinLayout.addMember(helloUserLabel);
		loggedinLayout.addMember(logoutButton);

		this.addCard(LOGIN, loginLayout);
		this.addCard(LOGGED_IN, loggedinLayout);
		this.showCard(LOGIN);

		loginForm.setAutoHeight();
		// loginForm.setAutoWidth();
		loginLayout.setAutoHeight();
		// loginLayout.setAutoWidth();

	}

	public void addCard(Object key, Canvas card) {
		card.setWidth100();
		card.setHeight100();
		card.setPageLeft(0);
		card.setPageTop(0);
		card.hide();
		this.addChild(card);
		cards.put(key, card);
	}

	public void showCard(Object key) {

		// which card should be shown?
		Canvas cardToShow = cards.get(key);

		// no action needed id this card is already shown
		if (cardToShow != activeCard) {

			// hide the active card
			if (activeCard != null) {
				activeCard.hide();
			}

			// show the new card
			cardToShow.bringToFront();
			cardToShow.show();

			// make the new card the active card
			activeCard = cardToShow;
		}
	}

	public void showLogin() {
		this.showCard(LOGIN);
	}

	public void showLoggedIn() {
		this.showCard(LOGGED_IN);
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

}
