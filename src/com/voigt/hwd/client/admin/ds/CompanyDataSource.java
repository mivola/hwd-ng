package com.voigt.hwd.client.admin.ds;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class CompanyDataSource extends DataSource {
	public CompanyDataSource() {
		setID("companyDataSource");

		DataSourceTextField id = new DataSourceTextField("id", "ID");
		DataSourceTextField companyName = new DataSourceTextField("company", "Country");
		DataSourceFloatField price = new DataSourceFloatField("price", "Price");
		DataSourceFloatField change = new DataSourceFloatField("change", "Change");
		DataSourceFloatField pctChange = new DataSourceFloatField("pctChange", "% Change");
		DataSourceDateField lastChanged = new DataSourceDateField("lastChanged", "Last Changed");
		DataSourceTextField symbol = new DataSourceTextField("symbol", "Symbol");
		DataSourceTextField industry = new DataSourceTextField("industry", "Industry");

		setClientOnly(true);
		setFields(id, companyName, price, change, pctChange, lastChanged, symbol, industry);
	}
}
