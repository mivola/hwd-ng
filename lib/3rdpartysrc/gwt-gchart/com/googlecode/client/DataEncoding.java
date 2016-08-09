package com.googlecode.client;

import java.util.Map;

/**
 * Data encoding enumeration. The only advantage to the simple encoding scheme
 * is it will ultimately result in shorter URLs, but at the cost of lower
 * resolution. gchartjava defaults to extended encoding, but if you have lots of
 * data and if you are willing to sacrifice resolution, the simple encoding may
 * be right for you.
 * 
 * @author Julien Chastang
 * @author Silvin Lubecki
 */

public class DataEncoding extends Enum 
{
    public DataEncoding() { }

    private DataEncoding(String name, int ordinal) {
      super(name, ordinal);
    }

    public static /* final */ DataEncoding SIMPLE =
      new DataEncoding("SIMPLE", 0);
    public static /* final */ DataEncoding EXTENDED =
      new DataEncoding("EXTENDED", 1);

    private static /* final */ Map values =
      makeValuesMap(new Enum[] { SIMPLE, EXTENDED });

    public static DataEncoding valueOf(String name) {
      return (DataEncoding)values.get(name);
    }
}

