package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake;

import java.util.EnumMap;
import java.util.Map;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.AnimationState;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class ShakeAnimationController
extends AnimationState
implements IShakeAnimationController
{
	//委譲
	private DrawState drawState = new DrawState();
	
	//
	private Map<DrawStateElement, Float> factors = new EnumMap<>(DrawStateElement.class);
	private Map<DrawStateElement, Integer> signs = new EnumMap<>(DrawStateElement.class);
	
	private Map<DrawStateElement, Integer> timers = new EnumMap<>(DrawStateElement.class);
	
	//
	public ShakeAnimationController()
	{
		for(DrawStateElement element : DrawStateElement.values())
		{
			setSpeedRate(element, DEFAULT_SHAKE_SPEED_RATES.get(element));
			
			factors.put(element, 0.0f);			
			signs.put(element, 1);
			
			timers.put(element, TIMER_STOP);
		}
		
		ComponentHelper.copyDrawState(this, DEFAULT_SHAKE_DRAW_STATE);
	}
	
	
	//
	@Override
	public float getCurrentShakeFactor(DrawStateElement element)
	{
		return factors.get(element);
	}
	
	@Override
	public int getCurrentShakeSign(DrawStateElement element)
	{
		return signs.get(element);
	}
	
	@Override
	public int getShakeTimer(DrawStateElement element)
	{
		return timers.get(element);
	}
	
	@Override
	public void advanceAnimation()
	{
		for(DrawStateElement element : DrawStateElement.values())
		{
			if(!isEnabled(element))
			{
				continue;
			}
			
			int timer = timers.get(element);
			
			if(isTimerEnabled(timer))
			{
				float current = getCurrentShakeFactor(element);	
				
				float speed = getSpeedRate(element) * 4;		
				int sign = signs.get(element);
				
				
				float result = current + (speed * sign);
				
				while(result < -1 || 1 < result)
				{
					float diff = sign - result;
					
					result = sign + diff;
					
					sign *= (-1);
					signs.put(element, sign);
				}
				
				factors.put(element, result);
				
				//
				setShakeTimer(element, timer - 1);
			}
//			else
//			{
//				setShakeFactor(element, 0, 1);
//			}
		}
	}
	
	protected final boolean isTimerEnabled(int timer)
	{
		return timer != TIMER_STOP;
//		return timer == TIMER_CONTINUE || timer > 0;
	}

	@Override
	public void setShakeFactor(DrawStateElement element, float factor, int sign)
	{
		factors.put(element, MathHelper.minMax(-1, factor, 1));			
		signs.put(element, (sign >= 0 ? 1 : -1));
	}
	
	@Override
	public void synchronizeShakeFactor(float factor, int sign)
	{
		for(DrawStateElement element : DrawStateElement.values())
		{
			setShakeFactor(element, factor, sign);
		}
	}

	@Override
	public void setShakeTimer(DrawStateElement element, int time)
	{
		timers.put(element, Math.max(TIMER_CONTINUE, time));
	}
	
	@Override
	public void clearShakeTimer()
	{
		for(DrawStateElement element : DrawStateElement.values())
		{
			timers.put(element, TIMER_STOP);
		}
	}

	//委譲
	public Color getColorMask()
	{
		return drawState.getColorMask();
	}


	public float getAlphaMaskRate()
	{
		return drawState.getAlphaMaskRate();
	}


	public void setColorMask(Color color)
	{
		drawState.setColorMask(color);
	}


	public void setAlphaMaskRate(float rate)
	{
		drawState.setAlphaMaskRate(rate);
	}


//	public void setVisible(boolean flag)
//	{
//		drawState.setVisible(flag);
//	}


	public final void setPosition(float x, float y)
	{
		drawState.setPosition(x, y);
	}


	public void setPosition(Vector2f v)
	{
		drawState.setPosition(v);
	}


	public void setRotation(float angle)
	{
		drawState.setRotation(angle);
	}


	public final void setScale(float x, float y)
	{
		drawState.setScale(x, y);
	}

	public void setScale(Vector2f factors)
	{
		drawState.setScale(factors);
	}


	public final void setOrigin(float x, float y)
	{
		drawState.setOrigin(x, y);
	}


	public void setOrigin(Vector2f v)
	{
		drawState.setOrigin(v);
	}


	public Vector2f getPosition()
	{
		return drawState.getPosition();
	}


	public float getRotation()
	{
		return drawState.getRotation();
	}


	public Vector2f getScale()
	{
		return drawState.getScale();
	}


	public Vector2f getOrigin()
	{
		return drawState.getOrigin();
	}


	public final void move(float x, float y)
	{
		drawState.move(x, y);
	}


	public void move(Vector2f v)
	{
		drawState.move(v);
	}


	public void rotate(float angle)
	{
		drawState.rotate(angle);
	}


	public final void scale(float x, float y)
	{
		drawState.scale(x, y);
	}


	public void scale(Vector2f factors)
	{
		drawState.scale(factors);
	}


	public Transform getTransform()
	{
		return drawState.getTransform();
	}


	public Transform getInverseTransform()
	{
		return drawState.getInverseTransform();
	}

//	public boolean isVisible()
//	{
//		return drawState.isVisible();
//	}
	//
}
