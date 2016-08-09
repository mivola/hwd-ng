package com.voigt.hwd.server.dao;

import java.io.Serializable;
import java.util.List;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Team;

public interface ITeamDao extends IGenericDao<Team, Serializable> {

	public List<Team> getTeams(League league);

	public Team getByOLId(int id);

}