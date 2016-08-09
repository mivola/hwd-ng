/**
 * 
 */
package com.voigt.hwd.client.service;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Default implementation of the AsyncCallback. Implements the onFailure method.
 * 
 * @author bruno.marchesson
 * 
 */
public abstract class DefaultCallback<T> implements AsyncCallback<T> {
    // -------------------------------------------------------------------------
    //
    // Public interface
    //
    // -------------------------------------------------------------------------
    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
     */
    public void onFailure(Throwable caught) {
	// Message to display
	//
	String message = caught.getMessage();
	if ((message == null) || (message.length() == 0)) {
	    message = caught.toString();
	}

	Log.error("exception caught: " + caught);
	caught.printStackTrace();

	// Display from the top application
	//

	// ApplicationParameters.getInstance().getApplication().displayErrorMessage(message);
    }
}
