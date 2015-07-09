package jp.gr.java_conf.kgd.library.cool.jsfml.actor.base;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstFont;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractBaseComponentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.RectangleTextComponent;

public class RectangleTextActor
extends AbstractBaseComponentActor
implements IRectangleTextActor
{
	private RectangleTextComponent base = new RectangleTextComponent();

	public RectangleTextActor()
	{
		setBaseComponent(base);
	}
	
	public Color getTextColor()
	{
		return base.getTextColor();
	}

	public void setTextColor(Color color)
	{
		base.setTextColor(color);
	}

	public Vector2f getTextSize()
	{
		return base.getTextSize();
	}

	public void setString(String string)
	{
		base.setString(string);
	}

	public void setFont(ConstFont font)
	{
		base.setFont(font);
	}

	public void setCharacterSize(int characterSize)
	{
		base.setCharacterSize(characterSize);
	}

	public void setStyle(int style)
	{
		base.setStyle(style);
	}

	public String getString()
	{
		return base.getString();
	}

	public ConstFont getFont()
	{
		return base.getFont();
	}

	public int getCharacterSize()
	{
		return base.getCharacterSize();
	}

	public int getStyle()
	{
		return base.getStyle();
	}

	public Vector2f findCharacterPos(int i)
	{
		return base.findCharacterPos(i);
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

	public boolean isBottomAlignment()
	{
		return base.isBottomAlignment();
	}

	public void setBottomAlignment(boolean flag)
	{
		base.setBottomAlignment(flag);
	}
	
	
}
