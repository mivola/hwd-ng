/**
 * 
 */
package com.voigt.hwd.server.service.impl;

import java.io.Serializable;
import java.util.List;

import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;
import com.voigt.hwd.server.dao.IGenericDao;
import com.voigt.hwd.server.service.IMatchFixtureService;

/**
 * Implementation of the identification service
 * 
 * @author bruno.marchesson
 * 
 */
public class MatchFixtureService extends GenericServiceImpl<Match, Serializable> implements IMatchFixtureService {

	private IGenericDao<Match, Serializable> matchDao;
	private IGenericDao<MatchDay, Serializable> matchDayDao;

	public IGenericDao<Match, Serializable> getMatchDao() {
		return matchDao;
	}

	public void setMatchDao(IGenericDao<Match, Serializable> matchDao) {
		this.matchDao = matchDao;
		this.dao = matchDao;
	}

	public IGenericDao<MatchDay, Serializable> getMatchDayDao() {
		return matchDayDao;
	}

	public void setMatchDayDao(IGenericDao<MatchDay, Serializable> matchDayDao) {
		this.matchDayDao = matchDayDao;
	}

	@Override
	public MatchDay getMatchDay(MatchDay matchDay) {
		MatchDay m2 = matchDayDao.getById(matchDay.getId());
		// load associations to avoid LazyLoadingExceptions
		m2.getMatches().size();
		m2.getSeason().getLeagues().size();
		return m2;
	}

	@Override
	public MatchDay getMatchDayByOLId(int matchDayOLId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MatchDay> getMatchDays(Season season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MatchDay> getMatchDays() {
		// TODO Auto-generated method stub
		return null;
	}

}
