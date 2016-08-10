package com.voigt.hwd.comm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.League.LeagueType;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.server.comm.IDataImporter;
import com.voigt.hwd.server.comm.IOLImporter;
import com.voigt.hwd.server.comm.OpenLigaDataImporter;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

@Ignore("fails due to openLigaDB.GetMatchByMatchID(oLId) returning null")
public class CommTester {

	Match match = new Match();

	@Before
	public void setup() {
		match.setOLId(39648);
	}

	@Test
	public void testGoovyOpenLigaImporterSingleMatch() {

		IDataImporter importer = new OpenLigaDataImporter();
		Map<String, String> matchDetails = importer.getMatchDetails(match);
		for (String key : matchDetails.keySet()) {
			String value = matchDetails.get(key);
			System.out.println("key: " + key + "; value:" + value);
		}
		assertTrue(matchDetails.size() > 3);
		assertNotNull(matchDetails.get("groupID"));

	}

	@Test
	public void testGoovyOpenLigaImporterMatchDay() {
		MatchDay matchDay = new MatchDay();
		matchDay.setMatchDayId(28);

		League league = new League();
		league.setType(LeagueType.FIRST_BL);
		league.setOLShortcut("bl1");

		IDataImporter importer = new OpenLigaDataImporter();
		List<Match> matchesOfMatchDay = importer.getMatchesOfMatchDay(matchDay, league);
		for (Match match : matchesOfMatchDay) {
			System.out.println(match.getOLId());
		}
		assertEquals(9, matchesOfMatchDay.size());
		assertTrue(matchesOfMatchDay.get(0).getOLId() > 0);
		// assertTrue(matchesOfMatchDay.get(0).getTeam1OLId() > 0);
		assertTrue(matchesOfMatchDay.get(0).getId() == 0);

	}

	@Test
	public void testGoovyOpenLigaDirect()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		ClassLoader parent = CommTester.class.getClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);

		Class<?> gImporter = loader.loadClass("com.voigt.hwd.server.comm.GroovyOLImporter");

		GroovyObject goImporter = (GroovyObject) gImporter.newInstance();
		IOLImporter dataImporter = (IOLImporter) goImporter;

		Map<String, String> matchDetails = dataImporter.getOLDetails(match.getId());
		Set<String> keySet = matchDetails.keySet();

		for (String key : keySet) {
			String value = matchDetails.get(key);
			System.out.println("key: " + key + "; value:" + value);
		}

	}

}
