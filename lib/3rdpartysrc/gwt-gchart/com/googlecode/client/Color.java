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
 * RGB Color. There are many predefined colors defined herein (e.g. Color.AQUA).
 * You can also construct a color with the usual hexdecimal notation (e.g.
 * F0F8FF)
 * 
 * @author Julien Chastang
 */
public class Color {

	public static final Color ALICEBLUE = new Color("F0F8FF");
	public static final Color ANTIQUEWHITE = new Color("FAEBD7");
	public static final Color AQUA = new Color("00FFFF");
	public static final Color AQUAMARINE = new Color("7FFFD4");
	public static final Color AZURE = new Color("F0FFFF");
	public static final Color BEIGE = new Color("F5F5DC");
	public static final Color BISQUE = new Color("FFE4C4");
	public static final Color BLACK = new Color("000000");
	public static final Color BLANCHEDALMOND = new Color("FFEBCD");
	public static final Color BLUE = new Color("0000FF");
	public static final Color BLUEVIOLET = new Color("8A2BE2");
	public static final Color BROWN = new Color("A52A2A");
	public static final Color BURLYWOOD = new Color("DEB887");
	public static final Color CADETBLUE = new Color("5F9EA0");
	public static final Color CHARTREUSE = new Color("7FFF00");
	public static final Color CHOCOLATE = new Color("D2691E");
	public static final Color CORAL = new Color("FF7F50");
	public static final Color CORNFLOWERBLUE = new Color("6495ED");
	public static final Color CORNSILK = new Color("FFF8DC");
	public static final Color CRIMSON = new Color("DC143C");
	public static final Color CYAN = new Color("00FFFF");
	public static final Color DARKBLUE = new Color("00008B");
	public static final Color DARKCYAN = new Color("008B8B");
	public static final Color DARKGOLDENROD = new Color("B8860B");
	public static final Color DARKGRAY = new Color("A9A9A9");
	public static final Color DARKGREEN = new Color("006400");
	public static final Color DARKKHAKI = new Color("BDB76B");
	public static final Color DARKMAGENTA = new Color("8B008B");
	public static final Color DARKOLIVEGREEN = new Color("556B2F");
	public static final Color DARKORANGE = new Color("FF8C00");
	public static final Color DARKORCHID = new Color("9932CC");
	public static final Color DARKRED = new Color("8B0000");
	public static final Color DARKSALMON = new Color("E9967A");
	public static final Color DARKSEAGREEN = new Color("8FBC8F");
	public static final Color DARKSLATEBLUE = new Color("483D8B");
	public static final Color DARKSLATEGRAY = new Color("2F4F4F");
	public static final Color DARKTURQUOISE = new Color("00CED1");
	public static final Color DARKVIOLET = new Color("9400D3");
	public static final Color DEEPPINK = new Color("FF1493");
	public static final Color DEEPSKYBLUE = new Color("00BFFF");
	public static final Color DIMGRAY = new Color("696969");
	public static final Color DODGERBLUE = new Color("1E90FF");
	public static final Color FIREBRICK = new Color("B22222");
	public static final Color FLORALWHITE = new Color("FFFAF0");
	public static final Color FORESTGREEN = new Color("228B22");
	public static final Color FUCHSIA = new Color("FF00FF");
	public static final Color GAINSBORO = new Color("DCDCDC");
	public static final Color GHOSTWHITE = new Color("F8F8FF");
	public static final Color GOLD = new Color("FFD700");
	public static final Color GOLDENROD = new Color("DAA520");
	public static final Color GRAY = new Color("808080");
	public static final Color GREEN = new Color("008000");
	public static final Color GREENYELLOW = new Color("ADFF2F");
	public static final Color HONEYDEW = new Color("F0FFF0");
	public static final Color HOTPINK = new Color("FF69B4");
	public static final Color INDIANRED = new Color("CD5C5C");
	public static final Color INDIGO = new Color("4B0082");
	public static final Color IVORY = new Color("FFFFF0");
	public static final Color KHAKI = new Color("F0E68C");
	public static final Color LAVENDER = new Color("E6E6FA");
	public static final Color LAVENDERBLUSH = new Color("FFF0F5");
	public static final Color LAWNGREEN = new Color("7CFC00");
	public static final Color LEMONCHIFFON = new Color("FFFACD");
	public static final Color LIGHTBLUE = new Color("ADD8E6");
	public static final Color LIGHTCORAL = new Color("F08080");
	public static final Color LIGHTCYAN = new Color("E0FFFF");
	public static final Color LIGHTGOLDENRODYELLOW = new Color("FAFAD2");
	public static final Color LIGHTGREEN = new Color("90EE90");
	public static final Color LIGHTGREY = new Color("D3D3D3");
	public static final Color LIGHTPINK = new Color("FFB6C1");
	public static final Color LIGHTSALMON = new Color("FFA07A");
	public static final Color LIGHTSEAGREEN = new Color("20B2AA");
	public static final Color LIGHTSKYBLUE = new Color("87CEFA");
	public static final Color LIGHTSLATEGRAY = new Color("778899");
	public static final Color LIGHTSTEELBLUE = new Color("B0C4DE");
	public static final Color LIGHTYELLOW = new Color("FFFFE0");
	public static final Color LIME = new Color("00FF00");
	public static final Color LIMEGREEN = new Color("32CD32");
	public static final Color LINEN = new Color("FAF0E6");
	public static final Color MAGENTA = new Color("FF00FF");
	public static final Color MAROON = new Color("800000");
	public static final Color MEDIUMAQUAMARINE = new Color("66CDAA");
	public static final Color MEDIUMBLUE = new Color("0000CD");
	public static final Color MEDIUMORCHID = new Color("BA55D3");
	public static final Color MEDIUMPURPLE = new Color("9370DB");
	public static final Color MEDIUMSEAGREEN = new Color("3CB371");
	public static final Color MEDIUMSLATEBLUE = new Color("7B68EE");
	public static final Color MEDIUMSPRINGGREEN = new Color("00FA9A");
	public static final Color MEDIUMTURQUOISE = new Color("48D1CC");
	public static final Color MEDIUMVIOLETRED = new Color("C71585");
	public static final Color MIDNIGHTBLUE = new Color("191970");
	public static final Color MINTCREAM = new Color("F5FFFA");
	public static final Color MISTYROSE = new Color("FFE4E1");
	public static final Color MOCCASIN = new Color("FFE4B5");
	public static final Color NAVAJOWHITE = new Color("FFDEAD");
	public static final Color NAVY = new Color("000080");
	public static final Color OLDLACE = new Color("FDF5E6");
	public static final Color OLIVE = new Color("808000");
	public static final Color OLIVEDRAB = new Color("6B8E23");
	public static final Color ORANGE = new Color("FFA500");
	public static final Color ORANGERED = new Color("FF4500");
	public static final Color ORCHID = new Color("DA70D6");
	public static final Color PALEGOLDENROD = new Color("EEE8AA");
	public static final Color PALEGREEN = new Color("98FB98");
	public static final Color PALETURQUOISE = new Color("AFEEEE");
	public static final Color PALEVIOLETRED = new Color("DB7093");
	public static final Color PAPAYAWHIP = new Color("FFEFD5");
	public static final Color PEACHPUFF = new Color("FFDAB9");
	public static final Color PERU = new Color("CD853F");
	public static final Color PINK = new Color("FFC0CB");
	public static final Color PLUM = new Color("DDA0DD");
	public static final Color POWDERBLUE = new Color("B0E0E6");
	public static final Color PURPLE = new Color("800080");
	public static final Color RED = new Color("FF0000");
	public static final Color ROSYBROWN = new Color("BC8F8F");
	public static final Color ROYALBLUE = new Color("4169E1");
	public static final Color SADDLEBROWN = new Color("8B4513");
	public static final Color SALMON = new Color("FA8072");
	public static final Color SANDYBROWN = new Color("F4A460");
	public static final Color SEAGREEN = new Color("2E8B57");
	public static final Color SEASHELL = new Color("FFF5EE");
	public static final Color SIENNA = new Color("A0522D");
	public static final Color SILVER = new Color("C0C0C0");
	public static final Color SKYBLUE = new Color("87CEEB");
	public static final Color SLATEBLUE = new Color("6A5ACD");
	public static final Color SLATEGRAY = new Color("708090");
	public static final Color SNOW = new Color("FFFAFA");
	public static final Color SPRINGGREEN = new Color("00FF7F");
	public static final Color STEELBLUE = new Color("4682B4");
	public static final Color TAN = new Color("D2B48C");
	public static final Color TEAL = new Color("008080");
	public static final Color THISTLE = new Color("D8BFD8");
	public static final Color TOMATO = new Color("FF6347");
	public static final Color TURQUOISE = new Color("40E0D0");
	public static final Color VIOLET = new Color("EE82EE");
	public static final Color WHEAT = new Color("F5DEB3");
	public static final Color WHITE = new Color("FFFFFF");
	public static final Color WHITESMOKE = new Color("F5F5F5");
	public static final Color YELLOW = new Color("FFFF00");
	public static final Color YELLOWGREEN = new Color("9ACD32");

