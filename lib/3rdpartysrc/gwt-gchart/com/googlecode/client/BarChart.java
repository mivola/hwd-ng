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
import java.util.Arrays;

/**
 * 
 * Bar chart class that supports vertical and horizontal bar charts. Bars from
 * different data series can either be stacked or side-by-side.
 * 
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class BarChart extends AbstractAxisChart {
	private static final String BAR_CHART_URL = GCHART_URL + "?";
	private static final String CHART_TYPE_PARAM = "cht=b";
	private static final String BAR_CONFIG = "chbh=";

	private boolean dataStacked = false;
	private boolean horizontal = false;
	private int barWidth = 23; // This is the default, as far as I can tell.
	private int spaceBetweenGroupsOfBars = 10; // This is the default, as far
												// as I can tell.

	private final List barSeriesList; // BarChartDataSeries

	/**
	 * @param barChartData
	 *            A bar chart data series.
	 */
//	public BarChart(final BarChartDataSeries... barChartData) {
//		super();
//		this.barSeriesList = asUnmodifiableList(barChartData);
//	}

	/**
	 * @param barChartData
	 *            A bar chart data series.
	 */
	public BarChart(final List barChartData) {
		this.barSeriesList = barChartData;
	}

	/**
	 * 
	 * @param dataStacked
	 *            If true, data from different bar chart series will be stacked
	 *            instead of side-by-side.
	 */
	public void setDataStacked(final boolean dataStacked) {
		this.dataStacked = dataStacked;
	}

	/**
	 * 
	 * @param horizontal
	 *            If true, the bar chart will be horizontal instead of vertical.
	 */
	public void setHorizontal(final boolean horizontal) {
		this.horizontal = horizontal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.gchartjava.AbstractGChart#prepareData()
	 */
	protected void prepareData() {
		final List datas = new ArrayList(); // Data
		final List colors = new ArrayList(); // Color
		final List legends = new ArrayList(); // String

        for ( Iterator it = barSeriesList.iterator(); it.hasNext(); ) 
        {
            BarChartDataSeries series = (BarChartDataSeries)it.next();		
			datas.add(series.getData());
			colors.add(series.getColor());
			legends.add(series.getLegend());
		}

		setData(datas);
		setLegends(legends);
		setColors(colors);
		addParameter(BAR_CONFIG, barWidth + "," + spaceBetweenGroupsOfBars);
		setAxisLabels();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.gchartjava.AbstractGChart#getBaseURLString()
	 */
	protected String getBaseURLString() {
		return BAR_CHART_URL + CHART_TYPE_PARAM + ((horizontal) ? "h" : "v")
				+ ((dataStacked) ? "s" : "g");
	}

	/**
	 * 
	 * @param barWidth
	 *            Bar width in pixels. If not set defaults to 23.
	 */
	public void setBarWidth(final int barWidth) {
		this.barWidth = barWidth;
	}

	/**
	 * 
	 * @param spaceBetweenGroupsOfBars
	 *            The space between groups of bars. Bars within a group have
	 *            half the specified spacing. If not set default to 10.
	 */
	public void setSpaceBetweenGroupsOfBars(final int spaceBetweenGroupsOfBars) {
		this.spaceBetweenGroupsOfBars = spaceBetweenGroupsOfBars;
	}
}