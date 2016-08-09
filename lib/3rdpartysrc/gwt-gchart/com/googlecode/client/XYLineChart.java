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
 * XY Line chart.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class XYLineChart extends AbstractLineChart {
	private static final String XY_LINE_CHART_URL = GCHART_URL + "?cht=lxy";

	/**
	 * @param lines xy lines.
	 */
	//public XYLineChart(final XYLine... xylines){
	//	super(asUnmodifiableList(xylines));
	//	this.xylines = asUnmodifiableList(xylines);
	//}	

	/**
	 * 
	 * @param lines list of xy lines.
	 */
	public XYLineChart( final AbstractLine[] xylines )
	{
		super( xylines );
	}	

		
 	/* (non-Javadoc)
 	 * @see com.googlecode.gchartjava.AbstractLineChart#prepareLineData()
 	 */
	protected void prepareLineData() {
		final List datas = new ArrayList(); // Data
        
		for ( Iterator it = Arrays.asList( lines ).iterator(); it.hasNext(); ) 
        {
            XYLine xyline = (XYLine)it.next();		
			datas.add( xyline.getXData() );
			datas.add( xyline.getYData() );
		}
		setData( datas );
	}

	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.AbstractGChart#getBaseURLString()
	 */
	protected String getBaseURLString() {		
		return XY_LINE_CHART_URL;
	}	
}