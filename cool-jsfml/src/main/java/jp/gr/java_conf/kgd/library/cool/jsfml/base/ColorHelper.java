package jp.gr.java_conf.kgd.library.cool.jsfml.base;

import org.jsfml.graphics.Color;

public class ColorHelper
{
	protected ColorHelper()
	{
	}
	
	//
	public static Color addAll(Color lhs, Color rhs, float rhsFactor)
	{
		int r = (int)(lhs.r + rhs.r * rhsFactor);
		int g = (int)(lhs.g + rhs.g * rhsFactor);
		int b = (int)(lhs.b + rhs.b * rhsFactor);
		int a = (int)(lhs.a + rhs.a * rhsFactor);
	
		return new Color(r, g, b, a);
	}
	
	public static Color addRGB(Color lhs, Color rhs, float rhsFactor)
	{
		int r = (int)(lhs.r + rhs.r * rhsFactor);
		int g = (int)(lhs.g + rhs.g * rhsFactor);
		int b = (int)(lhs.b + rhs.b * rhsFactor);
	
		return new Color(r, g, b, lhs.a);
	}
}
