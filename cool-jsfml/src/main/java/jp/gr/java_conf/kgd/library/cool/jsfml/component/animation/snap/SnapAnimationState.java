package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstDrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.AnimationState;

public class SnapAnimationState
extends AnimationState
implements ISnapAnimationState
{
	//
	private IConstDrawState target;

	
	//
	public SnapAnimationState()
	{
		for(DrawStateElement element : DrawStateElement.values())
		{
			setSpeedRate(element, DEFAULT_SNAP_SPEED_RATE);
		}
	}
	
	
	//
	@Override
	public IConstDrawState getSnapTarget()
	{
		return target;
	}
	
	@Override
	public void setSnapTarget(IConstDrawState target)
	{
		this.target = target;
	}
	
	
	//
	public Color getColorMask()
	{
		return target.getColorMask();
	}

//	public boolean isVisible()
//	{
//		return target.isVisible();
//	}

	public Vector2f getOrigin()
	{
		return target.getOrigin();
	}

	public float getAlphaMaskRate()
	{
		return target.getAlphaMaskRate();
	}

	public float getRotation()
	{
		return target.getRotation();
	}

	public Vector2f getScale()
	{
		return target.getScale();
	}

	public Vector2f getPosition()
	{
		return target.getPosition();
	}

	public Transform getTransform()
	{
		return target.getTransform();
	}
	//
}
