package com.voigt.hwd.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voigt.hwd.client.admin.ds.FetchResult;
import com.voigt.hwd.client.admin.ds.criteria.GWTCriterion;

public interface IDataSourceService<T> extends RemoteService {

    public List<T> getAllEntries();

    public T getOneEntry(int id);

    public boolean saveOneEntry(T t);

    public boolean deleteOneEntry(int id);

    // from http://forums.smartclient.com/showthread.php?t=4814&page=5
    public FetchResult<T> fetch(GWTCriterion criterion, String sorting, int startRow, int endRow);

    public T add(T record);

    public T update(T record);

    public void remove(T record);

}
