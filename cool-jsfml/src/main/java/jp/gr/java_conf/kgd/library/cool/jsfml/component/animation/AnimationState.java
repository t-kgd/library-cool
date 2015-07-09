package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class AnimationState
implements IAnimationState
{
	private Set<DrawStateElement> enableBits = EnumSet.noneOf(DrawStateElement.class);
	private Map<DrawStateElement, Float> speedRates = new EnumMap<>(DrawStateElement.class);

	public AnimationState()
	{
		for(DrawStateElement element : DrawStateElement.values())
		{			
			speedRates.put(element, 1.0f);
		}
	}
	
	@Override
	public boolean isEnabled(DrawStateElement element)
	{
		return enableBits.contains(element);
	}

	@Override
	public float getSpeedRate(DrawStateElement element)
	{
		return speedRates.get(element);
	}

	@Override
	public void setEnabled(DrawStateElement element, boolean flag)
	{
		if(flag)
		{
			enableBits.add(element);
		}
		else
		{
			enableBits.remove(element);
		}
	}
	
	@Override
	public void clearEnabled()
	{
		enableBits.clear();
	}

	@Override
	public void setSpeedRate(DrawStateElement element, float rate)
	{
		speedRates.put(element, MathHelper.minMax(0, rate, 1));
	}
}
