package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.LayoutCache;

public class LineLayoutCache
extends LayoutCache
implements ILineLayoutCache
{
	private int lineNumX;
	private int lineNumY;
	
	private Vector2f componentSize = Vector2f.ZERO;

	//
	@Override
	public int getLineNumX()
	{
		return lineNumX;
	}

	@Override
	public int getLineNumY()
	{
		return lineNumY;
	}

	@Override
	public Vector2f getComponentSize()
	{
		return componentSize;
	}

	@Override
	public void setLineNumX(int num)
	{
		this.lineNumX = Math.max(0, num);
	}

	@Override
	public void setLineNumY(int num)
	{
		this.lineNumY = Math.max(0, num);
	}

	@Override
	public void setComponentSize(Vector2f size)
	{
		this.componentSize = size;
	}
}
