package jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;

public interface IActorHolder
extends IActor
{
	IActor getInnerActor();
	void setInnerActor(IActor actor);
}
