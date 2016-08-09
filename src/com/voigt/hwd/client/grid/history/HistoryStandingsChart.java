package com.voigt.hwd.client.grid.history;

import static com.googlecode.charts4j.Color.BLUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;

public class HistoryStandingsChart extends AbstractBasePanel {

	private static final String HUENI = "Hüni";
	private static final String MICHA = "Micha";
	private static final String STEV = "Stev";
	private static final String NICO = "Nico";
	private static final String MARKUS = "Markus";
	private static final String TOBI = "Tobi";
	private static final String MARCEL = "Marcel";
	private static final String JAN = "Jan";
	private static final String PATZI = "Patzi";
	private static final String ROSSI = "Rossi";
	private static final String SVEN = "Sven";

	private final static int CHART_WIDTH = 660;
	private final static int CHART_HEIGHT = 440;

	private static final String DESCRIPTION = "<p>Die Platzierungen in den einzelnen Jahren im Überblick</p>";

	private static final Map<String, Plot> userPlots = new HashMap<String, Plot>();
	private static final Map<String, Boolean> selectedUsers = new HashMap<String, Boolean>();

	static {
		Integer[] hueni = new Integer[] { 1, 3, 3, 4, 2, 4, 4, 8, 9 };
		Integer[] micha = new Integer[] { 2, 2, 2, 3, 3, 1, 3, 7, 3 };
		Integer[] stev = new Integer[] { 3, 1, 4, 5, 1, 2, 2, 2, 1 };
		Integer[] nico = new Integer[] { 0, 0, 1, 1, 4, 3, 1, 1, 2 };
		Integer[] markus = new Integer[] { 0, 0, 5, 6, 6, 6, 5, 4, 8 };
		Integer[] tobi = new Integer[] { 0, 0, 0, 2, 5, 5, 7, 5, 4 };
		Integer[] marcel = new Integer[] { 0, 0, 0, 0, 0, 0, 6, 9, 10 };
		Integer[] jan = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 3, 7 };
		Integer[] patzi = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 6, 11 };
		Integer[] sven = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 5 };
		Integer[] rossi = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 6 };

		List<Integer> hueniList = getLineDataAsList(hueni);
		List<Integer> michaList = getLineDataAsList(micha);
		List<Integer> stevList = getLineDataAsList(stev);
		List<Integer> nicoList = getLineDataAsList(nico);
		List<Integer> markusList = getLineDataAsList(markus);
		List<Integer> tobiList = getLineDataAsList(tobi);
		List<Integer> marcelList = getLineDataAsList(marcel);
		List<Integer> janList = getLineDataAsList(jan);
		List<Integer> patziList = getLineDataAsList(patzi);
		List<Integer> rossiList = getLineDataAsList(rossi);
		List<Integer> svenList = getLineDataAsList(sven);

		Plot hueniPlot = Plots.newPlot(Data.newData(hueniList), Color.BLUE);
		hueniPlot.addShapeMarkers(Shape.DIAMOND, BLUE, 8);
		userPlots.put(HUENI, hueniPlot);

		Plot michaPlot = Plots.newPlot(Data.newData(michaList), Color.BLUEVIOLET);
		michaPlot.addShapeMarkers(Shape.CROSS, BLUE, 8);
		userPlots.put(MICHA, michaPlot);

		Plot stevPlot = Plots.newPlot(Data.newData(stevList), Color.CORAL);
		stevPlot.addShapeMarkers(Shape.CIRCLE, BLUE, 8);
		userPlots.put(STEV, stevPlot);

		Plot nicoPlot = Plots.newPlot(Data.newData(nicoList), Color.AQUA);
		nicoPlot.addShapeMarkers(Shape.SQUARE, BLUE, 6);
		userPlots.put(NICO, nicoPlot);

		Plot markusPlot = Plots.newPlot(Data.newData(markusList), Color.BROWN);
		markusPlot.addShapeMarkers(Shape.SQUARE, BLUE, 6);
		userPlots.put(MARKUS, markusPlot);

		Plot tobiPlot = Plots.newPlot(Data.newData(tobiList), Color.DODGERBLUE);
		tobiPlot.addShapeMarkers(Shape.CIRCLE, BLUE, 8);
		userPlots.put(TOBI, tobiPlot);

		Plot marcelPlot = Plots.newPlot(Data.newData(marcelList), Color.SPRINGGREEN);
		marcelPlot.addShapeMarkers(Shape.CROSS, BLUE, 8);
		userPlots.put(MARCEL, marcelPlot);

		Plot janPlot = Plots.newPlot(Data.newData(janList), Color.RED);
		janPlot.addShapeMarkers(Shape.SQUARE, BLUE, 6);
		userPlots.put(JAN, janPlot);

		Plot patziPlot = Plots.newPlot(Data.newData(patziList), Color.TAN);
		patziPlot.addShapeMarkers(Shape.DIAMOND, BLUE, 8);
		userPlots.put(PATZI, patziPlot);

		Plot rossiPlot = Plots.newPlot(Data.newData(rossiList), Color.DARKSEAGREEN);
		rossiPlot.addShapeMarkers(Shape.CROSS, Color.DARKSEAGREEN, 8);
		userPlots.put(ROSSI, rossiPlot);

		Plot svenPlot = Plots.newPlot(Data.newData(svenList), Color.FUCHSIA);
		svenPlot.addShapeMarkers(Shape.CIRCLE, Color.FUCHSIA, 8);
		userPlots.put(SVEN, svenPlot);

		selectedUsers.put(HUENI, true);
		selectedUsers.put(MICHA, true);
		selectedUsers.put(STEV, true);
		selectedUsers.put(NICO, true);
		selectedUsers.put(MARKUS, true);
		selectedUsers.put(TOBI, true);
		selectedUsers.put(MARCEL, true);
		selectedUsers.put(JAN, true);
		selectedUsers.put(PATZI, true);
		selectedUsers.put(ROSSI, true);
		selectedUsers.put(SVEN, true);

	}

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			HistoryStandingsChart panel = new HistoryStandingsChart();
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
		label.setContents("HWD - 9-Jahres-Platzierungen");
		layout.addMember(label);

		int height2 = this.getPageRect().getHeight();
		int width2 = this.getPageRect().getWidth();

		final Img img = new Img(getChartImageUrl());
		img.setSize(CHART_WIDTH + "px", CHART_HEIGHT + "px");
		img.setOverflow(Overflow.AUTO);

		VLayout usersVLayout = new VLayout();

		ChangedHandler changedHandler = new CheckboxItemChangedHandler(img);

		CheckboxItem cbHueni = new CheckboxItem(HUENI);
		cbHueni.setDefaultValue(true);
		cbHueni.setTextBoxStyle("hueniCheckboxTextbox hwdCheckboxTextbox");
		cbHueni.addChangedHandler(changedHandler);
		CheckboxItem cbMicha = new CheckboxItem(MICHA);
		cbMicha.setDefaultValue(true);
		cbMicha.setTextBoxStyle("michaChecboxTextbox hwdCheckboxTextbox");
		cbMicha.addChangedHandler(changedHandler);
		CheckboxItem cbStev = new CheckboxItem(STEV);
		cbStev.setDefaultValue(true);
		cbStev.setTextBoxStyle("stevChecboxTextbox hwdCheckboxTextbox");
		cbStev.addChangedHandler(changedHandler);
		CheckboxItem cbNico = new CheckboxItem(NICO);
		cbNico.setDefaultValue(true);
		cbNico.setTextBoxStyle("nicoChecboxTextbox hwdCheckboxTextbox");
		cbNico.addChangedHandler(changedHandler);
		CheckboxItem cbMarkus = new CheckboxItem(MARKUS);
		cbMarkus.setDefaultValue(true);
		cbMarkus.setTextBoxStyle("markusChecboxTextbox hwdCheckboxTextbox");
		cbMarkus.addChangedHandler(changedHandler);
		CheckboxItem cbTobi = new CheckboxItem(TOBI);
		cbTobi.setDefaultValue(true);
		cbTobi.setTextBoxStyle("tobiChecboxTextbox hwdCheckboxTextbox");
		cbTobi.addChangedHandler(changedHandler);
		CheckboxItem cbMarcel = new CheckboxItem(MARCEL);
		cbMarcel.setDefaultValue(true);
		cbMarcel.setTextBoxStyle("marcelChecboxTextbox hwdCheckboxTextbox");
		cbMarcel.addChangedHandler(changedHandler);
		CheckboxItem cbJan = new CheckboxItem(JAN);
		cbJan.setDefaultValue(true);
		cbJan.setTextBoxStyle("janChecboxTextbox hwdCheckboxTextbox");
		cbJan.addChangedHandler(changedHandler);
		CheckboxItem cbPatzi = new CheckboxItem(PATZI);
		cbPatzi.setDefaultValue(true);
		cbPatzi.setTextBoxStyle("patziChecboxTextbox hwdCheckboxTextbox");
		cbPatzi.addChangedHandler(changedHandler);
		CheckboxItem cbRossi = new CheckboxItem(ROSSI);
		cbRossi.setDefaultValue(true);
		cbRossi.setTextBoxStyle("rossiChecboxTextbox hwdCheckboxTextbox");
		cbRossi.addChangedHandler(changedHandler);
		CheckboxItem cbSven = new CheckboxItem(SVEN);
		cbSven.setDefaultValue(true);
		cbSven.setTextBoxStyle("svenChecboxTextbox hwdCheckboxTextbox");
		cbSven.addChangedHandler(changedHandler);

		DynamicForm form = new DynamicForm();
		form.setFields(cbHueni, cbMicha, cbStev, cbNico, cbMarkus, cbTobi, cbMarcel, cbJan, cbPatzi, cbRossi, cbSven);

		usersVLayout.addChild(form);

		HLayout hLayout = new HLayout();
		hLayout.addMember(img);
		hLayout.addMember(usersVLayout);

		layout.addMember(hLayout);

		return layout;
	}

	private class CheckboxItemChangedHandler implements ChangedHandler {

		private final Img img;

		public CheckboxItemChangedHandler(Img img) {
			this.img = img;
		}

		public void onChanged(ChangedEvent event) {

			final Window winModal = new Window();
			winModal.setShowModalMask(true);
			winModal.setWidth(360);
			winModal.setHeight(150);
			winModal.setTitle("Berechne ...");
			winModal.setShowMinimizeButton(false);
			winModal.setShowCloseButton(false);
			winModal.setIsModal(true);
			winModal.centerInPage();

			HLayout layout = new HLayout();
			layout.setAlign(Alignment.CENTER);
			layout.setAlign(VerticalAlignment.CENTER);
			Label label = new Label("Berechne Diagram ... ");
			label.setWidth100();
			label.setAlign(Alignment.CENTER);
			layout.addChild(label);
			winModal.addItem(layout);

			winModal.show();

			CheckboxItem checkboxItem = (CheckboxItem) event.getSource();
			String name = checkboxItem.getName();
			Boolean value = (Boolean) event.getValue();
			selectedUsers.put(name, value);
			img.setSrc(getChartImageUrl());
			img.resetSrc();

			winModal.destroy();
		}

	}

	public String getChartImageUrl() {

		LineChart lineChart = GCharts.newLineChart(getSelectedPlots());
		lineChart.setSize(CHART_WIDTH, CHART_HEIGHT);

		// lineChart.addHorizontalRangeMarker(33.3, 66.6, LIGHTBLUE);
		lineChart.setGrid(100d / 8d, 100d / 10d, 3, 3);
		lineChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("1999/2000", "2000", "2001", "2002", "2003", "2004",
				"2005", "2006", "2007/2008"));
		AxisLabels yAxisLabels = AxisLabelsFactory.newAxisLabels("", "10.", "9. ", "8. ", "7. ", "6. ", "5. ", "4. ",
				"3. ", "2. ", "1. ");
		lineChart.addRightAxisLabels(yAxisLabels);
		lineChart.addYAxisLabels(yAxisLabels);

		String lineChartUrl = lineChart.toURLString();
		Log.debug(lineChartUrl);
		return lineChartUrl;
	}

	private List<Plot> getSelectedPlots() {

		List<Plot> plots = new ArrayList<Plot>();
		Set<String> keySet = selectedUsers.keySet();
		for (String user : keySet) {
			Boolean selected = selectedUsers.get(user);
			if (selected) {
				Plot plot = userPlots.get(user);
				plots.add(plot);
			}
		}
		return plots;

	}

	private static List<Integer> getLineDataAsList(Integer[] array) {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer i : array) {
			i = 110 - (i * 10);
			list.add(i);
		}
		return list;
	}

	@SuppressWarnings("unused")
	private static native JavaScriptObject getJson(String varName)
	/*-{ return $wnd[varName]; }-*/
	;
}