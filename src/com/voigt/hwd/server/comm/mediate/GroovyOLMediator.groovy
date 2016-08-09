/**
 * 
 */
package com.voigt.hwd.server.comm.mediate

import com.voigt.hwd.domain.Match
import com.voigt.hwd.server.comm.mediate.IMediator
import javax.xml.datatype.XMLGregorianCalendarimport java.sql.Timestamp
import java.util.Date
/**
 * @author mvoigt
 *
 */
public class GroovyOLMediator implements IMediator{

	
	
	/* (non-Javadoc)
	 * @see com.voigt.hwd.server.comm.mediate.IMediator#createMatch(java.lang.Object)
	 */
	public Match createMatch(Object oLMatchdata){

	        Match match = new Match();
	        match.setOLId(oLMatchdata.matchID);
	        match.setTeam1OLId(oLMatchdata.idTeam1);
	        match.setTeam2OLId(oLMatchdata.idTeam2);
	        match.setResult1(oLMatchdata.pointsTeam1);
	        match.setResult2(oLMatchdata.pointsTeam2);
	        
	        XMLGregorianCalendar gregorianCalendar = oLMatchdata.matchDateTime;
	        Date d = gregorianCalendar.toGregorianCalendar().getTime();
			Timestamp t = (d instanceof Timestamp) ? (Timestamp) d : new Timestamp(d.getTime());
			match.setKickOffDate(t);
			
	        
	        return match;
	    
	}
	
}
