package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout;

import org.jsfml.system.Vector2f;

public class LayoutCache
implements ILayoutCache
{
	private int componentNum;
	private Vector2f layoutSize = Vector2f.ZERO;
	
	@Override
	public int getComponentNum()
	{
		return componentNum;
	}

	@Override
	public Vector2f getLayoutSize()
	{
		return layoutSize;
	}

	@Override
	public void setComponentNum(int num)
	{
		this.componentNum = Math.max(0, num);
	}

	@Override
	public void setLayoutSize(Vector2f size)
	{
		this.layoutSize = size;
	}
}
