package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.base.ColorHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IDrawState;

public class ShakeAnimationManager
extends ShakeAnimationController
implements IShakeAnimationManager
{

	@Override
	public void applyAnimation(IDrawState state)
	{
		DrawStateElement element;
		int timer;
		
		element = DrawStateElement.ROTATION;
		timer = getShakeTimer(element);
		if(isEnabled(element) && isTimerEnabled(timer))
		{
			float shake = getRotation();
			float factor = getCurrentShakeFactor(element);
			
			float current = state.getRotation();
					
			state.setRotation(current + shake * factor); 
		}
		
		element = DrawStateElement.SCALE;
		timer = getShakeTimer(element);
		if(isEnabled(element) && isTimerEnabled(timer))
		{
			Vector2f shake = getScale();
			float factor = getCurrentShakeFactor(element);
			
			Vector2f current = state.getScale();
					
			state.setScale(current.x + shake.x * factor, current.y + shake.y * factor);
		}
		
		element = DrawStateElement.POSITION;
		timer = getShakeTimer(element);
		if(isEnabled(element) && isTimerEnabled(timer))
		{
			Vector2f shake = getPosition();
			float factor = getCurrentShakeFactor(element);
			
			Vector2f current = state.getPosition();
					
			state.setPosition(current.x + shake.x * factor, current.y + shake.y * factor);
		}
		
		element = DrawStateElement.COLOR_MASK;
		timer = getShakeTimer(element);
		if(isEnabled(element) && isTimerEnabled(timer))
		{
			Color shake = getColorMask();
			float factor = (getCurrentShakeFactor(element) + 1) / 2;
			
			Color current = state.getColorMask();
				
			state.setColorMask(ColorHelper.addRGB(current, shake, -factor));
		}
		
		element = DrawStateElement.ALPHA_MASK;
		timer = getShakeTimer(element);
		if(isEnabled(element) && isTimerEnabled(timer))
		{
			float shake = getAlphaMaskRate();
			float factor = (getCurrentShakeFactor(element) + 1) / 2;
			
			float current = state.getAlphaMaskRate();
					
			state.setAlphaMaskRate(current - shake * factor); 
		}
		
		
		advanceAnimation();
	}
}
