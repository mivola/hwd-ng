package com.voigt.hwd.server.service;

import java.io.Serializable;
import java.util.List;

import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;

/**
 * Interface of the team service
 */
public interface IMatchFixtureService extends IGenericService<Match, Serializable> {

	public static final String NAME = "matchFixtureService";

	public List<MatchDay> getMatchDays(Season season);

	/**
	 * @return all MatchDays of all leagues in the current season
	 */
	public List<MatchDay> getMatchDays();

	/**
	 * 
	 * @param matchDayOLId
	 * @return the MatchDay with the given OpenLigaDB-ID
	 */
	public MatchDay getMatchDayByOLId(int matchDayOLId);

	/**
	 * reloads the given matchDay from DB and pre-loads some associations
	 * 
	 * @param matchDay
	 * @return the MatchDay to be reloaded
	 */
	public MatchDay getMatchDay(MatchDay matchDay);

}