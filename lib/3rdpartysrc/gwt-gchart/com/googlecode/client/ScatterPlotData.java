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
 * Class that encapsulates scatter plot data. There will be more stuff added here soon.
 *
 * @author Julien Chastang
 */
public class ScatterPlotData extends AbstractMarkableDataSeries {
	
	private final Data  xData;
	private final Data  yData;
	
	private Data pointSizes;

	
	
	/**
	 * @param xData
	 *            x data.
	 * @param yData
	 *            y data.
	 */
	public ScatterPlotData(final Data xData, final Data yData){
		this.xData = xData;
		this.yData = yData;
	}
	
	/**
	 * For the 3rd argument, specify the size of the data points. The elements
	 * in this data series define the sizes of each data point specified in the
	 * x,y data series. For the points to be properly rendered according to
	 * desired sizes, the developer must also specify shape markers for each
	 * point via MarkableDataSeries.setShapeMarker method.
	 * 
	 * 
	 * The size at which the data point is rendered is a function the marker
	 * size defined the setShapeMarker method, and the values passed into this
	 * method in the pointSizes object. For instance, if a point is defined (via
	 * the shape marker) with a size of 20, then the element that corresponds to
	 * that point in the data series will render proportionally to that marker
	 * definition. A value of 0 will render at size 0, a value of 100 will
	 * render at size 20.
	 * 
	 * @param xData
	 *            x data
	 * @param yData
	 *            y data
	 * @param pointSizes
	 *            size of x,y data
	 */
	public ScatterPlotData(final Data xData, final Data yData, final Data pointSizes){
		this.xData      = xData;
		this.yData      = yData;
		this.pointSizes = pointSizes;
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
	 * Specify the size of the data points. The elements in this data series
	 * define the sizes of each data point specified in the x,y data series. For
	 * the points to be properly rendered according to desired sizes, the
	 * developer must also specify shape markers for each point via
	 * MarkableDataSeries.setShapeMarker method.
	 * 
	 * The size at which the data point is rendered is a function the marker
	 * size defined the setShapeMarker method, and the values passed into this
	 * method in the pointSizes object. For instance, if a point is defined (via
	 * the shape marker) with a size of 20, then the element that corresponds to
	 * that point in the data series will render proportionally to that marker
	 * definition. A value of 0 will render at size 0, a value of 100 will
	 * render at size 20.
	 * 
	 * 
	 * @param pointSizes
	 *            data point sizes.
	 */
	public void setPointSizes(final Data pointSizes){
		this.pointSizes = pointSizes;
	}

	/**
	 * 
	 * @return data point sizes.
	 */
	public Data getPointSizes(){
		return pointSizes;
	}
}