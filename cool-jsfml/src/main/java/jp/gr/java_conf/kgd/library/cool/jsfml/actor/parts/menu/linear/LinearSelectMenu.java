package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.linear;

import java.util.List;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.IScrollbarConfig;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.Scrollbar;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap.ISnapAnimationState;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class LinearSelectMenu
extends AbstractParentActor
implements ILinearSelectMenu
{
	private LinearSelectMenuBase linearSelectMenu = new LinearSelectMenuBase();
	
	//
	private boolean isScrollbarVisible = true;
	private float scrollbarSize = 8;
	
	private Scrollbar scrollbar = new ObservalInnerScrollbar();
	
	//
	private boolean isLayoutNeedsUpdate = true;
	
	
	public LinearSelectMenu()
	{
	}
	
	//
	@Override
	public void setSize(Vector2f size)
	{
		super.setSize(size);
		
		isLayoutNeedsUpdate = true;
	}
	
	//
	public boolean isScrollbarVisible()
	{
		return isScrollbarVisible;
	}
	
	public void setScrollbarVisible(boolean flag)
	{
		if(isScrollbarVisible == flag)
		{
			return;
		}
		
		this.isScrollbarVisible = flag;
		
		isLayoutNeedsUpdate = true;
	}
	
	public float getScrollbarSize()
	{
		return scrollbarSize;
	}
	
	public void setScrollbarSize(float size)
	{
		float sizeBuf = Math.max(0, size);
		
		if(MathHelper.equals(scrollbarSize, sizeBuf))
		{
			return;
		}
		
		this.scrollbarSize = sizeBuf;
		
		isLayoutNeedsUpdate = true;
	}
	
	public IScrollbarConfig getScrollbarConfig()
	{
		return scrollbar;
	}

	
	@Override
	protected void onUpdate()
	{
		if(isLayoutNeedsUpdate)
		{
			isLayoutNeedsUpdate = false;
			updateLayout();
		}
		
		int length = getActors().size();		
		int orderNum = getOrderNum();		
		
		//スクロールスライダーの操作。	
		float sliderLengthRate = length > 0 ? (float)orderNum / length : 1;		
		scrollbar.setSliderLengthRate(sliderLengthRate);
		
		int displayPageMax = getDisplayPageMax();
		float sliderPositionRate = displayPageMax > 1 ? (float)getDisplayPageIndex() / (displayPageMax - 1) : 0;
		scrollbar.setSliderPositionRate(sliderPositionRate);
	}
	
	private void updateLayout()
	{
		Vector2f baseSize = getSize();
		
		if(!isScrollbarVisible)
		{
			ComponentHelper.setSizeIfNeedsUpdate(linearSelectMenu, baseSize);
			scrollbar.setVisible(false);
			return;
		}
		else
		{
			scrollbar.setVisible(true);
		}
		
//		if(!isScrollbarVisible || getActors().size() <= getOrderNum())
//		{
//			ComponentHelper.setSizeIfNeedsUpdate(linearSelectMenu, baseSize);
//			scrollbar.setVisible(false);
//			return;
//		}
//		else
//		{
//			scrollbar.setVisible(true);
//		}
		
		
		float viewWidth = baseSize.x;
		float viewHeight = baseSize.y;

		float scrollbarFrameSize = scrollbar.getFrameSize();
		float scrollbarInnerSize = Math.max(0, scrollbarSize - scrollbarFrameSize * 2);
		
		boolean isMajorOrderHorizontal = isMajorOrderHorizontal();
		if(isMajorOrderHorizontal)
		{
			viewHeight -= scrollbarSize;
			
			scrollbar.setSize(viewWidth - scrollbarFrameSize * 2, scrollbarInnerSize);
			scrollbar.setPosition(0, viewHeight);
		}
		else
		{
			viewWidth -= scrollbarSize;
			
			scrollbar.setSize(scrollbarInnerSize, viewHeight - scrollbarFrameSize * 2);
			scrollbar.setPosition(viewWidth, 0);
		}
			
		ComponentHelper.setSizeIfNeedsUpdate(linearSelectMenu, viewWidth, viewHeight);		
		scrollbar.move(scrollbarFrameSize, scrollbarFrameSize);
	}
	
	@Override
	protected void onUpdateChildren()
	{
		linearSelectMenu.update();
		
		scrollbar.update();
	}
	
	@Override
	protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		linearSelectMenu.draw(target, states, mask);
		
		scrollbar.draw(target, states, mask);
	}
	
	//
	public List<IActor> getActors()
	{
		return linearSelectMenu.getActors();
	}

	public boolean addActor(IActor actor)
	{
		return linearSelectMenu.addActor(actor);
	}

	public boolean removeActor(Object key)
	{
		return linearSelectMenu.removeActor(key);
	}

	public int getSelectIndex()
	{
		return linearSelectMenu.getSelectIndex();
	}

	public final void addSelectIndex(int diff)
	{
		setSelectIndex(linearSelectMenu.getSelectIndex() + diff);
	}

	public int getDisplayPageIndex()
	{
		return linearSelectMenu.getDisplayPageIndex();
	}

	public IActor getSelectFocusActor()
	{
		return linearSelectMenu.getSelectFocusActor();
	}

	public void setSelectFocusActor(IActor actor)
	{
		linearSelectMenu.setSelectFocusActor(actor);
	}

	public int getDisplaySelectIndex()
	{
		return linearSelectMenu.getDisplaySelectIndex();
	}

	public ISnapAnimationState getSelectFocusAnimationState()
	{
		return linearSelectMenu.getSelectFocusAnimationState();
	}

	public void addDisplayPageIndex(int diff)
	{
		setSelectIndex(linearSelectMenu.getSelectIndex() +
				linearSelectMenu.getOrderNum() * diff);
	}

	public int getDisplayPageMax()
	{
		return linearSelectMenu.getDisplayPageMax();
	}

	public boolean isMajorOrderHorizontal()
	{
		return linearSelectMenu.isMajorOrderHorizontal();
	}

	public void setMajorOrderHorizontal(boolean flag)
	{
		if(flag == isMajorOrderHorizontal())
		{
			return;
		}
		
		linearSelectMenu.setMajorOrderHorizontal(flag);
		
		isLayoutNeedsUpdate = true;
	}

	public int getOrderNum()
	{
		return linearSelectMenu.getOrderNum();
	}

	public void setOrderNum(int orderNum)
	{
		linearSelectMenu.setOrderNum(orderNum);
	}

	public float getMarginWidth()
	{
		return linearSelectMenu.getMarginWidth();
	}

	public void setMarginWidth(float width)
	{
		linearSelectMenu.setMarginWidth(width);
	}

	public float getMarginHeight()
	{
		return linearSelectMenu.getMarginHeight();
	}

	public void setMarginHeight(float height)
	{
		linearSelectMenu.setMarginHeight(height);
	}

	public void setSelectIndex(int index)
	{
		linearSelectMenu.setSelectIndex(index);
	}
	
	//
	private class ObservalInnerScrollbar
	extends Scrollbar
	{
		@Override
		public void setFrameSize(float size)
		{
			if(MathHelper.equals(size, getFrameSize()))
			{
				return;
			}
			
			super.setFrameSize(size);
			
			isLayoutNeedsUpdate = true;
		}
	}
	
	@Override
	public boolean isFocusSizeFitToSelectComponent()
	{
		return linearSelectMenu.isFocusSizeFitToSelectComponent();
	}
	
	@Override
	public void setFocusSizeFitToSelectComponent(boolean flag)
	{
		linearSelectMenu.setFocusSizeFitToSelectComponent(flag);
	}
}
