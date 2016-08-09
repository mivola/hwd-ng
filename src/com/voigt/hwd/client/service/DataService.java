package com.voigt.hwd.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface DataService extends RemoteService {

	public static final String SERVICE_URI = "dataservice";

	public static class Util {

		public static DataServiceAsync getInstance() {

			DataServiceAsync instance = (DataServiceAsync) GWT
					.create(DataService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}
	
	public String[][] getData();

}
