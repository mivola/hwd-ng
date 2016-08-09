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

/**
 * Class for dealing with line styles. You can specify if a line is solid or
 * dotted, and the thickness of the line with this class.
 * 
 * @author Julien Chastang
 */
public class LineStyle {
	
	private final int lineThickness;
	private final int lengthOfLineSegment;
	private final int lengthOfBlankSegment;
	
	/**
	 * 
	 * @param lineThickness
	 *            Thickness of line.
	 * @param lengthOfLineSegment
	 *            Length of line segment.
	 * @param lengthOfBlankSegment
	 *            Length of blank segment.
	 */
	public LineStyle(final int lineThickness, final int lengthOfLineSegment,
			int lengthOfBlankSegment) {
		super();
		this.lineThickness        = lineThickness;
		this.lengthOfLineSegment  = lengthOfLineSegment;
		this.lengthOfBlankSegment = lengthOfBlankSegment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return  lineThickness + "," + lengthOfLineSegment + "," + lengthOfBlankSegment;
	}
}