package com.voigt.hwd.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ClickListenerCollection;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class SaveButtonPanel extends HLayout implements SourcesClickEvents {

	protected final List<Widget> widgets = new ArrayList<Widget>();
	private final IButton saveButton = new IButton();
	private final ClickListenerCollection clickListenerCollection = new ClickListenerCollection();

	public SaveButtonPanel() {

		createAdditionalWidgets();

		createSaveButton();

		for (Widget widget : widgets) {
			this.addMember(widget);
		}

		this.addMember(saveButton);
		this.setAutoWidth();
		this.setBorder("2px dotted darkgreen");
		this.setAlign(Alignment.RIGHT);

	}

	private void createSaveButton() {
		// save matches button
		saveButton.setTitle("Daten speichern");
		// saveButton.setMargin(5);
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				clickListenerCollection.fireClick(saveButton);
			}
		});

	}

	/**
	 * create some buttons and other stuff that should be displayed additionally
	 * to the save button; add each button to the "buttons" list
	 */
	protected void createAdditionalWidgets() {
		// do nothing here
	}

	public void addClickListener(ClickListener listener) {
		this.clickListenerCollection.add(listener);

	}

	public void removeClickListener(ClickListener listener) {
		this.clickListenerCollection.remove(listener);
	}
}
