package jp.gr.java_conf.kgd.library.cool.jsfml.actor.base;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstFont;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractBaseComponentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.TextComponent;

public class TextActor
extends AbstractBaseComponentActor
implements ITextActor
{
	private TextComponent base = new TextComponent();

	public TextActor()
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

	public boolean isBottomAlignment()
	{
		return base.isBottomAlignment();
	}

	public void setBottomAlignment(boolean flag)
	{
		base.setBottomAlignment(flag);
	}
	
	
}
