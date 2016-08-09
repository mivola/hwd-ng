package com.voigt.hwd.client.service;

import java.util.List;

import com.voigt.hwd.client.admin.ds.FetchResult;
import com.voigt.hwd.client.admin.ds.criteria.GWTCriterion;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IDataSourceServiceAsync<T> {

    public void getAllEntries(AsyncCallback callback);

    public void getOneEntry(int id, AsyncCallback callback);

    public void saveOneEntry(T t, AsyncCallback callback);

    public void deleteOneEntry(int id, AsyncCallback callback);

    // from http://forums.smartclient.com/showthread.php?t=4814&page=5
    public void fetch(GWTCriterion criterion, String sorting, int startRow, int endRow, AsyncCallback callback);

    public void add(T record, AsyncCallback callback);

    public void update(T record, AsyncCallback callback);

    public void remove(T record, AsyncCallback callback);

}
