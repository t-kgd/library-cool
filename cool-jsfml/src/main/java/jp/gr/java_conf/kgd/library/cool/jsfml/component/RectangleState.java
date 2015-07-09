package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

public class RectangleState
implements IRectangleState
{
	private Vector2f position;
	private Vector2f origin;
	private Vector2f size;

	public RectangleState()
	{
		this(Vector2f.ZERO);
	}
	
	public RectangleState(Vector2f size)
	{
		this(Vector2f.ZERO, size);
	}
	
	public RectangleState(Vector2f position, Vector2f size)
	{
		this(position, size, Vector2f.ZERO);
	}
	
	public RectangleState(Vector2f position, Vector2f size, Vector2f origin)
	{
		this.position = position;
		this.origin = origin;
		this.size = size;
	}
	
	@Override
	public Vector2f getPosition()
	{
		return position;
	}

	@Override
	public Vector2f getOrigin()
	{
		return origin;
	}

	@Override
	public Vector2f getSize()
	{
		return size;
	}

	@Override
	public void setSize(Vector2f size)
	{
		this.size = size;
	}

	@Override
	final public void setSize(float width, float height)
	{
		this.size = new Vector2f(width, height);
	}

	@Override
	public void setPosition(Vector2f position)
	{
		this.position = position;
	}

	@Override
	final public void setPosition(float x, float y)
	{
		this.position = new Vector2f(x, y);
	}

	@Override
	public void setOrigin(Vector2f origin)
	{
		this.origin = origin;
	}

	@Override
	final public void setOrigin(float x, float y)
	{
		this.origin = new Vector2f(x, y);
	}
	
	@Override
	public FloatRect getLocalBounds()
	{
		return new FloatRect(Vector2f.ZERO, size);
	}
	
	@Override
	public FloatRect getGlobalBounds()
	{
		return new FloatRect(
				position.x - origin.x, position.y - origin.y,
				size.x, size.y);
	}
}
