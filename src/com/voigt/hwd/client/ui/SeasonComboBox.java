package com.voigt.hwd.client.ui;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.voigt.hwd.client.service.DefaultCallback;
import com.voigt.hwd.client.service.MatchService;
import com.voigt.hwd.client.service.MatchServiceAsync;
import com.voigt.hwd.domain.Season;

public class SeasonComboBox extends SelectItem {

	private final Map<Integer, Season> seasons = new HashMap<Integer, Season>();

	public SeasonComboBox() {
		super("Saison");

		MatchServiceAsync service = MatchService.Util.getInstance();
		service.getSeasons(new DefaultCallback<List<Season>>() {

			public void onSuccess(List<Season> seasons) {
				refreshValueMap(seasons);
			}
		});
		setAddUnknownValues(false);
	}

	protected void refreshValueMap(List<Season> seasons) {

		// which season is active?
		int activeSeasonId = 1;
		// get the list of all seasons
		LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
		for (Season season : seasons) {
			// put the season into the combobox
			values.put("" + season.getId(), season.getTitle());

			// put the season into the local hash map
			this.seasons.put(season.getId(), season);

			// check if this is the actve season
			if (season.isActive()) {
				activeSeasonId = season.getId();
			}
		}

		// set the values and the default value
		this.setValueMap(values);
		this.setValue(activeSeasonId);
		fireEvent(new ChangedEvent(this.getJsObj()));

	}

	public Season getSelectedSeason() {
		// there is always one season selected??

		int selectedSeasonId = 0;

		Object value = this.getValue();
		if (value instanceof String) {
			selectedSeasonId = Integer.parseInt((String) value);
		} else if (value instanceof Integer) {
			selectedSeasonId = (Integer) value;
		}

		Log.debug("selected season: " + selectedSeasonId);
		return seasons.get(selectedSeasonId);
	}

}
