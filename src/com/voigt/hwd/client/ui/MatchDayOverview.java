package com.voigt.hwd.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.service.DefaultCallback;
import com.voigt.hwd.client.service.MatchService;
import com.voigt.hwd.client.service.MatchServiceAsync;
import com.voigt.hwd.domain.Bet;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;
import com.voigt.hwd.domain.League.LeagueType;

public class MatchDayOverview extends Composite {

	final Map<Integer, MatchFixtureForm> fixtureMap = new HashMap<Integer, MatchFixtureForm>();

	final SeasonComboBox seasonComboBox = new SeasonComboBox();

	final MatchDayComboBox matchDayComboBox = new MatchDayComboBox();

	final IButton saveMatchesButton = new IButton();

	final IButton queryOLButton = new IButton();

	public MatchDayOverview() {

		final VLayout layout = new VLayout();

		seasonComboBox.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				SeasonComboBox seasonComboBox = (SeasonComboBox) event.getSource();
				Season selectedSeason = seasonComboBox.getSelectedSeason();
				matchDayComboBox.setSeason(selectedSeason);
			}
		});

		// add listener to matchDayComboBox to fire queryOLButton
		matchDayComboBox.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				int matchDayOLId = ((MatchDayComboBox) event.getItem()).getSelectedMatchDay().getMatchDayId();
				Log.debug("new matchday selected: " + matchDayOLId);
				loadMatchDayDataFromOL();
			}
		});

		queryOLButton.setTitle("OL-Daten holen");
		queryOLButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				Log.debug("starting to load matchday data...");
				loadMatchDayDataFromOL();
			}

		});

		DynamicForm form = new DynamicForm();
		form.setWidth100();
		form.setNumCols(18);
		form.setTitleSuffix("");

		// TextItem textItem = new TextItem(LONG_TEXT);

		// adding the top selection elements and buttons
		form.setFields(seasonComboBox, matchDayComboBox);

		HLayout topItemsLayout = new HLayout();
		topItemsLayout.addMember(form);
		topItemsLayout.addMember(queryOLButton);
		topItemsLayout.setMargin(10);

		layout.addMember(topItemsLayout);

		// add the fixtures for 1.BL
		for (int i = 0; i < 9; i++) {
			MatchFixtureForm matchFixture = new MatchFixtureForm(new Match(), new Bet());
			// matchFixture.setHeight("15px");

			// HLayout hLayout = new HLayout();
			// hLayout.addChild(matchFixture);
			// hLayout.setWidth100();
			// hLayout.setAutoHeight();
			// hLayout.setBorder("2px solid blue");
			// layout.addMember(hLayout);

			matchFixture.setHeight("35px");

			// matchFixture.addJokerChangeListener(new ChangeListener() {
			//
			// public void onChange(Widget sender) {
			// Log.debug("joker value changed");
			// MatchFixture matchFixture = (MatchFixture) sender.getParent();
			// BetJoker joker = matchFixture.getJoker();
			// if (joker == BetJoker.STANDARD) {
			// // a joker has been set, therefore all other jokers need
			// // to be deleted
			// deleteAllOtherJokers(matchFixture);
			// }
			// }
			//
			// });

			layout.addMember(matchFixture);
			fixtureMap.put(i, matchFixture);
		}

		// save matches button
		saveMatchesButton.setTitle("Daten speichern");
		saveMatchesButton.addClickHandler(new ClickHandler() {

			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {

				List<Match> matchesToBeSaved = new ArrayList<Match>();

				Iterator<Integer> iterator = fixtureMap.keySet().iterator();
				while (iterator.hasNext()) {
					MatchFixtureForm matchFixture = fixtureMap.get(iterator.next());
					Match match = matchFixture.getMatch();
					matchesToBeSaved.add(match);
					Log.debug("save match: " + match);
				}

				MatchServiceAsync service = MatchService.Util.getInstance();
				service.saveMatches(matchesToBeSaved, new DefaultCallback<Boolean>() {

					public void onSuccess(Boolean result) {

						Log.debug("matches saved!!! :-)");
						// reload data...
						queryOLButton.fireEvent(new ClickEvent(queryOLButton.getJsObj()));
					}

				});

			}

		});

		HLayout bottomItemsLayout = new HLayout();
		bottomItemsLayout.setAlign(Alignment.RIGHT);
		bottomItemsLayout.addMember(saveMatchesButton);

		layout.addMember(bottomItemsLayout);

		layout.setAutoHeight();
		layout.setAutoWidth();

		initWidget(layout);

	}

	protected void deleteAllOtherJokers(MatchFixture matchFixtureToExclude) {

		// while (fixtureMap.keySet().iterator().hasNext()) {
		// Integer fixtureNumber = fixtureMap.keySet().iterator().next();
		// MatchFixtureFormItem matchFixture = fixtureMap.get(fixtureNumber);
		//
		// if (matchFixture != matchFixtureToExclude) {
		// matchFixture.deleteJoker();
		// }
		// }

	}

	private void loadMatchDayDataFromOL() {

		MatchServiceAsync service = MatchService.Util.getInstance();

		Season selectedSeason = seasonComboBox.getSelectedSeason();
		Log.debug("selected season: " + selectedSeason);

		MatchDay selectedMatchDay = matchDayComboBox.getSelectedMatchDay();

		List<League> leagues = selectedSeason.getLeagues();

		for (League league : leagues) {
			// 1. Bundesliga
			if (league.getType() == LeagueType.FIRST_BL) {
				service.getMatches(selectedMatchDay, league, new DefaultCallback<List<Match>>() {

					public void onSuccess(List<Match> matches) {

						boolean matchDayIsDirty = false;
						int i = 0;
						for (Match match : matches) {
							Log.debug("match: " + match);

							MatchFixtureForm fixture = fixtureMap.get(i++);

							fixture = new MatchFixtureForm(match, new Bet());
							fixtureMap.put(i, fixture);
							// fixture.setMatch(match);

							if (match.isDirty()) {
								matchDayIsDirty = true;
							}

						}

						// set the state of the save button
						saveMatchesButton.setDisabled(!matchDayIsDirty);

					}

				});

			}
			// 2. Bundesliga
			if (league.getType() == LeagueType.SECOND_BL) {

				service.getMatches(selectedMatchDay, league, new DefaultCallback<List<Match>>() {

					public void onSuccess(List<Match> matches) {

						for (Match match : matches) {
							Log.debug("match: " + match);
						}

					}

				});
			}
		}

	}

	// public Map<Integer, MatchFixture> getFixtureMap() {
	// return fixtureMap;
	// }
}