	private final String color;
	
	private String opacity;

	/**
	 * Pass in an RGB color string from 000000 to FFFFFF
	 * 
	 * @param color
	 *            RGB color in hexidecimal.
	 */
	public Color(final String color) {
		int colorInt;
		try {
			colorInt = Integer.parseInt(color, 16);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(color + " is not a valid color.");
		}
		//Bit shifting 3 bytes should yield 0 for rgb colors.
		if ((colorInt >> 24) != 0){
			throw new IllegalArgumentException(color + " is not a valid color.");
		}
		if (color.length() != 6){
			throw new IllegalArgumentException(color + " is not a valid color. (Must be 6 charcters long.)");
		}
		this.color = color.toUpperCase();
	}
	
	/**
	 * You can optionally specify transparency by appending a value between 00
	 * and FF where 00 is completely transparent and FF completely opaque.
	 * 
	 * @param opacity
	 *            00 is completely transparent and FF completely opaque
	 */
	public void setOpacity(final String opacity){
		int opacityInt;
		try {
			opacityInt = Integer.parseInt(opacity, 16);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(opacity + " is not a valid opacity.");
		}
		//Bit shifting 1 bytes should yield 0 for opacity.
		if ((opacityInt >> 8) != 0){
			throw new IllegalArgumentException(opacity + " is not a valid opacity.");
		}
		if (opacity.length() != 2){
			throw new IllegalArgumentException(opacity + " is not a valid opacity. (Must be 2 charcters long.)");
		}
		this.opacity = opacity.toUpperCase();
	}

	public String toString() {
		return color +  ((opacity != null) ? opacity : "");
	}	
}