package com.voigt.hwd.server.util;

import java.lang.reflect.Method;
import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenericComparator<T> implements Comparator<T> {

	final Log log = LogFactory.getLog(this.getClass());

	private final Class<T> type;

	/* the name of the method whose output should be used for the comparison */
	private String methodName;

	public GenericComparator(Class<T> type, String methodName) {
		this.type = type;
		this.methodName = methodName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(T o1, T o2) {

		if (!isTypeValid()) {
			log.error("the current type does not support this sort operation! type: " + type);
			return 0;
		}

		// get the two values
		Object value1 = getValueOfCompareMethod(o1);
		Object value2 = getValueOfCompareMethod(o2);

		// check the return type and compare it accordingly
		int compare = 0;
		if ((value1 == null) && (value2 == null)) {
			// both values are null
			compare = 0;
		} else if ((value1 == null) || (value2 == null)) {
			// just one value is null
			compare = (value1 == null ? 1 : -1);
		}

		else if (value1 instanceof String) {
			String string1 = (String) value1;
			String string2 = (String) value2;
			compare = string1.compareToIgnoreCase(string2);
		} else if (value1 instanceof Integer) {
			Integer integer1 = (Integer) value1;
			Integer integer2 = (Integer) value2;
			compare = integer1.compareTo(integer2);
		} else {
			log.error("unsupported return type (" + value1.getClass() + ") of method " + methodName);
		}

		return compare;

	}

	/**
	 * gets the String that is used for the comparison
	 * 
	 * @param o
	 *            the object on which the method should be invoked
	 * @return the String which is the result of the invoked method
	 */
	protected Object getValueOfCompareMethod(T o) {
		Object value = "";

		try {
			// get the required method
			Method method = getRequiredMethod();

			// call the method
			Object arglist[] = new Object[0];
			Object result = method.invoke(o, arglist);
			// log.trace("result: " + result);

			value = result;
		} catch (Exception e) {
			log.error("an error occured during the usage of reflection! type: " + type, e);
		}

		return value;
	}

	/**
	 * checks if the required method exists for the given type
	 * 
	 * @return true, if the method exists; false otherwise
	 */
	private boolean isTypeValid() {

		boolean valid = false;

		try {

			// check if the required method is available
			getRequiredMethod();

			// if the required method doesnt exist, an exception would be
			// thrown...
			valid = true;
			// log.trace("method " + methodName + " found :-)");

		} catch (ClassNotFoundException e) {
			log.error("an error occured during the usage of reflection! class of type: " + type + " not found", e);
		} catch (SecurityException e) {
			log.error("an error occured during the usage of reflection! type: " + type, e);
		} catch (NoSuchMethodException e) {
			log.error("an error occured during the usage of reflection! method: " + methodName
					+ " is not defined in type: " + type, e);
		}

		return valid;
	}

	/**
	 * tries to get the required method for the comparison
	 * 
	 * @return the required method
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	private Method getRequiredMethod() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
		Class clazz = Class.forName(type.getName());
		Method method = clazz.getMethod(methodName, new Class[0]);

		return method;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
