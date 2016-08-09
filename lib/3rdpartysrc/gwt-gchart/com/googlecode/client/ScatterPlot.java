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
import java.util.List;


/**
 * Scatter plot class.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class ScatterPlot extends AbstractMarkableChart {
	private static final String SCATTER_PLOT = GCHART_URL + "?cht=s";

	private final ScatterPlotData data;
	
	/**
	 * 
	 * @param scatterPlotData
	 *            data to be rendered in this chart.
	 */
	public ScatterPlot(final ScatterPlotData scatterPlotData){
		super();
		data = scatterPlotData;
	}

	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.AbstractGChart#prepareData()
	 */
	protected void prepareData() {
		final List dataList = new ArrayList(); // Data
		dataList.add(data.getXData());
		dataList.add(data.getYData());
		if (data.getPointSizes() != null && data.getPointSizes().getSize() > 0){
			dataList.add(data.getPointSizes());
		}
		setData(dataList);
		final List colorList = new ArrayList(); // Color
		colorList.add(data.getColor());
		setColors(colorList);
		final List shapeMarkerList = new ArrayList(); // List<ShapeMarker>
		shapeMarkerList.add(data.getShapeMarkers());
		setShapeMarkers(shapeMarkerList);
		setRangeMarkers();
		setAxisLabels();
	}
	
	/* (non-Javadoc)
	 * @see org.gchart.AbstractGChart#getBaseURLString()
	 */
	protected String getBaseURLString() {
		return SCATTER_PLOT;
	}
}