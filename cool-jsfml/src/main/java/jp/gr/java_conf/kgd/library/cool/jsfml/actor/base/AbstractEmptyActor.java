package jp.gr.java_conf.kgd.library.cool.jsfml.actor.base;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

public abstract class AbstractEmptyActor
extends AbstractParentActor
{
	protected AbstractEmptyActor()
	{
	}

	@Override
	final protected void onUpdateChildren()
	{
	}

	@Override
	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
	}
}
