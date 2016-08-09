package com.voigt.hwd.client.ui;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;

public class MatchDayComboBox extends SelectItem {

	private final Map<Integer, MatchDay> matchDays = new HashMap<Integer, MatchDay>();

	public MatchDayComboBox() {
		super("Spieltag");
	}

	public MatchDayComboBox(Season season) {
		new MatchDayComboBox();
		setSeason(season);
	}

	public void setSeason(Season season) {
		refreshValueMap(season);
	}

	private void refreshValueMap(Season season) {

		this.matchDays.clear();
		// this.setValueMap(new LinkedHashMap<String, String>());

		// which matchDay is active?
		int activeMatchDayId = -1;
		boolean previousMatchDayIsFinished = false;

		// get the list of all seasons
		LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();

		// loop through all matchdays
		List<MatchDay> matchDays = season.getMatchDays();
		for (MatchDay matchDay : matchDays) {

			// put the matchday into the combobox
			values.put("" + matchDay.getId(), "" + matchDay.getMatchDayId());

			// put the season into the local hash map
			this.matchDays.put(matchDay.getId(), matchDay);

			// check if the current matchday is already finished
			if (matchDay.isFinished()) {
				// porepare for the next round
				previousMatchDayIsFinished = true;
			} else {
				// the current matchday is not finished
				if (previousMatchDayIsFinished) {
					// the previous matchday was finished, therefore the current
					// one is the lowest non-finished matchday
					activeMatchDayId = matchDay.getId();
					previousMatchDayIsFinished = false;
				}
			}

			// pre-select the first matchDay
			if (activeMatchDayId == -1) {
				activeMatchDayId = matchDay.getId();
			}

		}

		// set the values and the default value
		this.setValueMap(values);
		this.setValue(activeMatchDayId);

	}

	public MatchDay getSelectedMatchDay() {
		// there is always one matchday selected??

		int selectedMatchDayId = 0;

		Object value = this.getValue();
		if (value instanceof String) {
			selectedMatchDayId = Integer.parseInt((String) value);
		} else if (value instanceof Integer) {
			selectedMatchDayId = (Integer) value;
		}

		Log.debug("selected matchDay: " + selectedMatchDayId);
		return matchDays.get(selectedMatchDayId);
	}
}
