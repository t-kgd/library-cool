package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.line;

public class AbstractStraightLayout
extends AbstractProtectedLineLayout
implements IStraightLayout
{
	protected AbstractStraightLayout()
	{
	}
	
	@Override
	public float getMargin()
	{
		if(!isMajorOrderHorizontal())
		{
			return super.getMarginWidth();
		}
		else
		{
			return super.getMarginHeight();
		}
	}

	@Override
	public void setMargin(float margin)
	{
		if(!isMajorOrderHorizontal())
		{
			super.setMarginWidth(margin);
		}
		else
		{
			super.setMarginHeight(margin);
		}
	}
	
	@Override
	public float getSideMargin()
	{
		if(!isMajorOrderHorizontal())
		{
			return super.getMarginHeight();
		}
		else
		{
			return super.getMarginWidth();
		}
	}
	
	@Override
	public void setSideMargin(float margin)
	{
		if(!isMajorOrderHorizontal())
		{
			super.setMarginHeight(margin);
		}
		else
		{
			super.setMarginWidth(margin);
		}
	}
}
