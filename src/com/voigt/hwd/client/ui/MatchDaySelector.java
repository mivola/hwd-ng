package com.voigt.hwd.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.service.DefaultCallback;
import com.voigt.hwd.client.service.MatchService;
import com.voigt.hwd.client.service.MatchServiceAsync;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;
import com.voigt.hwd.domain.League.LeagueType;

public class MatchDaySelector extends Composite implements SourcesChangeEvents {

	private final SeasonComboBox seasonComboBox = new SeasonComboBox();

	private final MatchDayComboBox matchDayComboBox = new MatchDayComboBox();

	private final ButtonItem queryMatchDayDataButton = new ButtonItem();

	private List<Match> matches = new ArrayList<Match>();

	private boolean matchDayIsDirty = false;

	private final ChangeListenerCollection changeListenerCollection = new ChangeListenerCollection();

	public MatchDaySelector() {

		seasonComboBox.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				SeasonComboBox seasonComboBox = (SeasonComboBox) event.getSource();
				Season selectedSeason = seasonComboBox.getSelectedSeason();
				matchDayComboBox.setSeason(selectedSeason);

				// remove old matches
				setMatches(new ArrayList<Match>());

			}
		});

		// add listener to matchDayComboBox to fire queryOLButton
		matchDayComboBox.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				int matchDayOLId = ((MatchDayComboBox) event.getItem()).getSelectedMatchDay().getMatchDayId();
				Log.debug("new matchday selected: " + matchDayOLId);
				loadMatchDayData();
			}
		});

		queryMatchDayDataButton.setTitle("OL-Daten holen");
		queryMatchDayDataButton.setStartRow(false);
		queryMatchDayDataButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Log.debug("starting to load matchday data...");
				loadMatchDayData();
			}

		});

		final VLayout layout = new VLayout();
		layout.setBorder("2px dotted black");
		DynamicForm form = new DynamicForm();
		form.setWidth100();
		form.setNumCols(6);
		form.setTitleSuffix("");

		// adding the top selection elements and buttons
		form.setFields(seasonComboBox, matchDayComboBox, queryMatchDayDataButton);

		layout.addMember(form);

		layout.setAutoHeight();
		layout.setAutoWidth();

		initWidget(layout);

	}

	protected void loadMatchDayData() {

		MatchServiceAsync service = MatchService.Util.getInstance();

		// get the selected season
		Season selectedSeason = seasonComboBox.getSelectedSeason();
		Log.debug("selected season: " + selectedSeason);

		MatchDay selectedMatchDay = matchDayComboBox.getSelectedMatchDay();

		// get all leagues in this season
		List<League> leagues = selectedSeason.getLeagues();

		for (League league : leagues) {
			// 1. Bundesliga
			if (league.getType() == LeagueType.FIRST_BL) {
				service.getMatches(selectedMatchDay, league, new DefaultCallback<List<Match>>() {

					public void onSuccess(List<Match> matches) {

						setMatches(matches);

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

	private void setMatches(List<Match> matches) {
		this.matches = matches;

		matchDayIsDirty = false;
		for (Match match : matches) {
			Log.debug("match: " + match);

			if (match.isDirty()) {
				matchDayIsDirty = true;
			}

		}

		changeListenerCollection.fireChange(this);

	}

	public void addChangeListener(ChangeListener listener) {
		this.changeListenerCollection.add(listener);
	}

	public void removeChangeListener(ChangeListener listener) {
		this.changeListenerCollection.remove(listener);
	}

	public List<Match> getMatches() {
		return matches;
	}

}
