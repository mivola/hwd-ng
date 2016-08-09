package com.voigt.hwd.server.comm;

import java.util.List;
import java.util.Map;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;

public interface IDataImporter {

	/**
	 * get the details for a given match; since it id not known what details are
	 * available, we just provide a simple map
	 * 
	 * @param match
	 * @return
	 */
	public Map<String, String> getMatchDetails(Match match);

	/**
	 * returns a list of matches for the particular matchday and league
	 * 
	 * @param matchDay
	 * @param league
	 * @return
	 */
	public List<Match> getMatchesOfMatchDay(MatchDay matchDay, League league);

}
