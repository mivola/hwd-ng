package com.voigt.hwd.client.service;

import java.util.List;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MatchServiceAsync {

	public void getMatches(MatchDay matchDay, League league, AsyncCallback callback);

	public void getSeasons(AsyncCallback callback);

	public void saveMatches(List<Match> matches, AsyncCallback callback);

}
