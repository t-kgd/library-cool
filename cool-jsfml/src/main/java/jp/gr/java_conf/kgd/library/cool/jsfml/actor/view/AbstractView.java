package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;

import org.jsfml.system.Vector2f;

public class AbstractView
extends AbstractProtectedView
implements IView
{
	protected AbstractView()
	{
	}

	@Override
	public IActor getInnerActor()
	{
		return super.getInnerActor();
	}

	@Override
	public void setInnerActor(IActor actor)
	{
		super.setInnerActor(actor);
	}

	@Override
	public Vector2f getViewPosition()
	{
		return super.getViewPosition();
	}

	@Override
	public Vector2f getViewOrigin()
	{
		return super.getViewOrigin();
	}

	@Override
	public boolean isViewOriginKeepedCenter()
	{
		return super.isViewOriginKeepedCenter();
	}

	@Override
	public void setViewPosition(Vector2f position)
	{
		super.setViewPosition(position);
	}

	@Override
	final public void setViewPosition(float x, float y)
	{
		super.setViewPosition(x, y);
	}

	@Override
	public void setViewOrigin(Vector2f origin)
	{
		super.setViewOrigin(origin);
	}

	@Override
	final public void setViewOrigin(float x, float y)
	{
		super.setViewOrigin(x, y);
	}

	@Override
	public void setViewOriginKeepedCenter(boolean flag)
	{
		super.setViewOriginKeepedCenter(flag);
	}

	@Override
	public void moveViewPosition(Vector2f diff)
	{
		super.moveViewPosition(diff);
	}

	@Override
	final public void moveViewPosition(float x, float y)
	{
		super.moveViewPosition(x, y);
	}
}
