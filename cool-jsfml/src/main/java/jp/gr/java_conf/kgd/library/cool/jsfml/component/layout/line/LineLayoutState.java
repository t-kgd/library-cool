package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line;

public class LineLayoutState implements ILineLayoutState
{
	public static final boolean DEFAULT_IS_MAJOR_ORDER_HORIZONTAL = true;
	public static final int DEFAULT_ORDER_NUM = 1;

	public static final float DEFAULT_MARGIN_WIDTH = 0;
	public static final float DEFAULT_MARGIN_HEIGHT = 0;
	
	//
	private boolean isMajorOrderHorizontal = DEFAULT_IS_MAJOR_ORDER_HORIZONTAL;
	private int orderNum = DEFAULT_ORDER_NUM;
	
	private float marginWidth = DEFAULT_MARGIN_WIDTH;
	private float marginHeight = DEFAULT_MARGIN_HEIGHT;

	//
	@Override
	public boolean isMajorOrderHorizontal()
	{
		return isMajorOrderHorizontal;
	}

	@Override
	public void setMajorOrderHorizontal(boolean flag)
	{
		isMajorOrderHorizontal = flag;
	}

	@Override
	public int getOrderNum()
	{
		return orderNum;
	}

	@Override
	public void setOrderNum(int orderNum)
	{
		this.orderNum = Math.max(1, orderNum);
	}

	@Override
	public float getMarginWidth()
	{
		return marginWidth;
	}

	@Override
	public void setMarginWidth(float width)
	{
		this.marginWidth = Math.max(0, width);
	}

	@Override
	public float getMarginHeight()
	{
		return marginHeight;
	}

	@Override
	public void setMarginHeight(float height)
	{
		this.marginHeight = Math.max(0, height);
	}
}
