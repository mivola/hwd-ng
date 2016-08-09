package com.voigt.hwd.server.comm;

import java.util.List;
import java.util.Map;

import com.voigt.hwd.domain.Match;

/**
 * interface for openLeagueDB importer
 */
public interface IOLImporter {

	/**
	 * get the details for a given match; since it id not known what details are
	 * available, we just provide a simple map
	 * 
	 * @param oLId
	 *            openLigaID of the match
	 * @return
	 */
	public Map<String, String> getOLDetails(int oLId);

	/**
	 * 
	 * @param season
	 * @param leagueShort
	 * @param oLMatchDayId
	 * @return
	 */
	public List<Match> getMatchesOfMatchDay(int season, String leagueShort, int oLMatchDayId);

}
