package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.free;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder.AbstractSimpleActorHolder;

public class FreeHolderLayout
extends AbstractSimpleActorHolder
implements IFreeHolderLayout
{
	@Override
	protected void onUpdate()
	{
	}
	
	@Override
	protected void onUpdateInnerActor()
	{
		getInnerActor().update();
	}
}
