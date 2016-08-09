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
 * Line class for line charts.
 *
 * @author Julien Chastang
 */
public class Line extends AbstractLine {
	private final Data data;

	/**
	 * 
	 * @param data
	 *            Line data.
	 */
	public Line(final Data data){
		this.data = data;
	}

	/**
	 * 
	 * @param data
	 *            Line data.
	 * @param color
	 *            Line color.
	 */
	public Line(final Data data, final Color color){
		this.data = data;
		setColor(color);
	}

	/**
	 * 
	 * @param data
	 *            Line data.
	 * @param color
	 *            Line color.
	 * @param legend
	 *            Line legend.
	 */
	public Line(final Data data, final Color color, final String legend){
		this.data = data;
		setColor(color);
		setLegend(legend);
	}

	
	/**
	 * @return Line data.
	 */
	public Data getData(){
		return data;
	}
}