package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink;

public interface IConstBlinkAnimationState
{
	//
	boolean isBlinkEnabled();
	float getBlinkSpeedRate();
	
	boolean isBlinkUsedAlpha();
	
	float getCurrentBlinkFactor();	
	int getCurrentBlinkSign();
}
