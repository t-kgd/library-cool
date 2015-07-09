package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActorState;

public interface ITransitionState
extends IConstTransitionState
{
//	void setParentSize(Vector2f size);
	void setTargetActorState(IActorState actorState);
	
	void setTransitionTime(int time);
	
	void setOutTransition(boolean flag);
}
