package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink;

import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class BlinkAnimationController
implements IBlinkAnimationState, IBlinkAnimationController
{
	public static final float DEFAULT_BLINK_SPEED_RATE = 1.0f / 4;
	public static final boolean DEFAULT_BLINK_IS_USED_ALPHA = true;
	//激しく点滅
	
	//
	private boolean isBlinkEnabled = false;
	private float blinkSpeedRate = DEFAULT_BLINK_SPEED_RATE;
	
	private boolean isBlinkUsedAlpha = DEFAULT_BLINK_IS_USED_ALPHA;
	
	private float currentBlinkFactor = 0;
	private int currentBlinkSign = 1;
	
	//
	@Override
	public boolean isBlinkEnabled()
	{
		return isBlinkEnabled;
	}
	
	public float getBlinkSpeedRate()
	{
		return blinkSpeedRate;
	}
	
	public void setBlinkSpeedRate(float rate)
	{
		this.blinkSpeedRate = rate;
	}
	
	@Override
	public boolean isBlinkUsedAlpha()
	{
		return isBlinkUsedAlpha;
	}
	
	public float getCurrentBlinkFactor()
	{
		if(isBlinkUsedAlpha)
		{
			return currentBlinkFactor;
		}
		else
		{
			return currentBlinkFactor < 0.5f ? 0 : 1;
		}
	}
	
	public int getCurrentBlinkSign()
	{
		return currentBlinkSign;
	}
	
	@Override
	public void setBlinkEnabled(boolean flag)
	{
		this.isBlinkEnabled = flag;
	}
	
	@Override
	public void setBlinkUsedAlpha(boolean flag)
	{
		this.isBlinkUsedAlpha = flag;
	}
	
	public void setBlinkFactor(float factor, int sign)
	{
		this.currentBlinkFactor = MathHelper.minMax(0, factor, 1);
		
		this.currentBlinkSign = (sign >= 0 ? 1 : -1);
	}
	
	public void advanceAnimation()
	{
		if(!isBlinkEnabled)
		{
			return;
		}
		
		float result = currentBlinkFactor + ((blinkSpeedRate * 2) * currentBlinkSign);
		
		while(result < 0 || 1 < result)
		{
			int sign = (currentBlinkSign > 0 ? 1 : 0);
			
			float diff = sign - result;
			
			result = sign + diff;
			
			currentBlinkSign *= (-1);
		}
		
		currentBlinkFactor = result;
	}
}
