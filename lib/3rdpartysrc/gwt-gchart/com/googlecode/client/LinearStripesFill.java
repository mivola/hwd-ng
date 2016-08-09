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
 * Define linear stripes with this class.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class LinearStripesFill implements Fill {
	private final int   angle;
	private final Color color;
	private final float width;
	
	private List colorsAndWidths = new ArrayList(); // ColorAndWidth

	/**
	 * @param angle
	 *            specifies the angle of the gradient between 0 (horizontal) and
	 *            90 (vertical)
	 * @param color
	 *            color of stripe.
	 * @param must
	 *            be between 0 and 1 where 1 is the full width of the chart.
	 *            Stripes are repeated until the chart is filled.
	 */
	public LinearStripesFill(final int angle, final Color color, final float width){
		this.angle = angle;
		this.color = color;
		this.width = width;
	}
	
	/**
	 * Color and width for additional stripes.
	 * 
	 * @param color
	 *            color of additional stripe.
	 * @param width
	 *            width of stripe.
	 */
	public void addColorAndWidth(final Color color, final float width){
		colorsAndWidths.add(new ColorAndWidth(color,width));
	}

	public String toString() {
		String params = "";
        for ( Iterator it = colorsAndWidths.iterator(); it.hasNext(); ) 
        {
            ColorAndWidth co = (ColorAndWidth)it.next();		
			params += "," + co.color + "," + co.width;
		}
		return "ls," + angle + "," + color + "," + width + params;
	}
	
	/**
	 * Convenience inner static class.
	 *
	 */
	private static class ColorAndWidth{
		private final Color color;
		private final float width;
		public ColorAndWidth(final Color color, final float width) {
			this.color = color;
			this.width = width;
		}

		public String toString() {
			return color + "," + width;
		}
	}
}