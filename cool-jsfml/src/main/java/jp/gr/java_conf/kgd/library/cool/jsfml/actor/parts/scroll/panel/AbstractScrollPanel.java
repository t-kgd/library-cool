package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.panel;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActorState;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.ConnectedScrollbarConfigs;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.IScrollbarConfig;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.Scrollbar;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

public abstract class AbstractScrollPanel
extends AbstractParentActor
implements IScrollPanel
{
	private IActorState innerPanelActorState;
	
	private Scrollbar horizontalScrollbar = new Scrollbar();
	private Scrollbar verticalScrollbar = new Scrollbar();
	private IScrollbarConfig scrollbarConfig =
			new ObservalScrollConfig(horizontalScrollbar, verticalScrollbar);
	
	
	//
	private float scrollbarSize = 8;
	
	
	//
	private boolean isScrollPanelLayoutNeedsUpdate = true;
	
	//
	protected AbstractScrollPanel()
	{
		horizontalScrollbar.setVertical(false);
		verticalScrollbar.setVertical(true);
	}
	
	//
	@Override
	public float getScrollbarSize()
	{
		return scrollbarSize;
	}
	
	@Override
	public void setScrollbarSize(float size)
	{
		this.scrollbarSize = size;
	}
	
	@Override
	public IScrollbarConfig getScrollbarConfig()
	{
		return scrollbarConfig;
	}
	
	
	
	
	
	protected IActorState getInnerPanelActorState()
	{
		return innerPanelActorState;
	}
	
	protected void setInnerPanelActorState(IActorState innerPanelState)
	{
		this.innerPanelActorState = innerPanelState;
	}
	
	protected Scrollbar getHorizontalScrollbar()
	{
		return horizontalScrollbar;
	}
	
	protected Scrollbar getVerticalScrollbar()
	{
		return verticalScrollbar;
	}
	
	protected boolean isScrollPanelLayoutNeedsUpdate()
	{
		return isScrollPanelLayoutNeedsUpdate;
	}
	
	protected void setScrollPanelLayoutNeedsUpdate(boolean flag)
	{
		this.isScrollPanelLayoutNeedsUpdate = flag;
	}
	
	
	@Override
	protected void onUpdate()
	{
		onUpdateInnerPanelActor();
		
		if(isScrollPanelLayoutNeedsUpdate)
		{
			isScrollPanelLayoutNeedsUpdate = false;
			updateLayout();
		}
	}
	
	@Override
	protected void onUpdateChildren()
	{
		horizontalScrollbar.update();
		verticalScrollbar.update();
	}
	
	abstract protected void onUpdateInnerPanelActor();
	
	//
	private void updateLayout()
	{
		Vector2f baseSize = getSize();
		
		float viewWidth = baseSize.x - scrollbarSize;
		float viewHeight = baseSize.y - scrollbarSize;
		
		innerPanelActorState.setSize(viewWidth, viewHeight);	
		
		float scrollbarFrameSize = horizontalScrollbar.getFrameSize();
		float scrollbarInnerSize = Math.max(1, scrollbarSize - scrollbarFrameSize * 2);
		
		horizontalScrollbar.setSize(viewWidth, scrollbarInnerSize);
		
		verticalScrollbar.setSize(scrollbarInnerSize, viewHeight);

		
		horizontalScrollbar.setPosition(0, viewHeight + scrollbarFrameSize);
		verticalScrollbar.setPosition(viewWidth + scrollbarFrameSize, 0);
	}
	
	@Override
	protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		horizontalScrollbar.draw(target, states, mask);
		verticalScrollbar.draw(target, states, mask);
	}
	
	
	private final class ObservalScrollConfig
	extends ConnectedScrollbarConfigs
	{
		public ObservalScrollConfig(IScrollbarConfig... scrollbarConfigs)
		{
			super(scrollbarConfigs);
		}
		
		@Override
		public void setFrameSize(float size)
		{
			super.setFrameSize(size);
			
			isScrollPanelLayoutNeedsUpdate = true;
		}
	}
}
