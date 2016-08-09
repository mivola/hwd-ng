package com.voigt.hwd.client.admin;

import java.util.LinkedHashMap;
import java.util.Map;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.FilterBuilder;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.admin.ds.UserGwtRpcDataSource;

public class NewUserManager extends AbstractBasePanel {

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			NewUserManager panel = new NewUserManager();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return "";
		}
	}

	@Override
	public Canvas getViewPanel() {

		DataSource userDataSource = new UserGwtRpcDataSource();

		final ListGrid listGrid = new ListGrid();
		listGrid.setAutoFetchData(true);
		listGrid.setShowFilterEditor(true);
		listGrid.setShowAllRecords(false);
		listGrid.setAlternateRecordStyles(true);
		listGrid.setFilterOnKeypress(false);
		listGrid.setDataPageSize(60);
		listGrid.setWidth100();
		listGrid.setHeight("*");
		listGrid.setCanEdit(true);
		listGrid.setEditEvent(ListGridEditEvent.CLICK);
		listGrid.setEditByCell(true);

		final FilterBuilder filterBuilder = new FilterBuilder();
		// filterBuilder.setTopOperatorAppearance(TopOperatorAppearance.RADIO);

		ListGridField roleField = new ListGridField("roleName", "Rollen", 100);

		SelectItem countrySelectItem = new SelectItem();
		countrySelectItem.setName("roleName");
		countrySelectItem.setPickListWidth(200);
		countrySelectItem.setMultiple(true);

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("India", "India");
		map.put("IndiaB", "IndiaB");
		map.put("IndiaC", "IndiaC");
		roleField.setValueMap(map);

		roleField.setEditorType(countrySelectItem);

		IButton filterButton = new IButton("Filter");
		filterButton.setOverflow(Overflow.CLIP_V);

		filterButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				listGrid.invalidateCache();
				listGrid.fetchData(filterBuilder.getCriteria());
			}
		});

		filterBuilder.setDataSource(userDataSource);
		listGrid.setDataSource(userDataSource);

		// listGrid.setFields(roleField);

		// VStack vStack = new VStack(10);
		VLayout vStack = new VLayout();
		vStack.setWidth100();
		vStack.setHeight100();

		vStack.addMember(filterBuilder);
		vStack.addMember(filterButton);
		vStack.addMember(listGrid);

		return vStack;
	}

}
