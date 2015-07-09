package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActorState;

public interface IConstTransitionState
{
//	Vector2f getParentSize();
	IActorState getTargetActorState();
	
	int getTransitionTime();
	
	boolean isOutTransition();
	
	boolean isTransitionAdvancing();
}
