package com.voigt.hwd.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;

public interface MatchService extends RemoteService {

	public static final String SERVICE_URI = "/MatchService";

	public static class Util {

		public static MatchServiceAsync getInstance() {

			MatchServiceAsync instance = (MatchServiceAsync) GWT.create(MatchService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}

	public List<Match> getMatches(MatchDay matchDay, League league);

	public List<Season> getSeasons();

	public boolean saveMatches(List<Match> matches);

}
