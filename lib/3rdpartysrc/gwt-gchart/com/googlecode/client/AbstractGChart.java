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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Contains code common to all charts.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
abstract class AbstractGChart implements GChart {
		
	private static final String TITLE_WITH_COLOR_AND_SIZE_PARAM = "chts=";
	private static final String CHART_FILL_PARAM                = "chf=";
	private static final String LEGEND_PARAM                    = "chdl=";
	private static final String TITLE_PARAM                     = "chtt=";
	private static final String COLOR_PARAM                     = "chco=";
	private static final String SIZE_PARAM                      = "chs=";
    private static final String DATA_EXTENDED_ENCODING_PARAM    = "chd=e:";
    private static final String DATA_SIMPLE_ENCODING_PARAM      = "chd=s:";
    
	private final Map parameterMap = new HashMap();
	
	protected static final String GCHART_URL = "http://chart.apis.google.com/chart";

	private DataEncoding dataEncoding = DataEncoding.EXTENDED;

	/**
	 * 
	 */
	AbstractGChart(){
		addParameter(SIZE_PARAM,"200x125"); //Default chart size.
	}
    
    /**
     * 
     * @param data
     * @param dataSeriesSperator
     */
    protected final void setData( final List data )
    {
		String dataParam = "";
		int count = 0;
		for ( Iterator it = data.iterator(); it.hasNext(); ) 
		{
			Data datum = (Data)it.next();
			if (datum == null) continue;
            datum.setDataEncoding( dataEncoding );			
			dataParam += (count++ == 0) ? datum.toString() : "," + datum.toString();
		}
		if ( count > 0 )
            addParameter( ( dataEncoding.equals( DataEncoding.EXTENDED ) ) ? DATA_EXTENDED_ENCODING_PARAM : DATA_SIMPLE_ENCODING_PARAM, dataParam );
    }

	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.GChart#setSize(int, int)
	 */
	public void setSize(final int x, final int y){
		addParameter(SIZE_PARAM, x + "x" + y);
	}
	
	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.GChart#createURLString()
	 */
	public final String createURLString() {
		prepareData();
		String url = getBaseURLString();
		String params = "";
		for (Iterator it = parameterMap.keySet().iterator(); it.hasNext();)
		{
			String key = (String)it.next();
			params += "&" + key + "" + parameterMap.get(key);
		}
		return url + params;
	}

	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.GChart#createURLForHTML()
	 */
	public final String createURLForHTML() {
		return createURLString().replaceAll("&", "&amp;");
	}
	
	/**
	 * Internal method to set chart colors.
	 * @param colors
	 */
	protected final void setColors(final List colors) {
		String colorParam = "";
		int count = 0;
		for ( Iterator it = colors.iterator(); it.hasNext(); ) 
		{
			Color color = (Color)it.next();
			if (color == null) continue;
			colorParam += (count++ == 0) ? color.toString() : "," + color.toString();
		}
		if (count > 0){
			addParameter(COLOR_PARAM, colorParam);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.GChart#setTitle(java.lang.String)
	 */
	public void setTitle(final String title){
		addParameter(TITLE_PARAM, title.replaceAll("\\s", "+"));
	}
	
	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.GChart#setTtile(java.lang.String, com.googlecode.gchartjava.Color, int)
	 */
	public void setTitle(final String title, Color color, int fontSize){
		setTitle(title);
		addParameter(TITLE_WITH_COLOR_AND_SIZE_PARAM, color + "," + fontSize);
	}
	
	/**
	 * Internal method to set chart legends.
	 * @param legends
	 */
	protected final void setLegends(final List legends){
		String legendParam = "";
		int count = 0;
		for ( Iterator it = legends.iterator(); it.hasNext(); ) 
		{
			String legend = (String)it.next();
			if (legend == null) continue;
			final String l = legend.replaceAll("\\s", "+");
			legendParam += (count++ == 0) ? l : "|" + l;
		}
		if(count > 0)
		  addParameter(LEGEND_PARAM, legendParam);
	}
	
	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.GChart#setAreaFill(com.googlecode.gchartjava.Fill)
	 */
	public void setAreaFill(final Fill fill){
		String value = (String)parameterMap.get(CHART_FILL_PARAM);
		if (value == null){
			addParameter(CHART_FILL_PARAM, "c," + fill);
		}
		else{
			value += "|" +  "c," + fill;
			addParameter(CHART_FILL_PARAM, value);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.googlecode.gchartjava.GChart#setBackgroundFill(com.googlecode.gchartjava.Fill)
	 */
	public void setBackgroundFill(final Fill fill){
		String value = (String)parameterMap.get(CHART_FILL_PARAM);
		if (value == null){
			addParameter(CHART_FILL_PARAM, "bg," + fill);
		}
		else{
			value += "|" +  "bg," + fill;
			addParameter(CHART_FILL_PARAM, value);
		}
	}
	
     /**
     * Set the data encoding scheme. The only advantage to the simple encoding
     * scheme is it will ultimately result in shorter URLs, but at the cost of
     * lower resolution. gchartjava defaults to the extended encoding, but if you
     * have lots of data and if you are willing to sacrifice resolution, the
     * simple encoding may be right for you.
     * 
     * @param dataEncoding
     */
    public final void setDataEncoding( final DataEncoding dataEncoding )
    {
        this.dataEncoding = dataEncoding;
    }
	
	/**
	 * Convenience method for adding parameters to request.
	 * @param key
	 * @param value
	 */
	protected final void addParameter(final String key, final String value){
		  parameterMap.put(key, value); 
	}
	
	/**
	 * Prepare data for URL String formation.
	 */
	protected abstract void prepareData();
	
	/**
	 * Return the base URL String for the chart type.
	 * @return
	 */
	protected abstract String getBaseURLString();
}