package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.Color;

public class ConnectedColorMaskStates
implements IColorMaskState
{	
	private IColorMaskState[] colorMaskStates;
	
	//	
	public ConnectedColorMaskStates(IColorMaskState... colorMaskStates)
	{
		this.colorMaskStates = colorMaskStates;
	}
	
	public Color getColorMask()
	{
		return colorMaskStates[0].getColorMask();
	}
	public float getAlphaMaskRate()
	{
		return colorMaskStates[0].getAlphaMaskRate();
	}
	public void setColorMask(Color color)
	{
		for(IColorMaskState colorMaskState : colorMaskStates)
		{
			colorMaskState.setColorMask(color);
		}
	}
	public void setAlphaMaskRate(float rate)
	{
		for(IColorMaskState colorMaskState : colorMaskStates)
		{
			colorMaskState.setAlphaMaskRate(rate);
		}
	}
}
