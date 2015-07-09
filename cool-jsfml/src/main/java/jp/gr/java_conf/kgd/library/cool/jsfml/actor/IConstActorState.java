package jp.gr.java_conf.kgd.library.cool.jsfml.actor;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstComponentState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink.IConstBlinkShapeState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake.IConstShakeAnimationState;

public interface IConstActorState
extends IConstComponentState, IConstActiveState
{
	boolean isAnimationEnabled();
	
//	IConstSnapAnimationState getSnapAnimationState();
	IConstShakeAnimationState getShakeAnimationState();

	IConstBlinkShapeState getMaskShapeState();
}
