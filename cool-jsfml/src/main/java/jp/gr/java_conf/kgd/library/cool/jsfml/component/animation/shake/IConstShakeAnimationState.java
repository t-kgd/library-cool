package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.jsfml.graphics.Color;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstDrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ImmutableDrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.IConstAnimationState;

public interface IConstShakeAnimationState
extends IConstAnimationState, IConstDrawState
{	
	Map<DrawStateElement, Float> DEFAULT_SHAKE_SPEED_RATES = Collections.unmodifiableMap(new DefaultShakeSpeedRates());
	ImmutableDrawState DEFAULT_SHAKE_DRAW_STATE = new ImmutableDrawState(new DefaultShakeDrawState());

	int TIMER_STOP = 0;
	int TIMER_CONTINUE = -1;
	
	//
	float getCurrentShakeFactor(DrawStateElement element);
	int getCurrentShakeSign(DrawStateElement element);
	
	int getShakeTimer(DrawStateElement element);
}

class DefaultShakeSpeedRates
extends EnumMap<DrawStateElement, Float>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultShakeSpeedRates()
	{
		super(DrawStateElement.class);
		
		put(DrawStateElement.ROTATION, 1.0f / 30);
		
		put(DrawStateElement.SCALE, 1.0f / 60);
		
		put(DrawStateElement.POSITION, 1.0f / 15);
		
		put(DrawStateElement.COLOR_MASK, 1.0f / 4);
		
		put(DrawStateElement.ALPHA_MASK, 1.0f / 4);
	}
}

class DefaultShakeDrawState
extends DrawState
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2494078363587722885L;

	public DefaultShakeDrawState()
	{
		setRotation(2);
		
		setScale(1.0f / 32, 1.0f / 32);
		
		setPosition(2, 0);
		
		setColorMask(Color.WHITE);
		
		setAlphaMaskRate(1);
	}
}
