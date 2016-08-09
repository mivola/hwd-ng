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

import static com.googlecode.charts4j.collect.Preconditions.checkArgument;
import static com.googlecode.charts4j.collect.Preconditions.checkNotNull;

import com.googlecode.charts4j.parameters.FillType;
import com.googlecode.charts4j.parameters.SolidFillType;

/**
 * Abstract type for all charts that support legend positions. (MapChart,
 * GoogleOMeter and PieChart do not support legend positions.)
 * 
 * @author Julien Chastang (julien.c.chastang at gmail dot com)
 * 
 */
public abstract class AbstractGraphChart extends AbstractGChart implements GraphChart, TitledChart {

    /** Chart title. * */
    private ChartTitle chartTitle;

    /** Chart area fill. * */
    private Fill areaFill;

    /** The legend position which may be top, bottom, right, or left. * */
    private LegendPosition legendPosition;

    /**
     * Abstract graph chart constructor.
     */
    AbstractGraphChart() {
	super();
    }

    /**
     * {@inheritDoc}
     */
    public final void setTitle(final String title) {
	checkNotNull(title, "Title cannot be null.");
	this.chartTitle = new ChartTitle(title);
    }

    /**
     * {@inheritDoc}
     */
    public final void setTitle(final String title, final Color color, final int fontSize) {
	checkArgument(fontSize > 0, "font size must be > 0: %s", fontSize);
	checkNotNull(title, "Title cannot be null.");
	checkNotNull(color, "Color cannot be null.");
	this.chartTitle = new ChartTitle(title, color, fontSize);
    }

    /**
     * {@inheritDoc}
     */
    public final void setLegendPosition(final LegendPosition legendPosition) {
	checkNotNull(legendPosition, "Legend position cannot be null.");
	this.legendPosition = legendPosition;
    }

    /**
     * {@inheritDoc}
     */
    public final void setAreaFill(final Fill fill) {

	// try {
	// final Constructor<? extends Fill> constructor =
	// fill.getClass().getDeclaredConstructor(fill.getClass());
	// areaFill = constructor.newInstance(fill);
	// } catch (NoSuchMethodException e) {
	// throw new IllegalArgumentException("Problem accessing copy
	// constrcutor for " + fill.getClass().getName());
	// } catch (InstantiationException e) {
	// throw new IllegalArgumentException("Problem accessing copy
	// constrcutor for " + fill.getClass().getName());
	// } catch (IllegalAccessException e) {
	// throw new IllegalArgumentException("Problem accessing copy
	// constrcutor for " + fill.getClass().getName());
	// } catch (InvocationTargetException e) {
	// throw new IllegalArgumentException("Problem accessing copy
	// constrcutor for " + fill.getClass().getName());
	// }

	areaFill = fill;

	// MV
	System.out.println("setareaFill");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareData() {
	super.prepareData();
	if (areaFill instanceof SolidFill) {
	    final SolidFill solid = (SolidFill) areaFill;
	    parameterManager.addSolidFill(SolidFillType.CHART_AREA, solid.getColor());
	} else if (areaFill instanceof LinearGradientFill) {
	    final LinearGradientFill lg = (LinearGradientFill) areaFill;
	    parameterManager.addLinearGradientFill(FillType.CHART_AREA, lg.getAngle(), lg.getColorsAndOffsets());
	} else if (areaFill instanceof LinearStripesFill) {
	    final LinearStripesFill ls = (LinearStripesFill) areaFill;
	    parameterManager.addLinearStripeFill(FillType.CHART_AREA, ls.getAngle(), ls.getColorsAndWidths());
	}
	if (chartTitle != null) {
	    parameterManager.setChartTitleParameter(chartTitle.getTitle());
	}
	if (chartTitle != null && chartTitle.getColor() != null) {
	    parameterManager.setChartTitleColorAndSizeParameter(chartTitle.getColor(), chartTitle.getFontSize());
	}
	if (legendPosition != null) {
	    parameterManager.setLegendPositionParameter(legendPosition);
	}
    }
}
