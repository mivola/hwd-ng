// Creato il 02/lug/08
/**
 * 
 */
package com.voigt.hwd.server.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;

/**
 * Reflection utilities
 * 
 * @author Roberto Mozzicato
 * @author Infracom IT s.p.a.
 * 
 */
public class ReflUtils {
	protected static final HashMap<Class<?>, Method> ID_MAP = new HashMap<Class<?>, Method>();

	public static Method getMethod(Object oggetto, String propertyName) {
		if (oggetto == null || propertyName == null)
			return null;

		try {
			return oggetto.getClass().getMethod(
					"get" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1), new Class[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param oggetto
	 * @param propertyName
	 * @return
	 */
	public static Object getValue(Object oggetto, String propertyName) {
		if (oggetto == null || propertyName == null)
			return null;

		Object ret = null;

		try {
			Method method = oggetto.getClass().getMethod(
					"get" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1), new Class[0]);
			ret = method.invoke(oggetto, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String pathOfClass(Class<?> cls) {
		String ret = classLocation(cls);
		if (ret != null)
			ret = ret.substring(0, ret.lastIndexOf(File.separatorChar));

		return ret;
	}

	public static String classLocation(Class<?> cls) {
		if (cls == null)
			return null;
		String name = cls.getName().replace('.', '/');
		URL loc = cls.getResource("/" + name + ".class");
		File f = new File(loc.getFile());
		// Class file is inside a jar file.
		if (f.getPath().startsWith("file:")) {
			String s = f.getPath();
			int index = s.indexOf('!');
			// It confirm it is a jar file
			if (index != -1) {
				f = new File(s.substring(5).replace('!', File.separatorChar));
				return f.getPath();
			}
		}
		try {
			f = f.getCanonicalFile();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		return f.getPath();
	}

}
