package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.line;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.AbstractProtectedCustomizableLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.IContainerLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line.LineLayoutManager;

public class AbstractProtectedLineLayout
extends AbstractProtectedCustomizableLayout
implements IContainerLayout
{
	private LineLayoutManager lineLayoutManager = new LineLayoutManager();
	
	//
	protected AbstractProtectedLineLayout()
	{
		setLayoutManager(lineLayoutManager);
	}

	//
	protected LineLayoutManager getLineLayoutManager()
	{
		return lineLayoutManager;
	}
	
	
	//
	protected boolean isMajorOrderHorizontal()
	{
		return lineLayoutManager.isMajorOrderHorizontal();
	}

	protected void setMajorOrderHorizontal(boolean flag)
	{
		lineLayoutManager.setMajorOrderHorizontal(flag);
	}

	protected int getOrderNum()
	{
		return lineLayoutManager.getOrderNum();
	}

	protected void setOrderNum(int orderNum)
	{
		lineLayoutManager.setOrderNum(orderNum);
	}

	protected float getMarginWidth()
	{
		return lineLayoutManager.getMarginWidth();
	}

	protected void setMarginWidth(float width)
	{
		lineLayoutManager.setMarginWidth(width);
	}

	protected float getMarginHeight()
	{
		return lineLayoutManager.getMarginHeight();
	}

	protected void setMarginHeight(float height)
	{
		lineLayoutManager.setMarginHeight(height);
	}
}
