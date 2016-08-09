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

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 *  XYLine class.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class XYLine extends AbstractLine {
	private final Data xData;
	private final Data yData;

	/**
	 * 
	 * @param xData
	 *            x data
	 * @param yData
	 *            y data
	 */
	public XYLine(final Data xData, final Data yData){
		this.xData = xData;
		this.yData = yData;
	}

	/**
	 * This constructor will evenly space the data points along the x-axis
	 * 
	 * @param yData
	 */
	public XYLine(final Data yData){
		this.xData = new Data( new Float[]{} );
		this.yData = yData;
	}


	/**
	 * 
	 * @return x data
	 */
	public Data getXData() {
		return xData;
	}

	/**
	 * 
	 * @return y data
	 */
	public Data getYData() {
		return yData;
	}

	/**
	 * 
	 * @return x and y data in a list of size 2.
	 */
	public List getDataAsList()
	{
		return Arrays.asList( new Data[]{ xData,yData } );
	}
}