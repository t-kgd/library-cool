package jp.gr.java_conf.kgd.library.cool.jsfml.actor.container;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;

public abstract class AbstractActorContainer
extends AbstractProtectedActorContainer
implements IActorContainer
{

	//
	protected AbstractActorContainer()
	{
	}
	
	
	@Override
	public Collection<IActor> getActors()
	{
		return super.getChildren();
	}
	
	@Override
	public boolean addActor(IActor actor)
	{
		return getActors().add(actor);
	}


	@Override
	public boolean removeActor(Object key)
	{
		return getActors().remove(key);
	}
}
