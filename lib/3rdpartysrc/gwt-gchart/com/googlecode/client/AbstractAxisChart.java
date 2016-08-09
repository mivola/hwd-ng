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

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

/**
 * Contains code common to all charts with axes.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
abstract class AbstractAxisChart extends AbstractGChart{
	private static final String AXIS_LABEL_PARAM    = "chxl=";
	private static final String AXIS_TYPE_PARAM     = "chxt=";
	private static final String AXIS_RANGE_PARAM    = "chxr=";
	private static final String AXIS_POSITION_PARAM = "chxp=";
	private static final String AXIS_STYLE_PARAM    = "chxs=";
	
	private final List xAxisInfos     = new ArrayList();
	private final List yAxisInfos     = new ArrayList();
	private final List topAxisInfos   = new ArrayList();
	private final List rightAxisInfos = new ArrayList();
	
	AbstractAxisChart() {
		super();
	}

	/**
	 * Add X axis information.
	 * 
	 * @param axisInfo
	 *            x axis information.
	 */
	public void addXAxisInfo(final AxisInfo axisInfo){
		xAxisInfos.add(axisInfo);
	}
	
	/**
	 * Add Y axis information.
	 * 
	 * @param axisInfo
	 *            y axis information.
	 */
	public void addYAxisInfo(final AxisInfo axisInfo){
		yAxisInfos.add(axisInfo);
	}
	
	/**
	 * Add top axis information.
	 * 
	 * @param axisInfo
	 *            top axis information.
	 */
	public void addTopAxisInfo(final AxisInfo axisInfo){
		topAxisInfos.add(axisInfo);
	}
	
	/**
	 * Add right axis information.
	 * 
	 * @param axisInfo
	 *            right axis information.
	 */
	public void addRightAxisInfo(final AxisInfo axisInfo){
		rightAxisInfos.add(axisInfo);
	}
	
	/**
	 * 
	 */
	protected void setAxisLabels(){
		if (xAxisInfos.size() > 0 || yAxisInfos.size() > 0 || topAxisInfos.size() > 0 || rightAxisInfos.size() > 0){
			String axisType     = "";
			String axisLabel    = "";
			String axisPosition = "";
			String axisRange    = "";
			String axisStyle    = "";
			
			final Map map = new HashMap();
			map.put("x", xAxisInfos);
			map.put("y", yAxisInfos);
			map.put("t", topAxisInfos);
			map.put("r", rightAxisInfos);

			int axisIndex = 0;
		    for (Iterator it = map.keySet().iterator(); it.hasNext(); )
			{
		    	String key = (String)( it.next() );
				for ( Iterator itAxis = (( List )map.get( key )).iterator(); itAxis.hasNext(); )
				{
					AxisInfo axisInfo = (AxisInfo) itAxis.next();
					axisType  += (axisIndex == 0) ? key : "," + key;
					axisPosition +=  (axisInfo.getPositionString() != null) ? axisIndex + "," + axisInfo.getPositionString() + "|" : "";
					axisRange    +=  (axisInfo.getRangeString() != null) ? axisIndex + "," + axisInfo.getRangeString() + "|" : "";
					axisLabel    +=  (axisInfo.getLabels() != null) ? axisIndex + ":" + axisInfo.getLabels() + "|" : "";
					axisStyle    +=  (axisInfo.getAxisStyleString() != null) ? axisIndex + "," + axisInfo.getAxisStyleString() + "|" : "";
					axisIndex++;
				}
			}
			
			addParameter(AXIS_TYPE_PARAM,  axisType);
			// This is somewhat of a hack to deal with the fact that the Google
			// Chart API does not like it when a series of axis labels ends with a |
			if (axisLabel.length() > 0)
				addParameter(AXIS_LABEL_PARAM,    (axisLabel.endsWith("|")) ? axisLabel.substring(0, axisLabel.length() -1): axisLabel);
			if (axisRange.length() > 0)
				addParameter(AXIS_RANGE_PARAM,    (axisRange.endsWith("|")) ? axisRange.substring(0, axisRange.length() -1): axisRange);
			if (axisPosition.length() > 0)
				addParameter(AXIS_POSITION_PARAM, (axisPosition.endsWith("|")) ? axisPosition.substring(0, axisPosition.length() -1): axisPosition);
			if (axisStyle.length() > 0)
				addParameter(AXIS_STYLE_PARAM, (axisStyle.endsWith("|")) ? axisStyle.substring(0, axisStyle.length() -1): axisStyle);
		}
	}
}