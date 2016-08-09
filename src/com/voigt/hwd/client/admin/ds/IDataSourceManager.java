package com.voigt.hwd.client.admin.ds;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.FormItem;

public interface IDataSourceManager {

	final static String ID_FIELD = "id";

	public DataSource getDataSource();

	public boolean updateExisting(int pk, FormItem[] fields);

	public boolean saveNew(FormItem[] fields);

	public boolean delete(int pk);

}
