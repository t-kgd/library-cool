package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public class ImmutableDrawState
implements IConstDrawState
{
	private final IConstDrawState inner;
	
	public ImmutableDrawState(IConstDrawState source)
	{
		DrawState state = new DrawState();
		
		ComponentHelper.copyDrawState(state, source);
		
		inner = state;
	}

	public Color getColorMask()
	{
		return inner.getColorMask();
	}

//	public boolean isVisible()
//	{
//		return inner.isVisible();
//	}

	public Vector2f getOrigin()
	{
		return inner.getOrigin();
	}

	public float getAlphaMaskRate()
	{
		return inner.getAlphaMaskRate();
	}

	public float getRotation()
	{
		return inner.getRotation();
	}

	public Vector2f getScale()
	{
		return inner.getScale();
	}

	public Vector2f getPosition()
	{
		return inner.getPosition();
	}

	public Transform getTransform()
	{
		return inner.getTransform();
	}
}
