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

import java.util.Map;

/**
 *  Class for dealing with axis styles.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class AxisStyle {
	/**
	 * Enumeration for specifying position of label with respect to axis tick mark.
	 * 
	 * @author Julien Chastang
	 *
	 */
    
    public static class AlignmentEnum extends Enum 
    {
        public AlignmentEnum() { }

        private AlignmentEnum(String name, int ordinal) {
          super(name, ordinal);
        }

        public static /* final */ AlignmentEnum LEFT =
          new AlignmentEnum("LEFT", 0);
        public static /* final */ AlignmentEnum CENTER =
          new AlignmentEnum("CENTER", 1);
        public static /* final */ AlignmentEnum RIGHT =
          new AlignmentEnum("RIGHT", 2);

        private static /* final */ Map values =
          makeValuesMap(new Enum[] { LEFT, CENTER, RIGHT });

        public static AlignmentEnum valueOf(String name) {
          return (AlignmentEnum)values.get(name);
        }
    }

	private final Color color;
	private final int fontSize;
	private final AlignmentEnum alignment;

	/**
	 * 
	 * @param color
	 *            color of text displayed along the axis.
	 * @param fontSize
	 *            font size of text displayed along the axis.
	 * @param alignment
	 *            alingment of text along the axis with respect to the axis tick
	 *            marks.
	 */
	public AxisStyle(final Color color, final int fontSize, final AlignmentEnum alignment) {
		this.color     = color;
		this.fontSize  = fontSize;
		this.alignment = alignment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return color + "," + fontSize + "," + (alignment.getOrdinal() - 1);
	}
}