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
 * Top level interface for all charts.
 *
 * @author Julien Chastang
 */
public interface GChart{
		
	/**
	 * Set the chart size. If no size is specified it will default to 200x125.
	 * 
	 * @param x
	 *            Horizontal length.
	 * @param y
	 *            Vertical length.
	 */
	public void setSize(final int x, final int y);
	
	/**
	 * Create URL String from parameters. You can copy and paste this this string into
	 * your web browser, and see if you get the results you anticipated.
	 * 
	 * @return
	 */
	public String createURLString();
	
	/**
	 * Create a URL with the ampersand character entity reference &amp; in place of
	 * an ampersand. Useful for embedding your link in a web page.
	 * 
	 * @return
	 */
	public String createURLForHTML();
		
	/**
	 * Specify a chart title. Use a pipe character (|) to force a line break.
	 * 
	 * @param title Chart title.
	 */
	public void setTitle(final String title);

	/**
	 * Specify a chart title with color and font size. Use a pipe character (|)
	 * to force a line break.
	 * 
	 * @param title
	 *            Chart title.
	 * @param color
	 *            Chart title color.
	 * @param fontSize
	 *            Chart title font size.
	 */
	public void setTitle(final String title, Color color, int fontSize);

	
	/**
	 * Specify chart area fill.
	 * 
	 * @param fill
	 *            Chart fill.
	 */
	public void setAreaFill(final Fill fill);
	
	/**
	 * Specify background fill.
	 * 
	 * @param fill
	 *            Background fill.
	 */
	public void setBackgroundFill(final Fill fill);
}