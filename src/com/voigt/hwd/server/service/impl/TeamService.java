/**
 * 
 */
package com.voigt.hwd.server.service.impl;

import java.io.Serializable;
import java.util.List;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Team;
import com.voigt.hwd.server.dao.ITeamDao;
import com.voigt.hwd.server.service.ITeamService;

/**
 * Implementation of the identification service
 * 
 * @author bruno.marchesson
 * 
 */
public class TeamService extends GenericServiceImpl<Team, Serializable> implements ITeamService {

	private ITeamDao teamDao;

	public ITeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(ITeamDao teamDao) {
		this.teamDao = teamDao;
		this.dao = teamDao;
	}

	public List<Team> getTeams(League league) {

		List<Team> teams = teamDao.getTeams(league);
		return teams;

	}

	@Override
	public Team getByOLId(int oLId) {
		return teamDao.getByOLId(oLId);
	}

}
