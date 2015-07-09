package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import org.jsfml.system.Vector2f;

public class RectangleView
extends AbstractView
implements IRectangleView
{
	public RectangleView()
	{
		super.setViewControllLimited(true);
	}
	
	@Override
	protected void setViewControllLimited(boolean flag)
	{
		if(!flag)
		{
			throw new IllegalArgumentException();
		}
	}
	
	
	@Override
	public Vector2f getInnerSize()
	{
		return super.getInnerSize();
	}

	@Override
	public void setInnerSize(Vector2f size)
	{
		super.setInnerSize(size);
	}

	@Override
	final public void setInnerSize(float width, float height)
	{
		super.setInnerSize(width, height);
	}	
}
