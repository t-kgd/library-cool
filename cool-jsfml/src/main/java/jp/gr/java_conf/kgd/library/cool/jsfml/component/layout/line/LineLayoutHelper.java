package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line;

import org.jsfml.system.Vector2f;

public class LineLayoutHelper
{
	public static final Vector2f getComponentSize(IConstLineLayoutState layoutState, Vector2f layoutSize, int componentNum)
	{
		// 開始
		boolean isMajorOrderHorizontal = layoutState.isMajorOrderHorizontal();

		int orderNum = layoutState.getOrderNum();

		int numX = orderNum;
		int numY = ((componentNum - 1) / numX) + 1;

		if(!isMajorOrderHorizontal)
		{
			int tmp = numX;
			numX = numY;
			numY = tmp;
		}

		float marginWidth = layoutState.getMarginWidth();
		float marginHeight = layoutState.getMarginHeight();

		float innerWidth = layoutSize.x - (marginWidth * (numX + 1));
		float innerHeight = layoutSize.y - (marginHeight * (numY + 1));

		float componentWidth = innerWidth / numX;
		float componentHeight = innerHeight / numY;

		return new Vector2f(componentWidth, componentHeight);
	}
}
