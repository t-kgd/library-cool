package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponentState;

import org.jsfml.system.Vector2f;

public class LineLayoutManager
implements ILineLayoutManager
{
	private LineLayoutState lineLayoutState = new LineLayoutState();

	
	//cahce
	private LineLayoutCache lineLayoutCache = new LineLayoutCache();
	
	
	//
	public boolean isMajorOrderHorizontal()
	{
		return lineLayoutState.isMajorOrderHorizontal();
	}

	public void setMajorOrderHorizontal(boolean flag)
	{
		lineLayoutState.setMajorOrderHorizontal(flag);
	}

	public int getOrderNum()
	{
		return lineLayoutState.getOrderNum();
	}

	public void setOrderNum(int orderNum)
	{
		lineLayoutState.setOrderNum(orderNum);
	}

	public float getMarginWidth()
	{
		return lineLayoutState.getMarginWidth();
	}

	public void setMarginWidth(float width)
	{
		lineLayoutState.setMarginWidth(width);
	}

	public float getMarginHeight()
	{
		return lineLayoutState.getMarginHeight();
	}

	public void setMarginHeight(float height)
	{
		lineLayoutState.setMarginHeight(height);
	}

	@Override
	public void applyLayoutToComponents(
			Collection<? extends IComponentState> states, Vector2f layoutSize)
	{
		int length = states.size();

		lineLayoutCache.setComponentNum(length);
		lineLayoutCache.setLayoutSize(layoutSize);
		
		if(length <= 0)
		{
			return;
		}

		// 開始
		boolean isMajorOrderHorizontal = isMajorOrderHorizontal();

		int orderNum = getOrderNum();

		int numX = orderNum;
		int numY = ((length - 1) / numX) + 1;

		if(!isMajorOrderHorizontal)
		{
			int tmp = numX;
			numX = numY;
			numY = tmp;
		}
		
		lineLayoutCache.setLineNumX(numX);
		lineLayoutCache.setLineNumY(numY);
		
		//
		float marginWidth = getMarginWidth();
		float marginHeight = getMarginHeight();

		float innerWidth = layoutSize.x - (marginWidth * (numX + 1));
		float innerHeight = layoutSize.y - (marginHeight * (numY + 1));

		float componentWidth = innerWidth / numX;
		float componentHeight = innerHeight / numY;

		{
			Vector2f componentSize = new Vector2f(componentWidth,
					componentHeight);

			lineLayoutCache.setComponentSize(componentSize);
			
			int i = 0;
			for(IComponentState state : states)
			{
//				ComponentHelper.resetComponentState(state);
//				ComponentHelper.resetDrawState(state);

				//
				int ix = i % orderNum;
				int iy = i / orderNum;

				if(!isMajorOrderHorizontal)
				{
//					ix ^= iy;
//					iy ^= ix;
//					ix ^= iy;
					// →このスワップは怖いので却下。

					int tmp = ix;
					ix = iy;
					iy = tmp;
				}

				
//				state.setSize(componentSize);
				ComponentHelper.setSizeIfNeedsUpdate(state, componentSize);

				state.setPosition(marginWidth +
						(marginWidth + componentWidth) * ix, marginHeight +
						(marginHeight + componentHeight) * iy);

				state.setOrigin(Vector2f.ZERO);
				ComponentHelper.setOriginToCenterAndAdjustMove(state);

				//
				i++;
			}
		}
	}
	
	@Override
	public IConstLineLayoutCache getLayoutCache()
	{
		return lineLayoutCache;
	}
}
