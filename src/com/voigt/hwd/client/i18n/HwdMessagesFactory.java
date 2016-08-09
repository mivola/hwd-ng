package com.voigt.hwd.client.i18n;

import com.google.gwt.core.client.GWT;

public class HwdMessagesFactory {

	private HwdMessagesFactory() {
		// private
	}

	public static HwdMessages getInstance() {
		return (HwdMessages) GWT.create(HwdMessages.class);
	}

}
