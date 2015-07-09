package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.RectangleComponent;

public class BlinkShapeComponent
extends RectangleComponent
implements IBlinkShapeComponent
{
//	public static final Color DEFAULT_BLINK_COLOR = Color.WHITE;
	public static final Color DEFAULT_BLINK_COLOR = new Color(Color.WHITE, 128);
	public static final float DEFAULT_BLINK_SPEED_RATE = 1.0f / 60;
	
	//
	private BlinkAnimationManager blinkAnimationManager = new BlinkAnimationManager();

//	private DrawState animatedState = new DrawState();	
	private float blinkedAlpha = 1;

	//
	public BlinkShapeComponent()
	{
		setFillColor(DEFAULT_BLINK_COLOR);
		setBlinkSpeedRate(DEFAULT_BLINK_SPEED_RATE);
	}
	
	//
	@Override
	public void advanceAnimation()
	{
//		animatedState.setAlphaMaskRate(getAlphaMaskRate());
//		blinkAnimationManager.applyAnimation(animatedState);
		
		blinkedAlpha = getAlphaMaskRate();
		
		if(!isBlinkEnabled())
		{
			return;
		}
		
		blinkedAlpha *= getCurrentBlinkFactor();
		blinkAnimationManager.advanceAnimation();
	}
	
	//
	@Override
	public void draw(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		if(!isVisible())
		{
			return;
		}
		
		if(isBlinkEnabled())
		{
			float alpha = getAlphaMaskRate();
			setAlphaMaskRate(blinkedAlpha);
		
			super.draw(target, states, mask);
		
			setAlphaMaskRate(alpha);
		}
		else
		{
			super.draw(target, states, mask);
		}
	}
	
	
	//
	public boolean isBlinkEnabled()
	{
		return blinkAnimationManager.isBlinkEnabled();
	}

	public float getBlinkSpeedRate()
	{
		return blinkAnimationManager.getBlinkSpeedRate();
	}

	public void setBlinkSpeedRate(float rate)
	{
		blinkAnimationManager.setBlinkSpeedRate(rate);
	}

	public float getCurrentBlinkFactor()
	{
		return blinkAnimationManager.getCurrentBlinkFactor();
	}

	public int getCurrentBlinkSign()
	{
		return blinkAnimationManager.getCurrentBlinkSign();
	}

	public void setBlinkEnabled(boolean flag)
	{
		blinkAnimationManager.setBlinkEnabled(flag);
	}

	public void setBlinkFactor(float factor, int sign)
	{
		blinkAnimationManager.setBlinkFactor(factor, sign);
	}
	

	//
	public boolean isBlinkUsedAlpha()
	{
		return blinkAnimationManager.isBlinkUsedAlpha();
	}

	public void setBlinkUsedAlpha(boolean flag)
	{
		blinkAnimationManager.setBlinkUsedAlpha(flag);
	}
}
