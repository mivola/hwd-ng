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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Pie chart class. If the number of colors specified in the Slice objects is
 * less than the number of slices, then colors are interpolated. If no colors
 * are specified, pie segment colors are interpolated from dark orange to pale
 * yellow.
 * 
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class PieChart extends AbstractGChart {
	private static final String PIE_CHART_URL    = GCHART_URL + "?";
	private static final String CHART_TYPE_PARAM = "cht=";
	private static final String LABEL_PARAM      = "chl=";

	private boolean threeD = false;
	private final List slices;

	/**
	 * 
	 * @param slices
	 *            the data slices of the pie chart.
	 */
	//public PieChart(final Slice...slices){
	//	super();
	//	this.slices = Arrays.asList(slices);
	//}
	
	/**
	 * 
	 * @param slices
	 *            list of data slices of the pie chart.
	 */
	public PieChart(final List slices){
		this.slices = slices;
	}

	
	/**
	 * Is the chart a 3D Chart.
	 * @return the threeD
	 */
	public boolean isThreeD() {
		return threeD;
	}

	/**
	 * If you want the chart to be 3D, set to true
	 * 
	 * @param threeD
	 *            boolean to determine if pie chart is rendered in 3D.
	 */
	public void setThreeD(final boolean threeD) {
		this.threeD = threeD;
	}
	
	/**
	 * Set the pie chart labels.
	 * 
	 * @param labels
	 *            list of pie chart labels.
	 */
	private void setPieChartLabels(final List labels)
	{
		String labelParam = "";
		int count = 0;
	    for ( Iterator it = labels.iterator(); it.hasNext(); ) 
	    {
	        String label = (String)it.next();
			String l = label.replaceAll("\\s", "+");
			labelParam += (count++ == 0) ? l : "|" + l;
		}
		if(count > 0)
		  addParameter(LABEL_PARAM, labelParam);
	}
	
	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.AbstractGChart#prepareData()
	 */
	protected void prepareData() {
		final List    labels  = new ArrayList(); // String
		final List    colors  = new ArrayList(); // Color
		
		final Float[] d = new Float[ slices.size() ];
		int i = 0;
        for ( Iterator it = slices.iterator(); it.hasNext(); ++i ) 
        {
            Slice slice = (Slice)it.next();		
			d[i] = new Float( slice.getPercentage().floatValue() );
			labels.add(slice.getLabel());
			colors.add(slice.getColor());
		}
		//TODO: Might want to warning to user about % not adding up to 100.
		List datas = new ArrayList();
		datas.add( new Data( d ) );
		setData( datas );
		setPieChartLabels( labels );
		setColors(colors);
	}
	
	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.AbstractGChart#getBaseURLString()
	 */
	protected String getBaseURLString() {
		return PIE_CHART_URL + CHART_TYPE_PARAM + ((threeD) ? "p3" : "p");
	}
}