package com.voigt.hwd.server.remote;

import java.io.Serializable;
import java.util.List;

import com.voigt.hwd.client.service.TeamService;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Team;
import com.voigt.hwd.server.service.IGenericService;
import com.voigt.hwd.server.service.ITeamService;

public class TeamServiceImpl extends AbstractGwtDataSourceRemoteService<Team> implements TeamService {

	private static final long serialVersionUID = -1198445778134437752L;

	private ITeamService teamService;

	public TeamServiceImpl() {
		super();
		teamService = getContext().getTeamService();
	}

	public ITeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(ITeamService teamService) {
		this.teamService = teamService;
	}

	public List<Team> getTeams(League league) {
		return teamService.getTeams(league);
	}

	public List<Team> getAllEntries() {
		return teamService.getAll();
	}

	public Team getOneEntry(int id) {
		return teamService.getById(id);
	}

	public boolean saveOneEntry(Team team) {
		teamService.saveOrUpdate(team);
		return true;
	}

	public boolean deleteOneEntry(int id) {
		teamService.deleteById(id);
		return true;
	}

	@Override
	protected IGenericService<Team, Serializable> getService() {
		return teamService;
	}

}
