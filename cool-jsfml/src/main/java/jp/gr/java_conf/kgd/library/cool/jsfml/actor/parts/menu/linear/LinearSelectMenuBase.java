package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.linear;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.AbstractSelectMenu;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line.LineLayoutManager;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class LinearSelectMenuBase
extends AbstractSelectMenu
implements ILinearSelectMenuBase
{
//	private List<IActor> actorList = new LinkedList<>();
	private List<IActor> actorList = new ArrayList<>();
	
	private List<IActor> displaySubList;
//	private List<IActor> displaySubList = new ArrayList<>();
	
	private LineLayoutManager lineLayoutManager = new LineLayoutManager();
	
	private int displayPageIndex = 0;
	
	private boolean isFocusSizeFitToSelectComponent = true;
	
	
	//
	@Override
	public boolean isFocusSizeFitToSelectComponent()
	{
		return isFocusSizeFitToSelectComponent;
	}
	
	@Override
	public void setFocusSizeFitToSelectComponent(boolean flag)
	{
		this.isFocusSizeFitToSelectComponent = flag;
	}
	
	//
	@Override
	public List<IActor> getActors()
	{
		return actorList;
	}
	
	@Override
	public boolean addActor(IActor actor)
	{
		return actorList.add(actor);
	}
	
	@Override
	public boolean removeActor(Object key)
	{
		return actorList.remove(key);
	}
	
	//
	@Override
	public int getSelectIndex()
	{
//		setSelectIndex(super.getSelectIndex());
//		
//		return super.getSelectIndex();
		
		checkInnerState();
		return super.getSelectIndex();
	}
	
	@Override
	public void setSelectIndex(int index)
	{
//		super.setSelectIndex(Math.min(index, actorList.size() - 1));
//		
//		int indexBuf = super.getSelectIndex();
//		
//		setDisplayPageIndex(indexBuf / getOrderNum());
		
		super.setSelectIndex(index);		
		checkInnerState();
	}
	
	//
	@Override
	public int getDisplayPageIndex()
	{
//		setDisplayPageIndex(displayPageIndex);
//		
//		return displayPageIndex;
		
		checkInnerState();
		return displayPageIndex;
	}
	
	@Override
	public int getDisplaySelectIndex()
	{
		checkInnerState();
		return super.getSelectIndex() % getOrderNum();
	}
	
	@Override
	public void addDisplayPageIndex(int diff)
	{
		setSelectIndex(getSelectIndex() + getOrderNum() * diff);
	}
	
//	private void setDisplayPageIndex(int index)
//	{	
//		this.displayPageIndex = MathHelper.minMax(0, index, getDisplayPageMax() - 1);	
//	}
	
	@Override
	public int getDisplayPageMax()
	{
		return ((actorList.size() - 1) / lineLayoutManager.getOrderNum()) + 1;
	}
	
	private void checkInnerState()
	{
		int length = actorList.size();
		int orderNum = lineLayoutManager.getOrderNum();

		int pageMax = ((length - 1) / orderNum) + 1;
		
		//		
		super.setSelectIndex(MathHelper.minMax(0, super.getSelectIndex(), length - 1));
		
		displayPageIndex = MathHelper.minMax(0, super.getSelectIndex() / orderNum, pageMax - 1);
	}
	
	//
	@Override
	protected void onUpdate()
	{
		int orderNum = lineLayoutManager.getOrderNum();		
		int firstIndex = orderNum * getDisplayPageIndex();
		
		int length = actorList.size();
		int lastIndex = MathHelper.minMax(0, firstIndex + orderNum, length);
		//数値の意味合いとしては(lastIndex + 1)
		
		
		//表示リストを設定。
		displaySubList = actorList.subList(firstIndex, lastIndex);
//		displaySubList.clear();
//		displaySubList.addAll(actorList.subList(firstIndex, lastIndex));
		
		lineLayoutManager.applyLayoutToComponents(displaySubList, getSize());	

		
//		//スクロールスライダーの操作。	
//		float sliderLengthRate = (float)length / orderNum;		
//		scrollbar.setSliderLengthRate(sliderLengthRate);
//		
//		int displayPageMax = getDisplayPageMax();
//		scrollbar.setSliderPositionRate((float)displayPageIndex / displayPageMax);
		
		
		//フォーカスの操作。
		IActor focusActor = getSelectFocusActor();
		if(focusActor != null)
		{
			int selectIndex = getSelectIndex();
			int displayPageComponentNum = displaySubList.size();
			
			if(displayPageComponentNum > 0)
			{
				IActor selectActor = displaySubList.get(MathHelper.minMax(0, selectIndex % orderNum, displayPageComponentNum - 1));
				if(selectActor == null)
				{
					focusActor.setVisible(false);
				}
				else
				{
					focusActor.setVisible(true);

					if(isFocusSizeFitToSelectComponent)
					{
						ComponentHelper.setSizeIfNeedsUpdate(getSelectFocusActor(), selectActor.getSize());			
					}
				}

				getSelectFocusAnimationState().setSnapTarget(selectActor);
			}
		}
	}
	
	@Override
	protected void onUpdateSelectMenuChildren()
	{
//		if(displaySubList != null)
//		{
//			for(IActor actor : displaySubList)
//			{
//				actor.update();
//			}
//		}
		
		for(IActor actor : actorList)
		{
			actor.update();
		}
	}

	@Override
	protected void onDrawSelectMenuChildren(RenderTarget target,
			RenderStates states, IConstColorMaskState mask)
	{
		if(displaySubList != null)
		{
			for(IActor actor : displaySubList)
			{
				actor.draw(target, states, mask);
			}
		}
	}

	public boolean isMajorOrderHorizontal()
	{
		return lineLayoutManager.isMajorOrderHorizontal();
	}

	public void setMajorOrderHorizontal(boolean flag)
	{
		lineLayoutManager.setMajorOrderHorizontal(flag);
	}

	public int getOrderNum()
	{
		return lineLayoutManager.getOrderNum();
	}

	public void setOrderNum(int orderNum)
	{
		lineLayoutManager.setOrderNum(orderNum);
		
		checkInnerState();
	}

	public float getMarginWidth()
	{
		return lineLayoutManager.getMarginWidth();
	}

	public void setMarginWidth(float width)
	{
		lineLayoutManager.setMarginWidth(width);
	}

	public float getMarginHeight()
	{
		return lineLayoutManager.getMarginHeight();
	}

	public void setMarginHeight(float height)
	{
		lineLayoutManager.setMarginHeight(height);
	}
	
	//
}
