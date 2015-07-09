package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame.AbstractProtectedFrameWindow;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.IShapeState;

public class Scrollbar
extends AbstractProtectedFrameWindow
implements IScrollbar
{
	private ScrollbarLayout scrollbarBase = new ScrollbarLayout();
	
	public Scrollbar()
	{
		setFrameSize(2);
		setFrameVisible(false);
		getBackgroundColorState().setAlphaMaskRate(0);
		
		setInnerActor(scrollbarBase);
	}

	//
	@Override
	protected void onUpdate()
	{
	}
	
	//
	public IShapeState getSliderShapeState()
	{
		return scrollbarBase.getSliderShapeState();
	}

	public float getSliderPositionRate()
	{
		return scrollbarBase.getSliderPositionRate();
	}

	public float getSliderLengthRate()
	{
		return scrollbarBase.getSliderLengthRate();
	}

	public float getSliderWidthRate()
	{
		return scrollbarBase.getSliderWidthRate();
	}

	public void setSliderPositionRate(float rate)
	{
		scrollbarBase.setSliderPositionRate(rate);
	}

	public void setSliderLengthRate(float rate)
	{
		scrollbarBase.setSliderLengthRate(rate);
	}

	public void setSliderWidthRate(float rate)
	{
		scrollbarBase.setSliderWidthRate(rate);
	}

	public void setVertical(boolean flag)
	{
		scrollbarBase.setVertical(flag);
	}

	public boolean isVertical()
	{
		return scrollbarBase.isVertical();
	}
}
