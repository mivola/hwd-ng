package com.voigt.hwd.client.admin.ds;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.voigt.hwd.client.service.TeamService;
import com.voigt.hwd.client.service.TeamServiceAsync;
import com.voigt.hwd.domain.Team;

public class TeamDataSource extends DataSource {
	protected static final String NAME = "name";
	protected static final String ABBREVIATION = "abbreviaton";
	protected static final String OL_ID = "OLId";

	public TeamDataSource() {
		// setID("teamDataSource");

		DataSourceTextField id = new DataSourceTextField(IDataSourceManager.ID_FIELD, "ID");
		DataSourceTextField name = new DataSourceTextField(NAME, "Name");
		DataSourceTextField abbreviation = new DataSourceTextField(ABBREVIATION, "Abkürzung");
		DataSourceTextField openLigaId = new DataSourceTextField(OL_ID, "OpenLiga-ID");
		id.setHidden(true);
		id.setPrimaryKey(true);

		setFields(id, name, abbreviation, openLigaId);
		setClientOnly(true);

		setRecords();

	}

	private void setRecords() {
		TeamServiceAsync service = TeamService.Util.getInstance();
		service.getAllEntries(new AsyncCallback<List<Team>>() {
			public void onFailure(Throwable caught) {
				Log.debug("There was an error fetching data from the server");
			}

			public void onSuccess(List<Team> teams) {

				// load all users into the grid
				for (Team team : teams) {

					ListGridRecord record = new ListGridRecord();
					record.setAttribute(IDataSourceManager.ID_FIELD, team.getId());
					record.setAttribute(NAME, team.getName());
					record.setAttribute(ABBREVIATION, team.getAbbreviation());
					record.setAttribute(OL_ID, team.getOLId());

					addData(record);
				}

			}

		});
	}
}
