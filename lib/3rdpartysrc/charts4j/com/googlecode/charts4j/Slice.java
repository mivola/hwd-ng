/**
 *
 * The MIT License
 *
 * Copyright (c) 2008 the original author or authors.
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

package com.googlecode.charts4j;

import static com.googlecode.charts4j.collect.Preconditions.*;

/**
 * Slice of pie for a {@link PieChart}. If the number of colors specified is
 * less than the number of slices, then colors are interpolated. If no colors
 * are specified, pie segment colors are interpolated from dark orange to pale
 * yellow.
 *
 * @author Julien Chastang (julien.c.chastang at gmail dot com)
 *
 * @see PieChart
 * @see GCharts
 */
public class Slice {

    /** Percent of pie. **/
    private final int    percent;

    /** Label for pie slice. **/
    private final String label;

    /** Color for pie slice. **/
    private final Color  color;

    /**
     * @see #newSlice(int, Color, String)
     */
    Slice(final int percent, final Color color, final String sliceLabel) {
        this.percent = percent;
        this.color = color;
        this.label = sliceLabel;
    }

    /**
     * Get the percentage for this slice of the pie.
     *
     * @return percentage that this slice will take of the pie.
     */
    public final int getPercentage() {
        return percent;
    }

    /**
     * Get the color for this slice of the pie.
     *
     * @return color of pie slice.
     *
     */
    public final Color getColor() {
        return color;
    }

    /**
     * Get the pie slice label.
     *
     * @return label of pie slice.
     */
    public final String getLabel() {
        return label;
    }

    /**
     * Create a pie slice.
     *
     * @param percent
     *            percent of pie. Must be between 0 to 100.
     * @param color
     *            color of slice. Cannot be null
     * @param sliceLabel
     *            label associated with slice. Cannot be null.
     * @return a slice of pie.
     */
    public static Slice newSlice(final int percent, final Color color, final String sliceLabel) {
        checkArgument(percent >= 0 && percent <= 100, "percent must be between 0 and 100: %s", percent);
        checkNotNull(color, "Color cannot be null");
        checkNotNull(sliceLabel, "Slice label cannot be null");
        return new Slice(percent, color, sliceLabel);
    }

    /**
     * Create a pie slice.
     *
     * @param percent
     *            percent of pie. Must be between 0 to 100.
     * @param color
     *            color of slice. Cannot be null.
     * @return a slice of pie.
     */
    public static Slice newSlice(final int percent, final Color color) {
        checkArgument(percent >= 0 && percent <= 100, "percent must be between 0 and 100: %s", percent);
        checkNotNull(color, "Color cannot be null");
        return new Slice(percent, color, null);
    }


    /**
     * Create a pie slice.
     *
     * @param percent
     *            percent of pie. Must be between 0 to 100.
     * @param sliceLabel
     *            label associated with slice. Cannot be null.
     * @return a slice of pie.
     */
    public static Slice newSlice(final int percent, final String sliceLabel) {
        checkArgument(percent >= 0 && percent <= 100, "percent must be between 0 and 100: %s", percent);
        checkNotNull(sliceLabel, "Slice label cannot be null");
        return new Slice(percent, null, sliceLabel);
    }
}
