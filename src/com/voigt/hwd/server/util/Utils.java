package com.voigt.hwd.server.util;

public class Utils {

    /**
     * this is more or less a dummy method to avoid cluttering the code with
     * "@SuppressWarnings("unchecked")"; it does an excplicit cast and sets the
     * SuppressWarning annotation; should be used everywhere a refactoring of
     * the code to avoid the warning is not possible
     * 
     * @param <T>
     * @param x
     * @return
     */
    public static <T> T cast(Object x) {

	@SuppressWarnings("unchecked")
	T newX = (T) x;

	return newX;
    }

}
