package com.voigt.hwd.client.grid.history;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.history.HwdHistoryLocalDS;
import com.voigt.hwd.client.history.HwdItemRecord;

public class HistoryAllTimeTable extends AbstractBasePanel {

	private static final String DESCRIPTION = "<p>fast 10 Jahre HWD ...</p>";

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			HistoryAllTimeTable panel = new HistoryAllTimeTable();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}
	}

	@Override
	public Canvas getViewPanel() {

		VLayout layout = new VLayout(15);

		Label label = new Label();
		label.setHeight(10);
		label.setWidth100();
		// label.setWidth(250);
		label.setContents("HWD - Ewige Tabelle");
		layout.addMember(label);

		final DataSource dataSource = HwdHistoryLocalDS.getInstance();

		final ListGrid listGrid = new ListGrid();
		listGrid.setWidth100();
		listGrid.setHeight(255);
		listGrid.setDataSource(dataSource);
		listGrid.setAutoFetchData(true);

		// http://forums.smartclient.com/showthread.php?p=14440#post14440
		// http://abhijeetmaharana.com/blog/2008/12/12/override-rendering-of-column-from-smartgwt-data-source/
		ListGridField joinedField = new ListGridField(HwdItemRecord.JOINED_FIELD);

		CellFormatter dateFormatter = new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {

				String result = value.toString();
				if (value instanceof Date) {
					Date date = (Date) value;
					DateTimeFormat outputFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
					result = outputFormat.format(date);
				}

				return result;
			}
		};

		joinedField.setCellFormatter(dateFormatter);

		// http://abhijeetmaharana.com/blog/2008/12/12/override-rendering-of-column-from-smartgwt-data-source/
		CellFormatter floatFormatter = new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {

				String result = value.toString();
				NumberFormat format = NumberFormat.getFormat("#.00");

				if (value instanceof Float) {
					Float floatValue = (Float) value;
					result = format.format(floatValue);
				} else if (value instanceof Integer) {
					Integer intValue = (Integer) value;
					result = format.format(intValue);
				}

				result = result.replace(".", ",");

				return result;
			}
		};

		ListGridField pointsPerSeasonField = new ListGridField(HwdItemRecord.POINTS_PER_SEASON_FIELD);
		ListGridField tippPointsPerSeasonField = new ListGridField(HwdItemRecord.TIPP_POINTS_PER_SEASON_FIELD);
		pointsPerSeasonField.setCellFormatter(floatFormatter);
		tippPointsPerSeasonField.setCellFormatter(floatFormatter);

		listGrid.setFields(tippPointsPerSeasonField, pointsPerSeasonField, joinedField);
		listGrid.setUseAllDataSourceFields(true);

		listGrid.setLoadingDataMessage("Lade die ganzen dollen Daten ....");

		layout.addMember(listGrid);

		return layout;
	}

	@SuppressWarnings("unused")
	private static native JavaScriptObject getJson(String varName)
	/*-{ return $wnd[varName]; }-*/
	;
}