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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Class that describes axis labels, label positions, axis range, and style. 
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class AxisInfo {
	private String[]  labels;
	private Range     range;
	private Float[]   positions;
	private AxisStyle axisStyle;
	
	/**
	 * The first label is placed at the start of the axis, the last at the end,
	 * others are uniformly spaced in between.
	 * 
	 * @param labels
	 *            Labels that will be displayed along side the axis.
	 */
	public AxisInfo( final String[] labels )
	{
		this.labels = labels;
	}
	
	/**
	 * The first label is placed at the start of the axis, the last at the end,
	 * others are uniformly spaced in between.
	 * 
	 * @param labels
	 *            Labels that will be displayed along side the axis.
	 */
	//public AxisInfo(final String... labels){
	//	this.labels = Arrays.asList(labels);
	//}
	
	/**
	 * Labels are placed on the axis according to the position argument. In
	 * turn, the positions are expressed in terms of range values. In other
	 * words, the position values should fall between the min and max of the
	 * range.
	 * 
	 * @param minRange
	 *            The start of the range.
	 * @param maxRange
	 *            The end of the range.
	 * @param position
	 *            Position of labels.
	 * @param labels
	 *            Labels that will be displayed along side the axis.
	 */
	public AxisInfo( final int minRange, final int maxRange, final Float[] positions, final String[] labels )
	{
		this.labels = labels;
		this.range = new Range( minRange, maxRange );
		this.positions = positions;
	}
	
	/**
	 * Define a range on the axis. Values are evenly spaced between the min and
	 * max range.
	 * 
	 * @param minRange
	 *            The start of the range.
	 * @param maxRange
	 *            The end of the range.
	 */
	public AxisInfo(final int minRange, final int maxRange){
		this.range = new Range(minRange,maxRange);
	}
	
	/**
	 * 
	 * @return
	 */
	String getRangeString() {
		return (range != null) ? range.min + "," + range.max : null;
	}
	
	/**
	 * 
	 * @return
	 */
	String getLabels(){
		if (labels == null)
			return null;
		String returnString = "";
        for ( Iterator it = Arrays.asList( labels ).iterator(); it.hasNext(); ) 
        {
            String s = (String)it.next();		
			returnString += "|" + s.replaceAll("\\s", "+") ;
		}
		return returnString;
	}
	
	/**
	 * 
	 * @return
	 */
	String getPositionString(){
		if (positions == null)
			return null;
		String returnString = "";
		int cnt = 0;
        for ( Iterator it = Arrays.asList( positions ).iterator(); it.hasNext(); ) 
        {
            Float f = (Float)it.next();		
			returnString += (cnt++ > 0) ? "," + f.toString() : f.toString();
		}
		return returnString;
	}
	
	/**
	 *
	 */
	private static class Range{
		private final int min;
		private final int max;
		
		private Range(final int min, final int max) {
			this.min = min;
			this.max = max;
		}
	}

	/**
	 * 
	 * @return
	 */
	String getAxisStyleString() {
		return (axisStyle == null) ? null : axisStyle.toString();
	}

	/**
	 * 
	 * @param axisStyle
	 *            Set the axis style that will define the appearance of the text
	 *            along side the axis.
	 */
	public void setAxisStyle(AxisStyle axisStyle) {
		this.axisStyle = axisStyle;
	}
}