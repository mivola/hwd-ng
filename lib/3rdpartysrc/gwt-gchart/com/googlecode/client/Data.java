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
import java.util.Iterator;

/**
 * The Data class that contains the information that will be rendered in the
 * chart.
 * 
 * This class takes an array of floats from 0 to 100. If your data does not fall
 * in that range, you need to transform it so it does. This is the case for
 * negative number, for instance.
 * 
 * For the curious, behind the scenes, this class converts the float values to the
 * Google Chart API simple or extended encoding.
 * 
 * @author Julien Chastang
 * @author Silvin Lubecki
 */
public class Data 
{
    
    private static final char[] extendedEncodingChars = { 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '-', '.' };
    
    private static final char[] simpleEncodingChars = { 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9'};

	private static final String[] extendedEncoding = new String[4096];

    private static final String[] simpleEncoding   = new String[62];

    static { // Fill the encoding arrays in static block.
        int cnt = 0;
        for (int i = 0; i < extendedEncodingChars.length; i++) {
            for (int j = 0; j < extendedEncodingChars.length; j++) {
                extendedEncoding[cnt++] = extendedEncodingChars[i] + ""
                        + extendedEncodingChars[j];
            }
        }
        cnt = 0;
        for (int i = 0; i < simpleEncodingChars.length; i++){
            simpleEncoding[cnt++] = simpleEncodingChars[i] + "";
        }
    }

	private final Float[] data;
	
    private DataEncoding dataEncoding = DataEncoding.EXTENDED;

	/**
	 * 
	 * @param data
	 *            Float data.
	 */
	public Data( Float[] data ) 
	{
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
        return ( dataEncoding.equals( DataEncoding.SIMPLE ) ) ? simpleEncoding() : extendedEncoding();
    }
	
    /**
     * @return
     */
    private String extendedEncoding()
    {
        if ( data == null || data.length == 0 )
            return "";
        String encodedData = "";
        for ( Iterator it = Arrays.asList( data ).iterator(); it.hasNext(); ) 
        {
            Float datum = (Float)it.next();
            int index = ( datum.floatValue() >= 0f && datum.floatValue() <= 100f ) ? Math.round( ( datum.floatValue() / 100f ) * ( extendedEncoding.length - 1 ) ) : -1;
            encodedData += ( index < 0 || index > ( extendedEncoding.length - 1 ) ) ? "__" : extendedEncoding[ index ];
        }
        return encodedData;
    }
    
    /**
     * 
     * @return
     */
    private String simpleEncoding()
    {
        if (data == null || data.length == 0)
            return "";
        String encodedData = "";
        for ( Iterator it = Arrays.asList( data ).iterator(); it.hasNext(); ) 
        {
            Float datum = (Float)it.next();
            int index = ( datum.floatValue() >= 0f && datum.floatValue() <= 100f ) ? Math.round( ( datum.floatValue() / 100f ) * ( simpleEncoding.length - 1 ) ) : -1;
            encodedData += ( index < 0 || index > ( simpleEncoding.length - 1 ) ) ? "_" : simpleEncoding[ index ];
        }
        return encodedData;
    }
    
    /**
     * @param dataEncoding
     */
    final public void setDataEncoding( final DataEncoding dataEncoding )
    {
        this.dataEncoding = dataEncoding;
    }
	
	/**
	 * Get the size of the dataset.
	 * 
	 * @return Size of the dataset. 
	 */
	public int getSize()
	{
		return data.length;
	}
}