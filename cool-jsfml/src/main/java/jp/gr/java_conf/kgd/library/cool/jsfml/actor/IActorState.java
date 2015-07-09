package jp.gr.java_conf.kgd.library.cool.jsfml.actor;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponentState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink.IBlinkShapeState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake.IShakeAnimationState;

public interface IActorState
extends IComponentState, IActiveState, IConstActorState
{
	void setAnimationEnabled(boolean flag);
	
//	ISnapAnimationState getSnapAnimationState();
	IShakeAnimationState getShakeAnimationState();

	IBlinkShapeState getMaskShapeState();
}
