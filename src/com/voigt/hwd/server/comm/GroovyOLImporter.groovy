package com.voigt.hwd.server.comm

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.voigt.hwd.server.comm.IOLImporter

import com.voigt.hwd.server.comm.mediate.IMediatorimport com.voigt.hwd.server.comm.mediate.GroovyOLMediator
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;

import groovyx.net.ws.WSClient


/**
 * @author mvoigt
 *
 */
public class GroovyOLImporter implements IOLImporter {

    static def openLigaDB = new WSClient("http://www.openligadb.de/Webservices/Sportsdata.asmx?WSDL", GroovyOLImporter.class.classLoader)

    static{
		println("trying to create GroovyOLImporter")
		openLigaDB.initialize()
		println("GroovyOLImporter created: " + openLigaDB)
    }
    
    public GroovyOLImporter(){
    }
    
    public Map<String, String> getOLDetails(int oLId){
		def matchData = openLigaDB.GetMatchByMatchID(oLId)
		println("matchData for id: [" + oLId + "]: " + matchData)
		
		Map<String, String> values = new HashMap<String, String>()
		
		values.putAt("groupID", matchData.groupID+"")
		values.putAt("groupName", matchData.groupName)
		values.putAt("leagueName", matchData.leagueName)
		values.putAt("nameTeam1", matchData.nameTeam1)
		values.putAt("nameTeam2", matchData.nameTeam2)
	
		return values;
    }
    
    public List<Match> getMatchesOfMatchDay(int season, String leagueShort, int oLMatchDayId){

//		def matchDay = openLigaDB.GetMatchdataByGroupLeagueSaison(24,"bl1",2008)

		List<Match> matches = new ArrayList<Match>();

		def oLMatchday = openLigaDB.GetMatchdataByGroupLeagueSaison(oLMatchDayId, leagueShort, season)
		for (oLMatchdata in oLMatchday.matchdata){
		        println "id: ${oLMatchdata.matchID}; ${oLMatchdata.nameTeam1} (${oLMatchdata.idTeam1}) - ${oLMatchdata.nameTeam2} (${oLMatchdata.idTeam1}): ${oLMatchdata.pointsTeam1} - ${oLMatchdata.pointsTeam2}"
		        IMediator mediator = new GroovyOLMediator();
		        Match match = mediator.createMatch(oLMatchdata);
		        matches.add(match);
/*		        
		        println "INSERT INTO TEAM VALUES(id,'${oLMatchdata.nameTeam1}','${oLMatchdata.nameTeam1}',${oLMatchdata.idTeam1})"
		        println "INSERT INTO TEAM VALUES(id,'${oLMatchdata.nameTeam2}','${oLMatchdata.nameTeam2}',${oLMatchdata.idTeam2})"
*/		        
		        
		}		

		return matches;
	
    }
	
}
