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

import java.util.List;


/**
 * A markable data series is comprised of lines, XY lines, and scatter plot data
 * that can be annotated with shape markers.
 * 
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public interface MarkableDataSeries {
	
	/**
	 * @return data series legend.
	 */
	public String getLegend();
	
	
	/**
	 * @param legend
	 *            data series legend.
	 */
	public void setLegend(final String legend);
	
	/**
	 * @return color of data series.
	 */
	public Color getColor();
	
	/**
	 * @param color
	 *            color of data series.
	 */
	public void setColor(final Color color);
	
	/**
	 * @return List of shape markers.
	 */
	public List getShapeMarkers();

	/**
	 * @param shapeMarker
	 *            shape marker for this data series.
	 */
	public void addShapeMarker(final ShapeMarker shapeMarker);
}