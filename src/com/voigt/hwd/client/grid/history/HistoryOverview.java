package com.voigt.hwd.client.grid.history;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.types.TabBarControls;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.i18n.HwdMessages;
import com.voigt.hwd.client.i18n.HwdMessagesFactory;

public class HistoryOverview extends AbstractBasePanel {

	private static final String DESCRIPTION = "<p>die einzelnen Jahre im �berblick ...</p>";

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			HistoryOverview panel = new HistoryOverview();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}
	}

	@Override
	public Canvas getViewPanel() {

		final VLayout layout = new VLayout(15);

		Label label = new Label();
		label.setHeight(10);
		label.setWidth100();
		// label.setWidth(250);
		label.setContents("HWD - die einzelnen Jahre");
		layout.addMember(label);

		TabSet tabSet = new TabSet();

		tabSet.setTabBarThickness(24);
		tabSet.setWidth100();
		tabSet.setHeight100();
		tabSet.setCloseTabIcon("[SKIN]/Window/close.png");

		LayoutSpacer layoutSpacer = new LayoutSpacer();
		layoutSpacer.setWidth(5);

		tabSet.setTabBarControls(TabBarControls.TAB_SCROLLER, TabBarControls.TAB_PICKER, layoutSpacer);

		HwdMessages messages = HwdMessagesFactory.getInstance();

		for (int i = 1999; i < 2008; i++) {
			Tab tab = new Tab();

			String seasonString = messages.seasonString(i, (i + 1));
			tab.setTitle(seasonString);

			HistoryOverviewDataProvider dataProvider = new HistoryOverviewDataProvider();
			HistoryOverviewData data = dataProvider.getData(i);

			Window wnd = new HistoryOverviewWindow(data);

			tab.setPane(wnd);

			tabSet.addTab(tab);
		}

		layout.addMember(tabSet);

		return layout;
	}

	@SuppressWarnings("unused")
	private static native JavaScriptObject getJson(String varName)
	/*-{ return $wnd[varName]; }-*/
	;

}