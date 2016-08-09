package com.voigt.hwd.server.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ComparatorUtils {

    public static final String ID_METHOD = "getId";

    public static final String DISPLAY_NAME_METHOD = "getDisplayName";

    public static final String NAME_METHOD = "getName";

    /**
     * sorts a list by the given comparator
     * 
     * @param <T>
     *                the type of elements in the list
     * @param list
     *                the list of elements to be sorted
     * @param comparator
     *                the comparator that compares the elements in this list
     * @return a sorted list
     */
    public static <T> List<T> sortList(List<T> list, Comparator<T> comparator) {
	// convert the list into an array
	T[] array = Utils.cast(list.toArray());

	// sort the array using the given comparator
	Arrays.sort(array, comparator);

	// convert back into a list
	list = Arrays.asList(array);

	return list;

    }

    /**
     * sorts a map by the keys of the map using the given comparator
     * 
     * @param <T>
     *                the type of elements in the map
     * @param map
     *                the map of elements to be sorted
     * @param comparator
     *                the comparator that compares the elements in this map
     * @return a sorted map
     */
    @SuppressWarnings("hiding")
    public static <T, Object> Map<T, Object> sortMapByKey(Map<T, Object> map, Comparator<T> comparator) {
	// get all keys into a list
	List<T> list = new ArrayList<T>(map.keySet());

	// now sort the keys
	list = ComparatorUtils.sortList(list, comparator);

	// now create a new map with the sorted keys from the list and the
	// values of those keys from
	// the old map
	Map<T, Object> result = new LinkedHashMap<T, Object>();
	for (T key : list) {
	    Object value = map.get(key);
	    result.put(key, value);
	}

	// the resulting map
	return result;

    }

}
