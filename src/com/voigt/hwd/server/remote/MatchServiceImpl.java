package com.voigt.hwd.server.remote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.voigt.hwd.client.service.MatchService;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;
import com.voigt.hwd.domain.Team;
import com.voigt.hwd.server.comm.IDataImporter;
import com.voigt.hwd.server.comm.OpenLigaDataImporter;
import com.voigt.hwd.server.service.IGenericService;
import com.voigt.hwd.server.service.IMatchFixtureService;
import com.voigt.hwd.server.service.ITeamService;

public class MatchServiceImpl extends AbstractGwtRemoteService implements MatchService {

	private static final long serialVersionUID = -3791405030776100242L;

	private final ITeamService teamService;
	private final IMatchFixtureService matchFixtureService;
	private final IGenericService<Season, Serializable> seasonService;
	private final IGenericService<MatchDay, Serializable> matchDayService;

	/* map of teams based on their OpenLigaDB-ID */
	/* ..............<OLDB-ID, Team> */
	private final Map<Integer, Team> teams = new HashMap<Integer, Team>();

	public MatchServiceImpl() {
		super();
		teamService = getContext().getTeamService();
		matchFixtureService = getContext().getMatchFixtureService();
		seasonService = getContext().getSeasonService();
		matchDayService = getContext().getMatchDayService();

		List<Team> allTeams = teamService.getAll();
		for (Team team : allTeams) {
			teams.put(team.getOLId(), team);
		}

	}

	@Override
	public List<Match> getMatches(MatchDay matchDay, League league) {

		// load the matchday from the database
		matchDay = matchFixtureService.getMatchDay(matchDay);

		// matches that are already saved in the matchday
		List<Match> matches = matchDay.getMatches();

		// a map with all matches filled with data from own DB
		// <oLId..., match>
		Map<Integer, Match> existingMatches = new HashMap<Integer, Match>();
		for (Match match : matches) {
			existingMatches.put(match.getOLId(), match);
		}

		// get the data about this matchDay from OpenLigaDB
		IDataImporter importer = new OpenLigaDataImporter();
		List<Match> oLMatches = importer.getMatchesOfMatchDay(matchDay, league);

		// collect the new/updated matches
		List<Match> updatedMatches = new ArrayList<Match>();

		// loop through all known matched from OpenLigaDB and check if there is
		// an equivalent in our own DB
		for (Match oLMatch : oLMatches) {
			int oLMatchId = oLMatch.getOLId();

			Match match = existingMatches.get(oLMatchId);

			// match doesnt exists yet, so we create a new one
			if (match == null) {
				match = new Match();
				match.setOLId(oLMatchId);
				match.setMatchDay(matchDay);
				match.setLeague(league);
				match.setDirty(true);
			}

			// kickoff time set?
			if (match.getKickOffDate() == null) {
				match.setKickOffDate(oLMatch.getKickOffDate());
				match.setDirty(true);
			}

			// results available?
			if (match.getResult1() == Match.NO_RESULT) {
				match.setResult1(oLMatch.getResult1());
				match.setResult2(oLMatch.getResult2());
				match.setDirty(true);
			}

			// teams set?
			if (match.getTeam1() == null) {
				// TODO: use one list with all teams and keep it for this
				// request
				// Team team1 = teamService.getByOLId(oLMatch.getTeam1OLId());
				// Team team2 = teamService.getByOLId(oLMatch.getTeam2OLId());
				Team team1 = teams.get(oLMatch.getTeam1OLId());
				Team team2 = teams.get(oLMatch.getTeam2OLId());
				match.setTeam1(team1);
				match.setTeam2(team2);
				match.setDirty(true);
			}

			match.setTeam1OLId(oLMatch.getTeam1OLId());
			match.setTeam2OLId(oLMatch.getTeam2OLId());

			// add the list of new/updated matches
			updatedMatches.add(match);
			Log.debug("match added: " + match);
		}

		return updatedMatches;
	}

	@Override
	public List<Season> getSeasons() {
		IGenericService<Season, Serializable> seasonService = getContext().getSeasonService();

		List<Season> allSeasons = seasonService.getAll();
		for (Season season : allSeasons) {
			// get all matchdays to avoid reloading in client
			season.getMatchDays().size();
			season.getLeagues().size();
		}
		return allSeasons;
	}

	@Override
	public boolean saveMatches(List<Match> matches) {
		for (Match match : matches) {
			try {
				matchFixtureService.saveOrUpdate(match);
			} catch (Throwable e) {
				Log.debug("error while saving match: " + match, e);
				Log.error("error while saving match: " + match, e);
			}
		}
		return true;
	}

}
