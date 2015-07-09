package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.Color;

public class DrawState
extends AffineState
implements IDrawState
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5853834695980822455L;

	//委譲
	private ColorMaskState maskColorState = new ColorMaskState();

	//
//	private boolean isVisible = true;
	
	//
//	@Override
//	public boolean isVisible()
//	{
//		return this.isVisible;
//	}
//	
//	@Override
//	public void setVisible(boolean flag)
//	{
//		this.isVisible = flag;
//	}
	
	//委譲
	public Color getColorMask()
	{
		return maskColorState.getColorMask();
	}

	public float getAlphaMaskRate()
	{
		return maskColorState.getAlphaMaskRate();
	}

	public void setColorMask(Color color)
	{
		maskColorState.setColorMask(color);
	}

	public void setAlphaMaskRate(float rate)
	{
		maskColorState.setAlphaMaskRate(rate);
	}
}
