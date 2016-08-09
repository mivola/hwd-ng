package com.voigt.hwd.client.admin.ds;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.FormItem;

public class ReadOnlyDataSourceManager extends AbstractDataSourceManager<Object> {

    public ReadOnlyDataSourceManager(DataSource dataSource) {
	super(dataSource);
    }

    public boolean updateExisting(int pk, FormItem[] fields) {
	// do nothing since this is a read only datasource
	return true;
    }

    public boolean saveNew(FormItem[] fields) {
	// do nothing since this is a read only datasource
	return true;
    }

    public boolean delete(int pk) {
	// do nothing since this is a read only datasource
	return true;
    }

}
