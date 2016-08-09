/**
 * 
 * The MIT License
 * 
 * Copyright (c) 2007 the original author or authors.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package com.googlecode.client;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.client.AxisStyle.AlignmentEnum;


/**
 * Shape markers for line plots, xy plots, and scatter plots.
 * 
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class ShapeMarker {
	
	/**
	 * Enumeration for specifying shape of maker.
	 * 
	 * @author Julien Chastang
	 *
	 */
    
    public static class ShapeEnum extends Enum 
    {
        public ShapeEnum() { }

        private ShapeEnum(String name, int ordinal) {
          super(name, ordinal);
        }

        public static /* final */ ShapeEnum ARROW =
          new ShapeEnum("ARROW", 0);
        public static /* final */ ShapeEnum CROSS =
          new ShapeEnum("CROSS", 1);
        public static /* final */ ShapeEnum DIAMOND =
          new ShapeEnum("DIAMOND", 2);
        public static /* final */ ShapeEnum CIRCLE =
            new ShapeEnum("CIRCLE", 3);
        public static /* final */ ShapeEnum SQUARE =
            new ShapeEnum("SQUARE", 4);
        public static /* final */ ShapeEnum VERTICAL_LINE_PARTIAL =
            new ShapeEnum("VERTICAL_LINE_PARTIAL", 5);
        public static /* final */ ShapeEnum VERTICAL_LINE_FULL =
            new ShapeEnum("VERTICAL_LINE_FULL", 6);
        public static /* final */ ShapeEnum HORIZONTAL_LINE =
            new ShapeEnum("HORIZONTAL_LINE", 7);
        public static /* final */ ShapeEnum X =
            new ShapeEnum("X", 8); 
        private static /* final */ Map values =
          makeValuesMap(new Enum[] { ARROW, CROSS, DIAMOND, CIRCLE, SQUARE, VERTICAL_LINE_PARTIAL, VERTICAL_LINE_FULL, HORIZONTAL_LINE, X  });

        public static ShapeEnum valueOf(String name) {
          return (ShapeEnum)values.get(name);
        }
    }    

	private final ShapeEnum shape;
	private final Color     color;
	private final int       startIndex;
	private final int       endIndex;
	private final int       size;
	
	private static final Map symbolMap = new HashMap(); // <ShapeEnum,String>
	
	static{
		symbolMap.put(ShapeEnum.ARROW, "a");
		symbolMap.put(ShapeEnum.CROSS, "c");
		symbolMap.put(ShapeEnum.DIAMOND, "d");
		symbolMap.put(ShapeEnum.CIRCLE, "o");
		symbolMap.put(ShapeEnum.SQUARE, "s");
		symbolMap.put(ShapeEnum.VERTICAL_LINE_PARTIAL, "v");
		symbolMap.put(ShapeEnum.VERTICAL_LINE_FULL, "V");
		symbolMap.put(ShapeEnum.HORIZONTAL_LINE, "h");
		symbolMap.put(ShapeEnum.X, "x");
	}

	/**
	 * 
	 * @param shape
	 *            Arrows, Diamonds, etc.
	 * @param color
	 * @param startIndex
	 *            The start index of the data series. The index starts at 1
	 *            (just like the Google Chart API).
	 * @param endIndex
	 *            The end index (inclusive) of the data series.
	 * @param size
	 *            The size of the shape marker.
	 */
	public ShapeMarker(final ShapeEnum shape, final Color color, final int startIndex, final int endIndex, final int size) {
		//TODO: Add error schecking on indices.
		this.shape      = shape;
		this.color      = color;
		this.startIndex = startIndex;
		this.endIndex   = endIndex;
		this.size       = size;
	}

	/**
	 * 
	 * @return the shape symbol.
	 */
	public String getShapeSymbol() {
		return (String)symbolMap.get(shape);
	}

	/**
	 * 
	 * @return the color of the shape marker.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * @return start index where the shape markers will apply.
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * 
	 * @return end index where the shape markers will apply.
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * 
	 * @return Size of shape marker.
	 */
	public int getSize() {
		return size;
	}
}