/**
 * 
 */
package com.googlecode.client;

import com.google.gwt.user.client.ui.HTML;

/**
 * @author Silvin Lubecki
 *
 */
public class ChartWidget extends HTML
{
    ChartWidget( final GChart chart )
    {
        setHTML( "<span><img src='" + chart.createURLString() + "'/></span>" ); 
    }
}
