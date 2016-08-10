package com.voigt.hwd.server.comm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.groovy.control.CompilationFailedException;

import com.allen_sauer.gwt.log.client.Log;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class OpenLigaDataImporter implements IDataImporter {

	private static IOLImporter oLImporter;

	public OpenLigaDataImporter() {

		try {

			if (oLImporter == null) {
				ClassLoader parent = OpenLigaDataImporter.class.getClassLoader();
				GroovyClassLoader loader = new GroovyClassLoader(parent);
				Class<?> gImporter = loader.loadClass("com.voigt.hwd.server.comm.GroovyOLImporter");
				GroovyObject goImporter = (GroovyObject) gImporter.newInstance();
				oLImporter = (IOLImporter) goImporter;
			}

		} catch (CompilationFailedException e) {
			Log.debug("error: ", e);
		} catch (ClassNotFoundException e) {
			Log.debug("error: ", e);
		} catch (Exception e) {
			Log.debug("error: ", e);
		}

	}

	public Map<String, String> getMatchDetails(Match match) {

		Map<String, String> matchDetails = oLImporter.getOLDetails(match.getOLId());
		Set<String> keySet = matchDetails.keySet();

		for (String key : keySet) {
			String value = matchDetails.get(key);
			Log.debug("key: " + key + "; value:" + value);
		}

		return matchDetails;

	}

	public List<Match> getMatchesOfMatchDay(MatchDay matchDay, League league) {
		List<Match> matches = new ArrayList<Match>();

		Season season = matchDay.getSeason();
		int year = season.getYear();

		String oLShortcut = league.getOLShortcut();
		List<Match> matchesOfMatchDayInLeague = oLImporter.getMatchesOfMatchDay(year, oLShortcut,
				matchDay.getMatchDayId());
		matches.addAll(matchesOfMatchDayInLeague);

		return matches;
	}

}
