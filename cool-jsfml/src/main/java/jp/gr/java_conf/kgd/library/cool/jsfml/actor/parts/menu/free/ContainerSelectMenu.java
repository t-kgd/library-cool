package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.free;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.ActorHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.container.IActorContainer;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.AbstractSelectMenu;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap.ISnapAnimationState;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public class ContainerSelectMenu
extends AbstractSelectMenu
implements IContainerSelectMenu
{
	private IActorContainer container;
	
	//
	@Override
	public IActorContainer getActorContainer()
	{
		return container;
	}
	
	@Override
	public void setActorContainer(IActorContainer container)
	{
		this.container = container;
	}

	@Override
	public void setSelectIndex(int index)
	{
		if(container == null)
		{
			return;
		}
		
		super.setSelectIndex(Math.min(index, container.getActors().size() - 1));
	}
	
	@Override
	protected void onUpdate()
	{
		IActor selectFocusActor = getSelectFocusActor();
		
		if(container == null)
		{
			if(selectFocusActor != null)
			{
				selectFocusActor.setVisible(false);
			}
			
			return;
		}
		
	
		if(selectFocusActor == null)
		{
			return;
		}
		
		Collection<IActor> actors = container.getActors();		
		int actorsLength = actors.size();
		super.setSelectIndex(MathHelper.minMax(0, getSelectIndex(), actorsLength));
		
		int selectIndex = getSelectIndex();
		ISnapAnimationState snapAnimation = getSelectFocusAnimationState();
		
		int count = 0;
		for(IActor actor : actors)
		{
			if(selectIndex == count)
			{
				ComponentHelper.setSizeIfNeedsUpdate(selectFocusActor, actor.getSize());
//				ComponentHelper.setOriginAndAdjustMove(selectFocusActor, actor.getOrigin());				
				snapAnimation.setSnapTarget(actor);
			}
			
			count++;
		}
		
		selectFocusActor.setVisible(count != actorsLength);
	}
	
	@Override
	protected void onUpdateSelectMenuChildren()
	{
		if(container != null)
		{
			ActorHelper.updateStrictFixInnerActor(container, this);
		}
	}
	
	@Override
	protected void onDrawSelectMenuChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		if(container != null)
		{
			container.draw(target, states, mask);
		}
	}
}
