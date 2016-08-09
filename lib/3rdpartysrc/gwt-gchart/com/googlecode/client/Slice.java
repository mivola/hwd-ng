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
 * Slice of pie for pie charts. If the number of colors specified is less than
 * the number of slices, then colors are interpolated. If no colors are
 * specified, pie segment colors are interpolated from dark orange to pale
 * yellow.
 * 
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class Slice {
	private final Integer percent;
	private final String  label;
	
	private Color  color;

	/**
	 * 
	 * @param percent
	 *            percent of pie.
	 * @param color
	 *            color of slice.
	 * @param sliceLabel
	 *            label associated with slice.
	 */
	public Slice(final Integer percent, final Color color, final String sliceLabel){
		if ( percent.intValue() < 0 || percent.intValue() > 100 )
			throw new IllegalArgumentException("Slice of pie is expressed in terms of a percent which is a number between 0 and 100");
		this.percent    = percent;
		this.color      = color;
		this.label      = sliceLabel;
	}
	
	/**
	 * 
	 * @param percent
	 *            percent of pie.
	 * @param sliceLabel
	 *            label associated with slice.
	 */
	public Slice(final Integer percent, final String sliceLabel){
		if (percent.intValue() < 0 || percent.intValue() > 100)
			throw new IllegalArgumentException("Slice of pie is expressed in terms of a percent which is a number between 0 and 100");
		this.percent    = percent;
		this.label      = sliceLabel;
	}
	
	/**
	 * 
	 * @return percentage that this slice will take of of pie.
	 */
	public Integer getPercentage() {
		return percent;
	}

	/**
	 * 
	 * @return color of pie slice.
	 */
	public Color getColor() {
		return color;
	}
		
		
	/**
	 * 
	 * @return label of pie slice.
	 */
	public String getLabel() {
		return label;
	}
}