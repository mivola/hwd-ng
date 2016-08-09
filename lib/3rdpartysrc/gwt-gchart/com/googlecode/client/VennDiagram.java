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
 * VennDiagram class.
 *
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class VennDiagram extends AbstractGChart {
	private static final String VennDiagramURL = GCHART_URL + "?cht=v";
	
	/**
	 * Please specify all values between 0 and 100
	 * 
	 * @param circle1Size
	 *            the first three values specify the relative sizes of three
	 *            circles, A, B, and C
	 * @param circle2Size
	 *            the first three values specify the relative sizes of three
	 *            circles, A, B, and C
	 * @param circle3Size
	 *            the first three values specify the relative sizes of three
	 *            circles, A, B, and C
	 * @param abIntersect
	 *            the fourth value specifies the area of A intersecting B
	 * @param bcIntersect
	 *            the fifth value specifies the area of B intersecting C
	 * @param caIntersect
	 *            the sixth value specifies the area of C intersecting A
	 * @param abcIntersect
	 *            the seventh value specifies the area of A intersecting B
	 *            intersecting C
	 */
	public VennDiagram( final Float circle1Size, final Float circle2Size, final Float circle3Size, final Float abIntersect, final Float bcIntersect, final Float caIntersect, final Float abcIntersect )
	{
		super();
		final Float[] d = new Float[]{ circle1Size, circle2Size, circle3Size, abIntersect, bcIntersect, caIntersect, abcIntersect };
		for ( Iterator it = Arrays.asList( d ).iterator(); it.hasNext(); ) 
	    {
	        Float f = (Float)it.next();
			if (f.floatValue() < 0 || f.floatValue() > 100)
				throw new IllegalArgumentException("Venn diagram values must be between 0 and 100: " + f);
		}
        List datas = new ArrayList();
        datas.add( new Data( d ) );
        setData( datas );
	}

	/**
	 * @param circle1Color
	 *            color of circle 1.
	 * @param circle2Color
	 *            color of circle 2.
	 * @param circle3Color
	 *            color of circle 3.
	 */
	public void setCircleColors(final Color circle1Color, final Color circle2Color, final Color circle3Color){		
	    List colors = new ArrayList();
	    colors.add( circle1Color );
	    colors.add( circle2Color );
	    colors.add( circle3Color );
	    setColors( colors );
	}
	
	/**
	 * @param circle1Legend
	 *            legend of circle 1
	 * @param circle2Legend
	 *            legend of circle 2
	 * @param circle3Legend
	 *            legend of circle 3
	 */
	public void setCircleLegends(final String circle1Legend, final String circle2Legend, final String circle3Legend){
	    List legends = new ArrayList();
	    legends.add( circle1Legend );
	    legends.add( circle2Legend );
	    legends.add( circle3Legend );
	    setLegends( legends );
	}

	protected void prepareData() {
		//Don't need it for Mr. Venn.
	}

	/* (non-Javadoc)
	 * @see org.gchart.AbstractGChart#getBaseURLString()
	 */
	protected String getBaseURLString() {
		return VennDiagramURL;
	}

}
