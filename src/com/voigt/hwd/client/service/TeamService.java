package com.voigt.hwd.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Team;

public interface TeamService extends IDataSourceService<Team> {

	public static final String SERVICE_URI = "/TeamService";

	public static class Util {

		public static TeamServiceAsync getInstance() {

			TeamServiceAsync instance = (TeamServiceAsync) GWT.create(TeamService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}

	public List<Team> getTeams(League league);
}
