package com.voigt.hwd.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginPanel extends Composite {

	private static final int LOGIN = 0;
	private static final int LOGGED_IN = 1;
	private final DeckPanel deckPanel = new DeckPanel();;

	public LoginPanel() {

		// login page
		DynamicForm loginForm = new DynamicForm();
		loginForm.setNumCols(2);
		loginForm.setPadding(3);
		loginForm.setMargin(3);
		loginForm.setGroupTitle("Login");
		loginForm.setIsGroup(true);
		loginForm.setTitleSuffix("");

		TextItem nameItem = new TextItem("Username");
		nameItem.setWidth(100);

		PasswordItem passwordItem = new PasswordItem("Passwort");
		passwordItem.setWidth(100);

		ButtonItem submitButton = new ButtonItem("Login");
		submitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				deckPanel.showWidget(LOGGED_IN);
			}

		});

		loginForm.setFields(nameItem, passwordItem, submitButton);

		VLayout loginLayout = new VLayout();
		loginLayout.addMember(loginForm);

		// logged-in page
		VLayout loggedinLayout = new VLayout();
		Label helloUserLabel = new Label("Hello User");
		loggedinLayout.addMember(helloUserLabel);

		deckPanel.add(loginLayout);
		deckPanel.add(loggedinLayout);
		deckPanel.showWidget(LOGIN);

		// loginForm.setAutoHeight();
		// loginForm.setAutoWidth();
		loginLayout.setAutoHeight();
		loginLayout.setAutoWidth();

		HLayout layout = new HLayout();
		layout.addMember(deckPanel);
		layout.setAutoHeight();
		layout.setAutoWidth();

		initWidget(layout);
	}

	public void showLogin() {
		deckPanel.showWidget(LOGIN);
	}

	public void showLoggedIn() {
		deckPanel.showWidget(LOGGED_IN);
	}

}
