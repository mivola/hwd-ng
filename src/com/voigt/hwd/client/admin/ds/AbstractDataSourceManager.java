package com.voigt.hwd.client.admin.ds;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.FormItem;

public abstract class AbstractDataSourceManager<T> implements IDataSourceManager {

	private final DataSource dataSource;

	public AbstractDataSourceManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	protected String getStringValue(final FormItem[] fields, String fieldName) {
		for (FormItem formItem : fields) {
			if (formItem.getName().equals(fieldName)) {
				return formItem.getDisplayValue();
			}
		}
		return null;
	}

	protected int getIntValue(FormItem[] fields, String fieldName) {
		for (FormItem formItem : fields) {
			if (formItem.getName().equals(fieldName)) {
				String value = formItem.getDisplayValue();

				try {
					int parseInt = Integer.parseInt(value);
					return parseInt;
				} catch (Exception e) {
					// TODO: log something meaningful
				}
				break;

			}
		}
		return -1;
	}

}
