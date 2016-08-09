package com.voigt.hwd.client.admin;

import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.voigt.hwd.client.admin.ds.IDataSourceManager;

/**
 * shows a grid and a dynamic form; both show the fields of the datasource
 * 
 * @author mvoigt
 * 
 */
class CompoundEditor extends HLayout {

    private final IDataSourceManager dataSourceManager;

    private DataSource dataSource;
    private DynamicForm form;
    private ListGrid grid;
    private IButton saveButton;
    private IButton deleteButton;
    /* should it be possible to add new entries */
    private final boolean addNew;
    /* should it be possible to delete entries */
    private final boolean deleteExisting;

    protected CompoundEditor(IDataSourceManager dataSourceManager, boolean addNew, boolean deleteExisting) {
	this.dataSourceManager = dataSourceManager;
	this.dataSource = dataSourceManager.getDataSource();
	this.addNew = addNew;
	this.deleteExisting = deleteExisting;
    }

    @Override
    protected void onInit() {
	super.onInit();
	this.form = new DynamicForm();

	form.setDataSource(dataSource);

	saveButton = new IButton("Save");
	saveButton.setLayoutAlign(Alignment.CENTER);
	saveButton.addClickHandler(new ClickHandler() {

	    public void onClick(ClickEvent event) {

		FormItem[] fields = form.getFields();

		// Map<String, String> attributes = new HashMap<String,
		// String>();
		//
		// for (FormItem formItem : fields) {
		// String fieldName = formItem.getFieldName();
		// Log.debug(fieldName);
		// attributes.put(fieldName, formItem.getDisplayValue());
		// }
		// FormItem field = form.getField("firstName");
		// String value = field.getDisplayValue();

		// get id of the currently selected (ie modiefied) entry
		ListGridRecord record = grid.getSelectedRecord();

		boolean saved;
		if (record != null) {
		    // record selected => this is an existing entry
		    Integer pk = record.getAttributeAsInt(IDataSourceManager.ID_FIELD);
		    saved = dataSourceManager.updateExisting(pk, fields);
		} else {
		    // no record selected => this is a new entry
		    saved = dataSourceManager.saveNew(form.getFields());
		}

		if (saved) {
		    dataSource.fetchData();
		    // setDatasource(dataSource);
		    // form.saveData();
		    // // if (record == null) {
		    // // new entry created, so we need to reload the grid
		    // grid.setDataSource(dataSource);
		    // grid.markForRedraw();
		    // grid.invalidateCache();
		    // grid.fetchData();
		    // }
		} else {
		    // TODO: show an error message
		    Log.debug("error while saving data");
		}

	    }
	});

	IButton newButton = new IButton("Neuer Eintrag");
	newButton.setLayoutAlign(Alignment.CENTER);
	newButton.addClickHandler(new ClickHandler() {

	    public void onClick(ClickEvent event) {
		// grid.deselectRecords(grid.getSelection());
		grid.deselectAllRecords();
		form.clearValues();
		saveButton.enable();
	    }

	});

	deleteButton = new IButton("Eintrag löschen");
	deleteButton.setLayoutAlign(Alignment.CENTER);
	deleteButton.addClickHandler(new ClickHandler() {

	    public void onClick(ClickEvent event) {
		ListGridRecord record = grid.getSelectedRecord();

		if (record != null) {
		    // record selected => this record needs to be deleted
		    Integer pk = record.getAttributeAsInt(IDataSourceManager.ID_FIELD);
		    boolean saved = dataSourceManager.delete(pk);

		    if (saved) {
			// form.saveData();
			// form.clearValues();
			// grid.removeSelectedData();
			// grid.invalidateCache();
			// // grid.setData(null);
			// // grid.setDataSource(null);
			// grid.setDataSource(dataSource);
			// grid.fetchData();
			dataSource.fetchData();
			// setDatasource(dataSourceManager.getDataSource());
		    }

		}

	    }

	});

	VLayout editorLayout = new VLayout(5);
	editorLayout.addMember(form);
	editorLayout.addMember(saveButton);
	if (addNew) {
	    editorLayout.addMember(newButton);
	}
	if (deleteExisting) {
	    editorLayout.addMember(deleteButton);
	}
	editorLayout.setWidth(280);

	grid = new ListGrid();
	grid.setWidth(500);
	grid.setHeight(350);
	grid.setDataSource(dataSource);
	grid.setShowResizeBar(true);
	grid.setAutoFetchData(true);
	grid.setCanEdit(true);
	grid.setAlternateRecordStyles(true);
	grid.setEditEvent(ListGridEditEvent.CLICK);
	grid.setEditByCell(true);
	grid.setSelectionType(SelectionStyle.SINGLE);
	grid.addRecordClickHandler(new RecordClickHandler() {
	    public void onRecordClick(RecordClickEvent event) {
		form.clearErrors(true);
		form.editRecord(event.getRecord());
		saveButton.enable();
	    }

	});

	this.addMember(grid);
	this.addMember(editorLayout);
    }

    public DataSource getDatasource() {
	return dataSource;
    }

    public void setDatasource(DataSource dataSource) {
	if (this.dataSource == dataSource) {
	    Log.debug("setting dummy datasource");
	    this.dataSource = new DummyDataSource();
	    grid.setDataSource(dataSource);
	    form.setDataSource(dataSource);
	    grid.fetchData();
	    redraw();
	}
	this.dataSource = dataSource;
	grid.setDataSource(dataSource);
	form.setDataSource(dataSource);
	saveButton.disable();
	grid.fetchData();
    }

    private class DummyDataSource extends DataSource {

	public DummyDataSource() {

	    DataSourceTextField id = new DataSourceTextField(IDataSourceManager.ID_FIELD, "ID");
	    id.setHidden(true);
	    id.setPrimaryKey(true);

	    setFields(id);
	    setClientOnly(true);

	}
    }

}