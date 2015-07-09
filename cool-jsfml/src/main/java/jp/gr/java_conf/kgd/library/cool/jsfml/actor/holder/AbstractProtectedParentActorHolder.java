package jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder;

import java.util.Collection;
import java.util.LinkedHashSet;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/***
 * 内部アクターに対して前面と背面の修飾が可能なホルダーを作るための抽象クラスです。
 * @author taisa
 *
 */
public abstract class AbstractProtectedParentActorHolder
extends AbstractProtectedActorHolder
implements IActor
{
	private Collection<IActor> backChildren = new LinkedHashSet<>();
	private Collection<IActor> frontChildren = new LinkedHashSet<>();
	
	//
	protected AbstractProtectedParentActorHolder()
	{
	}

	//
	protected Collection<IActor> getBackChildren()
	{
//		if(backChildren == null)
//		{
//			backChildren = new LinkedHashSet<>();
//		}
		
		return backChildren;
	}
	
	protected boolean addBackChild(IActor actor)
	{
		return backChildren.add(actor);
	}
	
	protected Collection<IActor> getFrontChildren()
	{
//		if(frontChildren == null)
//		{
//			frontChildren = new LinkedHashSet<>();
//		}
		
		return frontChildren;
	}
	
	protected boolean addFrontChild(IActor actor)
	{
		return frontChildren.add(actor);
	}

	@Override
	protected void onUpdateChildren()
	{
		for(IActor actor : backChildren)
		{
			actor.update();
		}
		

		{
			if(getInnerActor() != null)
			{
				onUpdateInnerActor();
			}
		}
		
		
		for(IActor actor : frontChildren)
		{
			actor.update();
		}
	}
	
	@Override
	protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		for(IActor actor : backChildren)
		{
			actor.draw(target, states, mask);
		}
		
		
		{
			IActor innerActor = getInnerActor();
			if(innerActor != null)
			{
//				innerActor.draw(target, states, mask);
				onDrawInnerActor(target, states, mask);
			}
		}
		
		
		for(IActor actor : frontChildren)
		{
			actor.draw(target, states, mask);
		}
	}

	//
}
