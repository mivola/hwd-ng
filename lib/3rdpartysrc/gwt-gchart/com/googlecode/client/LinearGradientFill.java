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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Linear gradient fill.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class LinearGradientFill implements Fill {
	
	private final int   angle;
	private final Color color;
	private final float offset;
	
	private final List colorsAndOffsets = new ArrayList(); // ColorAndOffset

	/**
	 * @param angle
	 *            specifies the angle of the gradient between 0 (horizontal) and
	 *            90 (vertical)
	 * @param color
	 *            color for gradient.
	 * @param offset
	 *            specify at what point the color is pure where: 0 specifies the
	 *            right-most chart position and 1 the left-most.
	 */
	public LinearGradientFill(final int angle, final Color color, final float offset){
		this.angle = angle;
		this.color = color;
		this.offset = offset;
	}
	
	/**
	 * Add additional fill gradients.
	 * 
	 * @param color
	 *            Additional color.
	 * 
	 * @param offset
	 *            Additional offset.
	 */
	public void addColorAndOffset(final Color color, final float offset){
		colorsAndOffsets.add(new ColorAndOffset(color,offset));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String params = "";
        for ( Iterator it = colorsAndOffsets.iterator(); it.hasNext(); ) 
        {
            ColorAndOffset co = (ColorAndOffset)it.next();		
			params += "," + co.color + "," + co.offset;
		}
		return "lg," + angle + "," + color + "," + offset + params;
	}
	
	/**
	 * Convenience inner static class.
	 *
	 */
	private static class ColorAndOffset{
		private final Color color;
		private final float offset;
		public ColorAndOffset(final Color color, final float offset) {
			this.color = color;
			this.offset = offset;
		}

		public String toString() {
			return color + "," + offset;
		}
	}
}