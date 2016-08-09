package com.voigt.hwd.client.ui;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ClickListenerCollection;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuButton;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class SaveBetsButtonPanel extends SaveButtonPanel {

	private final ClickListenerCollection runBetGeneratorListenerCollection = new ClickListenerCollection();

	@Override
	protected void createAdditionalWidgets() {

		final Menu menu = new Menu();
		// menu.setMargin(5);
		menu.setShowShadow(true);
		menu.setShadowDepth(10);

		MenuItem newItem = new MenuItem("New", "icons/16/document_plain_new.png", "Ctrl+N");
		MenuItem openItem = new MenuItem("Open", "icons/16/folder_out.png", "Ctrl+O");
		MenuItem saveAsItem = new MenuItem("Save As", "icons/16/save_as.png");

		newItem.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				Log.debug("newItem clicked!");
				runBetGeneratorListenerCollection.fireClick(menu);
			}
		});

		menu.setItems(newItem, openItem, saveAsItem);

		MenuButton menuButton = new MenuButton("File", menu);
		widgets.add(menuButton);
	}

	public void addRunBetGeneratorClickListener(ClickListener listener) {
		this.runBetGeneratorListenerCollection.add(listener);

	}

	public void removeRunBetGeneratorClickListener(ClickListener listener) {
		this.runBetGeneratorListenerCollection.remove(listener);
	}
}
