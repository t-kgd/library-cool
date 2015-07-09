package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.line;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;

public class LineLayout
extends AbstractProtectedLineLayout
implements ILineLayout
{
	private List<IActor> actorList;
	
	@Override
	final protected Collection<IActor> createActualContainer()
	{
		this.actorList = createActualList();
		
		return this.actorList;
	}
	
	protected List<IActor> createActualList()
	{		
		return new LinkedList<>();
	}
	
	//
	@Override
	public List<IActor> getActors()
	{
		if(actorList == null)
		{
			super.getActors();	
		}
		
		return actorList;
	}
	
	//
	public boolean isMajorOrderHorizontal()
	{
		return super.isMajorOrderHorizontal();
	}

	public void setMajorOrderHorizontal(boolean flag)
	{
		super.setMajorOrderHorizontal(flag);
	}

	public int getOrderNum()
	{
		return super.getOrderNum();
	}

	public void setOrderNum(int orderNum)
	{
		super.setOrderNum(orderNum);
	}

	public float getMarginWidth()
	{
		return super.getMarginWidth();
	}

	public void setMarginWidth(float width)
	{
		super.setMarginWidth(width);
	}

	public float getMarginHeight()
	{
		return super.getMarginHeight();
	}

	public void setMarginHeight(float height)
	{
		super.setMarginHeight(height);
	}

//	public void applyLayoutToComponents(
//			Collection<? extends IComponentState> states, Vector2f layoutSize)
//	{
//		lineLayoutManager.applyLayoutToComponents(states, layoutSize);
//	}
//
//	public IConstLineLayoutCache getLayoutCache()
//	{
//		return getLineLayoutManager().getLayoutCache();
//	}
}
