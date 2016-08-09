package com.voigt.hwd.client.ui;

import java.util.List;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.voigt.hwd.client.service.DefaultCallback;
import com.voigt.hwd.client.service.MatchService;
import com.voigt.hwd.client.service.MatchServiceAsync;
import com.voigt.hwd.domain.Season;

public class SeasonListGridComboBox extends ComboBoxItem {

    public SeasonListGridComboBox() {
	super("Saison2");

	MatchServiceAsync service = MatchService.Util.getInstance();
	service.getSeasons(new DefaultCallback<List<Season>>() {
	    public void onSuccess(List<Season> seasons) {
		refreshValueMap(seasons);
	    }
	});

    }

    protected void refreshValueMap(List<Season> seasons) {

	ListGridField itemIdField = new ListGridField("itemID");
	ListGridField itemNameField = new ListGridField("itemName");
	ListGridField unitCostField = new ListGridField("unitCost");

	this.setOptionDataSource(new SeasonDataSource(seasons));

	this.setDisplayField("itemName");
	this.setValueField("SKU");

	this.setPickListFields(itemIdField, itemNameField, unitCostField);
	this.setPickListHeaderHeight(0);
	this.setPickListWidth(300);

    }

    private static class SeasonDataSource extends DataSource {

	public SeasonDataSource(List<Season> seasons) {

	    setClientOnly(true);

	    for (Season season : seasons) {

		ListGridRecord seasonRecord = new ListGridRecord();
		seasonRecord.setAttribute("itemID", season.getId());
		seasonRecord.setAttribute("itemName", season.getTitle());
		seasonRecord.setAttribute("unitCost", season.getYear());

		addData(seasonRecord);
	    }

	    DataSourceIntegerField pkField = new DataSourceIntegerField("itemID");
	    pkField.setHidden(true);
	    pkField.setPrimaryKey(true);

	    DataSourceTextField itemNameField = new DataSourceTextField("itemName", "Item Name", 128, true);

	    DataSourceIntegerField unitCostField = new DataSourceIntegerField("unitCost", "Unit Cost", 5);

	    setFields(pkField, itemNameField, unitCostField);

	}
    }

}
