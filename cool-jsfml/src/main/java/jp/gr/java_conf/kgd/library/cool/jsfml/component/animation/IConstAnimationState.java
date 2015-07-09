package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;

public interface IConstAnimationState
{	
	boolean isEnabled(DrawStateElement element);	 
	
	float getSpeedRate(DrawStateElement element); 
}
