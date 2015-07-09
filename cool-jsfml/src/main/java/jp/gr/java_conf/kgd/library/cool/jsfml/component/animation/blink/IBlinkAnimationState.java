package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink;

public interface IBlinkAnimationState
extends IConstBlinkAnimationState
{
	void setBlinkEnabled(boolean flag);
	void setBlinkSpeedRate(float rate);
	
	void setBlinkUsedAlpha(boolean flag);
	
	void setBlinkFactor(float factor, int sign);
}
