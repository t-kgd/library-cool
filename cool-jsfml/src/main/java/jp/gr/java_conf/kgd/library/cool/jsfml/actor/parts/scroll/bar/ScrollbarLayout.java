package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.IShapeActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.RectangleActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.IShapeState;

public class ScrollbarLayout
extends AbstractParentActor
implements IScrollbarLayout
{
	private ScrollbarController scrollbarController = new ScrollbarController();

	private float sliderWidthRate = 1;
	private IShapeActor slider = new RectangleActor();

	//
	private boolean isSliderNeedsResize = true;
	private boolean isSliderNeedsRelocate = true;
	
	//
	public ScrollbarLayout()
	{
//		addActor(slider);
	}
	
	//
	@Override
	public float getSliderWidthRate()
	{
		return sliderWidthRate;
	}
	
	@Override
	public void setSliderWidthRate(float rate)
	{
		this.sliderWidthRate = rate;
		
		isSliderNeedsRelocate = true;
	}
	
	@Override
	public IShapeState getSliderShapeState()
	{
		return slider;
	}
	
	//
	@Override
	public void setSize(Vector2f size)
	{
		super.setSize(size);
		
		isSliderNeedsResize = true;
	}
	
	//
	@Override
	protected void onUpdate()
	{	
		if(isSliderNeedsResize)
		{
			isSliderNeedsResize = false;
			resizeSlider();
			
			isSliderNeedsRelocate = true;
		}
		
		if(isSliderNeedsRelocate)
		{
			isSliderNeedsRelocate = false;
			relocateSlider();
		}
	}
	
	@Override
	protected void onUpdateChildren()
	{
		slider.update();
	}
	
	@Override
	protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		slider.draw(target, states, mask);
	}

	
	//
	private final void resizeSlider()
	{
		Vector2f backSize = getSize();
		float lengthRate = getSliderLengthRate();
				
		if(isVertical())
		{
			slider.setSize(backSize.x, backSize.y * lengthRate);
		}
		else
		{
			slider.setSize(backSize.x * lengthRate, backSize.y);
		}
	}
	
	private final void relocateSlider()
	{
		Vector2f backSize = getSize();
		
		Vector2f sliderSize = slider.getSize();
		float positionRate = getSliderPositionRate();
		
		slider.setOrigin(Vector2f.ZERO);
		
		if(isVertical())
		{
			slider.setPosition(0, (backSize.y - sliderSize.y) * positionRate);
			
			slider.setScale(getSliderWidthRate(), 1);
		}
		else
		{
			slider.setPosition((backSize.x - sliderSize.x) * positionRate, 0);
			
			slider.setScale(1, getSliderWidthRate());
		}
		
		ComponentHelper.setOriginToCenterAndAdjustMove(slider);
	}
	
	//
	public float getSliderPositionRate()
	{
		return scrollbarController.getSliderPositionRate();
	}

	public float getSliderLengthRate()
	{
		return scrollbarController.getSliderLengthRate();
	}

	public void setSliderPositionRate(float rate)
	{
		scrollbarController.setSliderPositionRate(rate);
		
		isSliderNeedsRelocate = true;
	}

	public void setSliderLengthRate(float rate)
	{
		scrollbarController.setSliderLengthRate(rate);
		
		isSliderNeedsResize = true;
	}

	public boolean isVertical()
	{
		return scrollbarController.isVertical();
	}

	public void setVertical(boolean flag)
	{
		scrollbarController.setVertical(flag);
		
		isSliderNeedsResize = true;
	}
}
