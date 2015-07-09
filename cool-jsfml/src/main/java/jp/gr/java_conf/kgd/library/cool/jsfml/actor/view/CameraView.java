package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IDrawState;

public class CameraView
extends AbstractView
implements ICameraView
{
	public CameraView()
	{
	}
	
	@Override
	public IDrawState getCameraState()
	{
		return super.getCameraState();
	}

	@Override
	public boolean isViewControllLimited()
	{
		return super.isViewControllLimited();
	}
	
	@Override
	public boolean isViewOriginKeepedCenter()
	{
		return super.isViewOriginKeepedCenter();
	}

	@Override
	public void setViewControllLimited(boolean flag)
	{
		super.setViewControllLimited(flag);
	}
	
	@Override
	public void setViewOriginKeepedCenter(boolean flag)
	{
		super.setViewOriginKeepedCenter(flag);
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
