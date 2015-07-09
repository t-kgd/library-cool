package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar;

import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class ScrollbarController
implements IScrollbarController
{
	private boolean isVertical = true;
	
	private float sliderPositionRate = 0;
	private float sliderLengthRate = 1;
	
	//
	@Override
	public boolean isVertical()
	{
		return isVertical;
	}
	
	@Override
	public float getSliderPositionRate()
	{
		return sliderPositionRate;
	}

	@Override
	public float getSliderLengthRate()
	{
		return sliderLengthRate;
	}

	//
	@Override
	public void setVertical(boolean flag)
	{
		this.isVertical = flag;
	}
	
	@Override
	public void setSliderPositionRate(float rate)
	{
		this.sliderPositionRate = MathHelper.minMax(0, rate, 1);
	}

	@Override
	public void setSliderLengthRate(float rate)
	{
		this.sliderLengthRate = MathHelper.minMax(0, rate, 1);
	}
}
