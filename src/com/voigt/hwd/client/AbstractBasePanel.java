package com.voigt.hwd.client;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class AbstractBasePanel extends VLayout {

	private final HLayout hLayout;

	public AbstractBasePanel() {

		setWidth100();
		setHeight100();

		hLayout = new HLayout();
		hLayout.setWidth100();
		hLayout.setMargin(10);
		hLayout.setMembersMargin(10);

		Canvas panel = getViewPanel();
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.addMember(panel);

		hLayout.addMember(wrapper);

		addMember(hLayout);
	}

	public String getHtmlUrl() {
		return null;
	}

	public String getXmlDataUrl() {
		return null;
	}

	public String getJsonDataUrl() {
		return null;
	}

	public String getCssUrl() {
		return null;
	}

	public abstract Canvas getViewPanel();

	public int getMainWindowWidth() {
		return this.getPageRect().getWidth();
	}

	public int getMainWindowHeight() {
		return this.getPageRect().getHeight();
	}

}
