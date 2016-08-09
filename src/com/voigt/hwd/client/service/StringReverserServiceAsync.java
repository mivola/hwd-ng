package com.voigt.hwd.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * StringReverserService
 *
 * This is the interface used to define the String Reverser Service
 *
 * @author Masud Idris
 * @version 1.0
 * @since Dec 6, 2007, 11:35:32 AM
 *
 */

public interface StringReverserServiceAsync {
  public void reverseString(String stringToReverse, AsyncCallback callback);
}
