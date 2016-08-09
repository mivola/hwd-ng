package com.voigt.hwd.client.admin.ds;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.voigt.hwd.client.service.TeamService;
import com.voigt.hwd.client.service.TeamServiceAsync;
import com.voigt.hwd.domain.Team;

public class TeamDataSourceManager extends AbstractDataSourceManager<com.voigt.hwd.domain.Team> {

    private final TeamServiceAsync teamService;

    public TeamDataSourceManager() {

	super(new TeamDataSource());

	// set the service that will be used for loading and saving objects
	teamService = TeamService.Util.getInstance();
    }

    public boolean updateExisting(int pk, final FormItem[] fields) {

	teamService.getOneEntry(pk, new AsyncCallback<Team>() {

	    public void onFailure(Throwable caught) {
		Log.debug("FAIL");

	    }

	    public void onSuccess(Team team) {

		team = setTeamDetails(fields, team);

		Log.debug("changed team: " + team);

		teamService.saveOneEntry(team, new AsyncCallback<Object>() {

		    public void onFailure(Throwable caught) {
			Log.debug("FAIL");

		    }

		    public void onSuccess(Object result) {
			// TODO Auto-generated method stub
			Log.debug("YESSSS: saved");

		    }

		});
	    }

	});

	return false;
    }

    private Team setTeamDetails(final FormItem[] fields, Team team) {
	String name = getStringValue(fields, TeamDataSource.NAME);
	if (name != null) {
	    team.setName(name);
	}
	String abbreviation = getStringValue(fields, TeamDataSource.ABBREVIATION);
	if (abbreviation != null) {
	    team.setAbbreviation(abbreviation);
	}
	int oLId = getIntValue(fields, TeamDataSource.NAME);
	if (oLId > -1) {
	    team.setOLId(oLId);
	}
	return team;
    }

    public boolean saveNew(FormItem[] fields) {
	Team team = new Team();
	team = setTeamDetails(fields, team);

	teamService.saveOneEntry(team, new AsyncCallback<Team>() {

	    public void onFailure(Throwable caught) {
		Log.debug("FAIL");

	    }

	    public void onSuccess(Team result) {
		Log.debug("YESSSS: saved");
	    }

	});
	return true;
    }

    public boolean delete(int pk) {

	teamService.deleteOneEntry(pk, new AsyncCallback() {

	    public void onFailure(Throwable caught) {
		Log.debug("FAIL");

	    }

	    public void onSuccess(Object result) {
		Log.debug("YESSSS: deleted: " + result);
	    }

	});

	return true;

    }

}
