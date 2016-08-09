package com.voigt.hwd.client.user;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.ui.MatchDayBetsOverview;

public class MatchBetsManager extends AbstractBasePanel {

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			MatchBetsManager panel = new MatchBetsManager();
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

		MatchDayBetsOverview matchDayOverview = new MatchDayBetsOverview();
		// matchDayOverview.set

		layout.addMember(matchDayOverview);

		return layout;
	}
}
