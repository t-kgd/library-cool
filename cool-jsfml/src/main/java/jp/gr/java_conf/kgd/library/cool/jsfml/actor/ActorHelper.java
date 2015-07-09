package jp.gr.java_conf.kgd.library.cool.jsfml.actor;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstDimensionState;

public class ActorHelper
{
	protected ActorHelper()
	{
	}
	
	//
	public static final void updateIgnoreAnimation(IActor actor)
	{
		boolean flag = actor.isAnimationEnabled();		
		actor.setAnimationEnabled(false);
		
		actor.update();		
		actor.setAnimationEnabled(flag);
	}
	
	public static final void updateStrictFixInnerActor(IActor inner, IConstDimensionState outer)
	{
		updateStrictFixInnerActor(inner, outer.getSize());
	}
	
	public static final void updateStrictFixInnerActor(IActor inner, Vector2f size)
	{
		ComponentHelper.setStrictFixAndOriginToCenter(inner, size);		
		updateIgnoreAnimation(inner);
	}
}
