package com.voigt.hwd.client.admin.ds;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.voigt.hwd.client.admin.ds.criteria.GWTCriterion;
import com.voigt.hwd.client.service.IDataSourceServiceAsync;
import com.voigt.hwd.domain.IDomainObject;

/**
 * Example <code>GwtRpcDataSource</code> implementation.
 * 
 * @author Aleksandras Novikovas
 * @author System Tier
 * @version 1.0
 */
public abstract class AbstractGenericGwtRpcDataSource<T extends IDomainObject> extends GwtRpcDataSource<T> {

    protected DataSourceField idField;

    public AbstractGenericGwtRpcDataSource() {
	super();

	idField = new DataSourceIntegerField("id", "ID");
	idField.setPrimaryKey(true);
	// AutoIncrement on server.
	idField.setRequired(false);

	// idField.setHidden(true);
	idField.setCanEdit(false);

	addField(idField);

    }

    protected abstract IDataSourceServiceAsync<T> getDataSourceServiceAsync();

    @Override
    protected void executeFetch(final String requestId, final DSRequest request, final DSResponse response) {
	// These can be used as parameters to create paging.
	// request.getStartRow ();
	// request.getEndRow ();
	// request.getSortBy ();
	IDataSourceServiceAsync<T> service = getDataSourceServiceAsync();

	GWTCriterion crit = null;
	try {
	    crit = createFetchCriteria(request.getData());
	} catch (Exception e) {
	    Log.debug(e.getMessage());
	}

	service.fetch(crit, request.getSortBy(), request.getStartRow(), request.getEndRow(),
		new AsyncCallback<FetchResult<T>>() {
		    public void onFailure(Throwable caught) {
			response.setStatus(RPCResponse.STATUS_FAILURE);
			processResponse(requestId, response);
		    }

		    public void onSuccess(FetchResult<T> result) {
			ListGridRecord[] list = new ListGridRecord[result.getFetchedList().size()];
			for (int i = 0; i < list.length; i++) {
			    ListGridRecord record = new ListGridRecord();
			    copyValues(result.getFetchedList().get(i), record);
			    list[i] = record;
			}
			response.setData(list);
			response.setStartRow(result.getStartRow());
			response.setEndRow(result.getEndRow());
			response.setTotalRows(result.getTotalRows());
			processResponse(requestId, response);
		    }
		});
    }

    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response) {
	// Retrieve record which should be added.
	JavaScriptObject data = request.getData();
	ListGridRecord rec = new ListGridRecord(data);
	T testRec = getEmptyRecord();
	copyValues(rec, testRec);
	IDataSourceServiceAsync<T> service = getDataSourceServiceAsync();

	service.add(testRec, new AsyncCallback<T>() {
	    public void onFailure(Throwable caught) {
		response.setStatus(RPCResponse.STATUS_FAILURE);
		processResponse(requestId, response);
	    }

	    public void onSuccess(T result) {
		ListGridRecord[] list = new ListGridRecord[1];
		ListGridRecord newRec = new ListGridRecord();
		copyValues(result, newRec);
		list[0] = newRec;
		response.setData(list);
		processResponse(requestId, response);
	    }
	});
    }

    protected abstract T getEmptyRecord();

    @Override
    protected void executeUpdate(final String requestId, final DSRequest request, final DSResponse response) {
	// Retrieve record which should be updated.
	JavaScriptObject data = request.getData();
	ListGridRecord rec = new ListGridRecord(data);
	// Find grid
	ListGrid grid = (ListGrid) Canvas.getById(request.getComponentId());
	// Get record with old and new values combined
	int index = grid.getRecordIndex(rec);
	rec = (ListGridRecord) grid.getEditedRecord(index);
	T testRec = getEmptyRecord();
	copyValues(rec, testRec);
	IDataSourceServiceAsync<T> service = getDataSourceServiceAsync();

	service.update(testRec, new AsyncCallback<T>() {
	    public void onFailure(Throwable caught) {
		response.setStatus(RPCResponse.STATUS_FAILURE);
		processResponse(requestId, response);
	    }

	    public void onSuccess(T result) {
		ListGridRecord[] list = new ListGridRecord[1];
		ListGridRecord updRec = new ListGridRecord();
		copyValues(result, updRec);
		list[0] = updRec;
		response.setData(list);
		processResponse(requestId, response);
	    }
	});
    }

    @Override
    protected void executeRemove(final String requestId, final DSRequest request, final DSResponse response) {
	// Retrieve record which should be removed.
	JavaScriptObject data = request.getData();
	final ListGridRecord rec = new ListGridRecord(data);
	T testRec = getEmptyRecord();
	copyValues(rec, testRec);
	IDataSourceServiceAsync<T> service = getDataSourceServiceAsync();

	service.remove(testRec, new AsyncCallback() {
	    public void onFailure(Throwable caught) {
		response.setStatus(RPCResponse.STATUS_FAILURE);
		processResponse(requestId, response);
	    }

	    public void onSuccess(Object result) {
		ListGridRecord[] list = new ListGridRecord[1];
		// We do not receive removed record from server.
		// Return record from request.
		list[0] = rec;
		response.setData(list);
		processResponse(requestId, response);
	    }

	});
    }

    protected abstract void copyValues(ListGridRecord from, T to);

    @Override
    protected abstract Object getValueOf(T from, String fieldName);
}
