package com.voigt.hwd.comm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.groovy.control.CompilationFailedException;
import org.junit.Before;
import org.junit.Test;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.League.LeagueType;
import com.voigt.hwd.server.comm.IDataImporter;
import com.voigt.hwd.server.comm.OpenLigaDataImporter;

public class CommTester {

	Match match = new Match();

	@Before
	public void setup() {
		match.setOLId(1103);
	}

	@Test
	public void testGoovyOpenLigaImporter() {

		IDataImporter importer = new OpenLigaDataImporter();
		Map<String, String> matchDetails = importer.getMatchDetails(match);
		for (String key : matchDetails.keySet()) {
			String value = matchDetails.get(key);
			System.out.println("key: " + key + "; value:" + value);
		}
		assertTrue(matchDetails.size() > 3);
		assertNotNull(matchDetails.get("groupID"));

		MatchDay matchDay = new MatchDay();
		matchDay.setMatchDayId(28);

		League league = new League();
		league.setType(LeagueType.FIRST_BL);
		league.setOLShortcut("bl1");

		List<Match> matchesOfMatchDay = importer.getMatchesOfMatchDay(matchDay, league);
		for (Match match : matchesOfMatchDay) {
			System.out.println(match.getOLId());
		}
		assertEquals(9, matchesOfMatchDay.size());
		assertTrue(matchesOfMatchDay.get(0).getOLId() > 0);
		// assertTrue(matchesOfMatchDay.get(0).getTeam1OLId() > 0);
		assertTrue(matchesOfMatchDay.get(0).getId() == 0);

	}

	// @Test
	public void testGoovyOpenLigaDirect() {

		ClassLoader parent = CommTester.class.getClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);

		try {
			Class<?> gImporter = loader.loadClass("com.voigt.hwd.server.comm.GroovyOLImporter");

			GroovyObject goImporter = (GroovyObject) gImporter.newInstance();
			IDataImporter dataImporter = (IDataImporter) goImporter;

			Map<String, String> matchDetails = dataImporter.getMatchDetails(match);
			Set<String> keySet = matchDetails.keySet();

			for (String key : keySet) {
				String value = matchDetails.get(key);
				System.out.println("key: " + key + "; value:" + value);
			}

		} catch (CompilationFailedException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
