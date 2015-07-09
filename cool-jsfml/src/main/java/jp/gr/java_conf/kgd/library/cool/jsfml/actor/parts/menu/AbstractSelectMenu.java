package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstDrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap.ISnapAnimationState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap.SnapAnimationManager;

public abstract class AbstractSelectMenu
extends AbstractParentActor
implements ISelectMenu
{
	private int selectIndex = 0;
	
	private IActor focusActor;
	private boolean isFocusActorModified = false;
	
	private SnapAnimationManager snapAnimationManager = new SnapAnimationManager();
	
	//
	protected AbstractSelectMenu()
	{
		snapAnimationManager.setEnabled(DrawStateElement.ROTATION, true);
		snapAnimationManager.setEnabled(DrawStateElement.SCALE, true);
		snapAnimationManager.setEnabled(DrawStateElement.POSITION, true);
		
		snapAnimationManager.setSpeedRate(DrawStateElement.POSITION, 0.5f);
	}
	
	//
	@Override
	public int getSelectIndex()
	{
		return selectIndex;
	}
	
	@Override
	public void setSelectIndex(int index)
	{
		this.selectIndex = Math.max(0, index);
	}
	
	@Override
	final public void addSelectIndex(int diff)
	{
		this.setSelectIndex(selectIndex + diff);
	}
	
	
	@Override
	public IActor getSelectFocusActor()
	{
		return focusActor;
	}
	
	@Override
	public void setSelectFocusActor(IActor actor)
	{
//		if(actor == null)
//		{
//			this.focusActor.setPosition(Vector2f.ZERO);
//		}
		
		this.focusActor = actor;		
		isFocusActorModified = true;
		
//		if(actor != null)
//		{
//			IConstDrawState snapState = snapAnimationManager.getSnapTarget();
//			if(snapState != null)
//			{
//				ComponentHelper.copyDrawState(actor, snapState);
//			}
//		}
	}
	
	//
	public ISnapAnimationState getSelectFocusAnimationState()
	{
		return snapAnimationManager;
	}
	
	
	@Override
	final protected void onUpdateChildren()
	{
		onUpdateSelectMenuChildren();
		
		if(focusActor != null)
		{
			IConstDrawState target = snapAnimationManager.getSnapTarget();
			if(target != null)
			{
				if(isFocusActorModified)
				{
					isFocusActorModified = false;
					ComponentHelper.copyDrawState(focusActor, target);
				}

				ComponentHelper.setOriginAndAdjustMove(focusActor, target.getOrigin());
//				focusActor.setOrigin(target.getOrigin());
				snapAnimationManager.applyAnimation(focusActor);

			}
			
			focusActor.update();
		}
	}
	
	abstract protected void onUpdateSelectMenuChildren();
	
	
	@Override
	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		onDrawSelectMenuChildren(target, states, mask);
			
		if(focusActor != null)
		{
			focusActor.draw(target, states, mask);
		}
	}
	
	abstract protected void onDrawSelectMenuChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask);
}
