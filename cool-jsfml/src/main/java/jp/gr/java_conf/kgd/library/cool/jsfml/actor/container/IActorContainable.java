package jp.gr.java_conf.kgd.library.cool.jsfml.actor.container;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;

public interface IActorContainable
{
	Collection<IActor> getActors();
	
	boolean addActor(IActor actor);
	boolean removeActor(Object key);
	
//	boolean addActors(Collection<? extends IActor> actors);
//	boolean removeActors(Collection<? extends Object> keys);
//	
//	void clearActors();
}
