package jp.gr.java_conf.kgd.library.cool.jsfml.actor.base;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractBaseComponentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.RectangleComponent;

public class RectangleActor
extends AbstractBaseComponentActor
implements IRectangleActor
{
	private RectangleComponent base = new RectangleComponent();

	public RectangleActor()
	{
		setBaseComponent(base);
	}
	
	public void setTexture(ConstTexture texture, boolean resetRect)
	{
		base.setTexture(texture, resetRect);
	}

	public final void setTexture(ConstTexture texture)
	{
		base.setTexture(texture);
	}

	public void setTextureRect(IntRect rect)
	{
		base.setTextureRect(rect);
	}

	public void setFillColor(Color color)
	{
		base.setFillColor(color);
	}

	public void setOutlineColor(Color color)
	{
		base.setOutlineColor(color);
	}

	public void setOutlineThickness(float thickness)
	{
		base.setOutlineThickness(thickness);
	}

	public ConstTexture getTexture()
	{
		return base.getTexture();
	}

	public IntRect getTextureRect()
	{
		return base.getTextureRect();
	}

	public Color getFillColor()
	{
		return base.getFillColor();
	}

	public Color getOutlineColor()
	{
		return base.getOutlineColor();
	}

	public float getOutlineThickness()
	{
		return base.getOutlineThickness();
	}

	public int getPointCount()
	{
		return base.getPointCount();
	}

	public Vector2f getPoint(int i)
	{
		return base.getPoint(i);
	}

	public Vector2f[] getPoints()
	{
		return base.getPoints();
	}
}
