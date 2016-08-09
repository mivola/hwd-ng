package com.voigt.hwd.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Team;

public interface TeamServiceAsync extends IDataSourceServiceAsync<Team> {

	public void getTeams(League league, AsyncCallback<List<Team>> callback);
}
