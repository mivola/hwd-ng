package com.voigt.hwd.server.remote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.voigt.hwd.client.admin.ds.FetchResult;
import com.voigt.hwd.client.admin.ds.criteria.GWTCriterion;
import com.voigt.hwd.domain.IDomainObject;
import com.voigt.hwd.server.service.IGenericService;
import com.voigt.hwd.server.util.criteria.Criterion;
import com.voigt.hwd.server.util.criteria.CriterionUtils;

public abstract class AbstractGwtDataSourceRemoteService<T extends IDomainObject> extends AbstractGwtRemoteService {

	public FetchResult<T> fetch(GWTCriterion criterion, String sorting, int startRow, int endRow) {
		/*
		 * String[] attributes = criteria.getAttributes();
		 * Log.debug("Attributi: " + attributes); for (String attr : attributes)
		 * { Log.debug(attr); Log.debug(", "); }
		 */
		Criterion crit = CriterionUtils.transformCriterion(criterion);

		FetchResult<T> fetchResult = new FetchResult<T>();
		Log.debug("Received a fetch request from " + startRow + " to " + endRow);
		ArrayList<T> pagedList = new ArrayList<T>();
		int i;
		T tmp;
		List<T> allEntries = getService().getAll();

		if (crit == null) {
			// no filter criteria
			for (i = startRow; i < endRow && i < allEntries.size(); i++) {
				// apply paging
				tmp = allEntries.get(i);
				pagedList.add(tmp);
			}
			fetchResult.setStartRow(startRow);
			fetchResult.setEndRow(i);
			fetchResult.setTotalRows(allEntries.size());
		} else {
			// apply filter criteria
			for (i = 0; i < allEntries.size(); i++) {
				// apply paging
				tmp = allEntries.get(i);
				if (crit == null || crit.pass(tmp)) {
					pagedList.add(tmp);
				}
			}
			fetchResult.setTotalRows(pagedList.size());

			while (pagedList.size() > endRow - startRow)
				pagedList.remove(pagedList.size() - 1);

			fetchResult.setStartRow(0);
			fetchResult.setEndRow(pagedList.size());
		}
		fetchResult.setFetchedList(pagedList);
		return fetchResult;
	}

	public T add(T record) {
		getService().saveOrUpdate(record);
		// list.add(record);
		return record;
	}

	public T update(T record) {
		Integer recordId = record.getId();
		if (recordId != null) {
			getService().saveOrUpdate(record);
			// int index = -1;
			// for (int i = 0; i < list.size(); i++) {
			// if (recordId.equals(list.get(i).getId())) {
			// index = i;
			// break;
			// }
			// }
			// if (index >= 0) {
			// list.set(index, record);
			// return record;
			// }
		} else {
			Log.debug("error while updating record: " + record);
		}
		return null;
	}

	public void remove(T record) {
		Integer recordId = record.getId();
		if (recordId != null) {
			getService().delete(record);
			// int index = -1;
			// for (int i = 0; i < list.size(); i++) {
			// if (recordId.equals(list.get(i).getId())) {
			// index = i;
			// break;
			// }
			// }
			// if (index >= 0) {
			// list.remove(index);
			// }
		} else {
			Log.debug("error while updating record: " + record);
		}
	}

	protected abstract IGenericService<T, Serializable> getService();
}
