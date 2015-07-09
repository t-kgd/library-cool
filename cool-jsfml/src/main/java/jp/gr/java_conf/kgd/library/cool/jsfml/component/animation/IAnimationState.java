package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;

public interface IAnimationState
extends IConstAnimationState
{
	void setEnabled(DrawStateElement element, boolean flag);
	void clearEnabled();
	
	void setSpeedRate(DrawStateElement element, float rate);
}
