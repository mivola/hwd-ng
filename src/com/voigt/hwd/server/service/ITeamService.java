package com.voigt.hwd.server.service;

import java.io.Serializable;
import java.util.List;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Team;

/**
 * Interface of the team service
 */
public interface ITeamService extends IGenericService<Team, Serializable> {

	public static final String NAME = "teamService";

	public List<Team> getTeams(League league);

	public Team getByOLId(int oLId);

}