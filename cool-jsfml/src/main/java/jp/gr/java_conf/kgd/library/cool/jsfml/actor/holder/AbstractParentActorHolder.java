package jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;

public abstract class AbstractParentActorHolder
extends AbstractProtectedParentActorHolder
implements IActorHolder
{
	protected AbstractParentActorHolder()
	{
	}
	
	@Override
	public IActor getInnerActor()
	{
		return super.getInnerActor();
	}

	@Override
	public void setInnerActor(IActor actor)
	{
		super.setInnerActor(actor);
	}
}
