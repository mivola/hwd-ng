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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Common code for line charts exists here.
 * 
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
abstract class AbstractLineChart extends AbstractMarkableChart {

	private static final String LINE_STYLE_PARAM = "chls=";

	protected final AbstractLine[] lines;

	/**
	 * @param lines
	 *            Lines to be rendered in chart.
	 */
	AbstractLineChart( final AbstractLine[] lines ) 
	{
		super();
		this.lines = lines;
	}

	/**
	 * Internal method for setting the styles of the line.
	 * 
	 * @param lineStyles
	 */
	private void setLineStyle( final List lineStyles )
	{
		String lineStyleParam = "";
		int count = 0;
        for ( Iterator it = lineStyles.iterator(); it.hasNext(); ) 
        {
            LineStyle lineStyle = (LineStyle)it.next();
			if (lineStyle == null)
				continue;
			lineStyleParam += (count++ == 0) ? lineStyle.toString() : "|" + lineStyle.toString();
		}
		if (count > 0) {
			addParameter(LINE_STYLE_PARAM, lineStyleParam);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.gchartjava.AbstractGChart#prepareData()
	 */
	protected void prepareData() {
		final List legends      = new ArrayList(); // Legends
		final List colors       = new ArrayList(); // Color
		final List lStyles      = new ArrayList(); // LineStyle
		final List shapeMarkers = new ArrayList(); // List<ShapeMarker>

		// Logic to make sure things stay in step.
		boolean hasLegend       = false;
		boolean hasColor        = false;
		boolean hasLineStyle    = false;
		boolean hasShapeMarkers = false;

		// Logic to make sure things stay in step continued.
        for ( Iterator it = Arrays.asList( lines ).iterator(); it.hasNext(); ) 
        {
            ILine line = (ILine)it.next();
            hasLegend       |= (line.getLegend() != null);
			hasColor        |= (line.getColor() != null);
			hasLineStyle    |= (line.getLineStyle() != null);
			hasShapeMarkers |= (line.getShapeMarkers().size() > 0);
		}

		// Logic to make sure things stay in step continued.
        for ( Iterator it = Arrays.asList( lines ).iterator(); it.hasNext(); ) 
        {
            ILine line = (ILine)it.next();
            if (hasLegend) {
				legends.add((line.getLegend() != null) ? line.getLegend() : "+");
			}
			if (hasColor) {
				colors.add((line.getColor() != null) ? line.getColor() : Color.BLACK);
			}
			if (hasLineStyle) {
				// TODO: Create some static line styles.
				lStyles.add((line.getLineStyle() != null) ? line.getLineStyle() : new LineStyle(1, 1, 0));
			}
			if (hasShapeMarkers) {
				shapeMarkers.add((line.getShapeMarkers().size() > 0) ? line.getShapeMarkers() : null);
			}
		}

		setLegends(legends);
		setColors(colors);
		setLineStyle(lStyles);
		setShapeMarkers(shapeMarkers);
		setRangeMarkers();
		prepareLineData();
		setAxisLabels();
	}

	/**
	 * Prepare line data individually for subtypes as this process is different
	 * for different types of charts.
	 */
	protected abstract void prepareLineData();

	/**
	 * 
	 * @return
	 */
	protected final List getLines() {
		return Arrays.asList( lines );
	}
}