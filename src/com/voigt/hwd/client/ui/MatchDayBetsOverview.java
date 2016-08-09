package com.voigt.hwd.client.ui;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.WidgetCanvas;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.service.DefaultCallback;
import com.voigt.hwd.client.service.MatchService;
import com.voigt.hwd.client.service.MatchServiceAsync;
import com.voigt.hwd.domain.Match;

public class MatchDayBetsOverview extends Composite {

	private FixtureList fixtureList;
	MatchDaySelector matchDaySelector;
	SaveButtonPanel saveButtonPanel;
	protected WidgetCanvas widget;

	public MatchDayBetsOverview() {

		final VLayout layout = new VLayout();
		layout.setBorder("2px dotted red");
		matchDaySelector = new MatchDaySelector();

		// fixtureList = new FixtureList(new ArrayList<Match>());

		// createSaveButtonPanel();

		matchDaySelector.addChangeListener(new ChangeListener() {

			public void onChange(Widget sender) {
				List<Match> matches = matchDaySelector.getMatches();

				// if (widget != null) {
				// }
				if ((widget != null) && layout.contains(widget)) {
					layout.removeMember(widget);
				}
				if ((saveButtonPanel != null) && layout.contains(saveButtonPanel)) {
					layout.removeMember(saveButtonPanel);
				}

				if ((matches != null) && (matches.size() > 0)) {
					// fill fixtureList with matches
					fixtureList = new FixtureList(matches, matchDaySelector);
					widget = new WidgetCanvas(fixtureList);
					widget.setAutoHeight();
					widget.setAutoWidth();
					layout.addMember(widget);
					// createSaveButtonPanel();
					// layout.addMember(saveButtonPanel);
					// layout.setWidth100();
					// layout.setHeight100();

					// layout.addMember(fixtureList, 1);

					// if (!layout.contains(saveButtonPanel)) {
					// layout.addMember(saveButtonPanel);
					// } else {
					// saveButtonPanel.show();
					// saveButtonPanel.bringToFront();
					// }
				} else {

					// layout.removeMember(widget);
					// layout.removeMember(saveButtonPanel);

					// if (saveButtonPanel.isVisible()) {
					// saveButtonPanel.hide();
					// }
				}
			}

		});

		layout.addMember(matchDaySelector);
		// layout.addMember(fixtureList);
		// layout.addMember(saveButtonPanel);
		// layout.addMember(matchDaySelector, 0);
		// layout.addMember(fixtureList, 1);
		// layout.addMember(saveButtonPanel, 2);
		//
		layout.setAutoHeight();
		layout.setAutoWidth();

		initWidget(layout);

	}

	private void createSaveButtonPanel() {
		saveButtonPanel = new SaveButtonPanel();
		saveButtonPanel.setAutoHeight();
		saveButtonPanel.setAutoWidth();

		saveButtonPanel.setBorder("1px solid blue");
		saveButtonPanel.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {

				Log.debug("need to save data");

				List<Match> matchesToBeSaved = fixtureList.getMatches();

				MatchServiceAsync service = MatchService.Util.getInstance();
				service.saveMatches(matchesToBeSaved, new DefaultCallback<Boolean>() {

					public void onSuccess(Boolean result) {

						Log.debug("matches saved!!! :-)");
						// reload data...
						matchDaySelector.loadMatchDayData();
					}

				});

				// fixtureList.getBets();

			}

		});
	}

}
