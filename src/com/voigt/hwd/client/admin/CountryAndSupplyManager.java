package com.voigt.hwd.client.admin;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.AbstractBasePanel;
import com.voigt.hwd.client.PanelFactory;
import com.voigt.hwd.client.admin.ds.ReadOnlyDataSourceManager;

public class CountryAndSupplyManager extends AbstractBasePanel {

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			CountryAndSupplyManager panel = new CountryAndSupplyManager();
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
		final DataSource countryDS = CountryXmlDS.getInstance();
		final DataSource supplyItemDS = ItemSupplyXmlDS.getInstance();

		final CompoundEditor cEditor = new CompoundEditor(new ReadOnlyDataSourceManager(countryDS), false, true);

		SelectItem dsSelect = new SelectItem();
		dsSelect.setName("datasource");
		dsSelect.setShowTitle(false);
		dsSelect.setValueMap("country", "supply");
		dsSelect.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				String ds = (String) event.getValue();
				if (ds.equalsIgnoreCase("country")) {
					cEditor.setDatasource(countryDS);
				} else {
					cEditor.setDatasource(supplyItemDS);
				}
			}
		});
		DynamicForm form = new DynamicForm();
		form.setValue("datasource", "Select a DataSource");
		form.setItems(dsSelect);

		Window window = new Window();
		window.setTitle("Introduction");

		Label label = new Label();
		label.setWidth100();
		label.setHeight100();
		label.setPadding(5);
		label.setValign(VerticalAlignment.TOP);

		window.setHeight(130);
		window.setWidth100();
		window.addItem(label);

		VLayout layout = new VLayout(15);
		layout.setWidth100();
		layout.setHeight("80%");

		layout.addMember(window);
		layout.addMember(form);
		layout.addMember(cEditor);

		return layout;
	}

}
