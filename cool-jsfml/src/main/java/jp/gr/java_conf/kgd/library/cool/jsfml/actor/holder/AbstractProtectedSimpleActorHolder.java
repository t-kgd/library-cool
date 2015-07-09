package jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public abstract class AbstractProtectedSimpleActorHolder
extends AbstractProtectedActorHolder
{
	protected AbstractProtectedSimpleActorHolder()
	{
	}
	
	//
	@Override
	final protected void onUpdateChildren()
	{
		if(getInnerActor() != null)
		{
			onUpdateInnerActor();
		}
	}

	@Override
	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		IActor innerActor = getInnerActor();
		
		if(innerActor != null)
		{
//			innerActor.draw(target, states, mask);
			onDrawInnerActor(target, states, mask);
		}
	}
	
	//
}
