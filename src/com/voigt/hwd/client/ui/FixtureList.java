package com.voigt.hwd.client.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.bet.IBetGenerator;
import com.voigt.hwd.client.bet.WeightedRandomBetGenerator;
import com.voigt.hwd.client.service.DefaultCallback;
import com.voigt.hwd.client.service.MatchService;
import com.voigt.hwd.client.service.MatchServiceAsync;
import com.voigt.hwd.domain.Bet;
import com.voigt.hwd.domain.Match;

public class FixtureList extends VLayout {

	private List<Match> matches = new ArrayList<Match>();
	private final List<MatchFixtureForm> matchFixtureForms = new ArrayList<MatchFixtureForm>();
	private final MatchDaySelector matchDaySelector;
	private SaveButtonPanel saveBetsButtonPanel;

	public FixtureList(List<Match> matches, MatchDaySelector matchDaySelector) {

		this.matches = matches;
		this.matchDaySelector = matchDaySelector;

		// VLayout layout = new VLayout();
		this.setBorder("1px dotted yellow");

		for (Match match : matches) {

			MatchFixtureForm matchFixtureForm = new MatchFixtureForm(match, new Bet());
			matchFixtureForms.add(matchFixtureForm);
			this.addMember(matchFixtureForm);

		}

		if ((matches != null) && (matches.size() > 0)) {
			saveBetsButtonPanel = createSaveButtonPanel();
			this.addMember(saveBetsButtonPanel);
		}

		// initWidget(layout);

	}

	private SaveButtonPanel createSaveButtonPanel() {
		SaveBetsButtonPanel saveButtonPanel = new SaveBetsButtonPanel();
		saveButtonPanel.setAutoHeight();
		saveButtonPanel.setAutoWidth();

		saveButtonPanel.setBorder("1px solid blue");
		saveButtonPanel.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {

				Log.debug("need to save data");

				List<Match> matchesToBeSaved = getMatches();

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

		saveButtonPanel.addRunBetGeneratorClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				Log.debug("generating bets ...");
				// generate bets
				List<Match> matches = getMatches();
				IBetGenerator random = new WeightedRandomBetGenerator();
				Map<Match, Bet> generatedBets = random.generateBets(matches);

				// dont create completely new FixtureForms, just use setBet(bet)

				createFixtureList(generatedBets);

			}

		});

		return saveButtonPanel;
	}

	protected void createFixtureList(Map<Match, Bet> generatedBets) {

		// remove all the old stuff
		for (MatchFixtureForm matchFixtureForm : matchFixtureForms) {
			this.removeMember(matchFixtureForm);
		}
		this.matchFixtureForms.clear();
		this.matches.clear();
		if ((saveBetsButtonPanel != null) && (this.contains(saveBetsButtonPanel))) {
			this.removeMember(saveBetsButtonPanel);
		}

		// add the new stuff
		Iterator<Match> iterator = generatedBets.keySet().iterator();
		while (iterator.hasNext()) {
			Match match = iterator.next();
			Bet bet = generatedBets.get(match);

			MatchFixtureForm matchFixtureForm = new MatchFixtureForm(match, bet);
			this.matchFixtureForms.add(matchFixtureForm);
			this.matches.add(match);
			this.addMember(matchFixtureForm);
		}

		if ((matches != null) && (matches.size() > 0)) {
			SaveButtonPanel saveBetsButtonPanel = createSaveButtonPanel();
			this.addMember(saveBetsButtonPanel);
		}

	}

	public List<Match> getMatches() {
		return matches;
	}

}
