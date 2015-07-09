package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IDrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.IAnimationState;

public interface IShakeAnimationState
extends IConstShakeAnimationState, IAnimationState, IDrawState
{
	void setShakeFactor(DrawStateElement element, float factor, int sign);
	void synchronizeShakeFactor(float factor, int sign);
	
	void setShakeTimer(DrawStateElement element, int time);
	void clearShakeTimer();
}
