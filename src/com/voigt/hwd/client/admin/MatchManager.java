package com.voigt.hwd.client.admin;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.ui.MatchDayOverview;

public class MatchManager extends AbstractBasePanel {

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			MatchManager panel = new MatchManager();
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

	@Override
	public Canvas getViewPanel() {

		final VLayout layout = new VLayout();

		layout.addMember(new MatchDayOverview());

		return layout;
	}
}
