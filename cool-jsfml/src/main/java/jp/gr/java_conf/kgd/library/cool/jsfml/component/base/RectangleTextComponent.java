package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstFont;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

public class RectangleTextComponent
extends RectangleComponent
implements IRectangleTextComponent
{
	private TextComponent textComponent = new TextComponent();
	
	public RectangleTextComponent()
	{
//		setFillColor(Color.TRANSPARENT);
	}
	
	//
	public void draw(RenderTarget target, RenderStates states, IConstColorMaskState mask)
	{
		if(!isVisible())
		{
			return;
		}
		
		super.draw(target, states, mask);
		
		//
		if(textComponent.getFont() == null ||
				textComponent.getString().equals(""))
		{
			return;
		}
		
		ComponentHelper.copyComponentState(textComponent, this);
		textComponent.draw(target, states, mask);
	}

	//
	public Color getTextColor()
	{
		return textComponent.getTextColor();
	}

	public void setTextColor(Color color)
	{
		textComponent.setTextColor(color);
	}

	public Vector2f getTextSize()
	{
		return textComponent.getTextSize();
	}

	public void setString(String string)
	{
		textComponent.setString(string);
	}

	public void setFont(ConstFont font)
	{
		textComponent.setFont(font);
	}

	public void setCharacterSize(int characterSize)
	{
		textComponent.setCharacterSize(characterSize);
	}

	public void setStyle(int style)
	{
		textComponent.setStyle(style);
	}

	public String getString()
	{
		return textComponent.getString();
	}

	public ConstFont getFont()
	{
		return textComponent.getFont();
	}

	public int getCharacterSize()
	{
		return textComponent.getCharacterSize();
	}

	public int getStyle()
	{
		return textComponent.getStyle();
	}

	public Vector2f findCharacterPos(int i)
	{
		return textComponent.findCharacterPos(i);
	}

	public boolean isBottomAlignment()
	{
		return textComponent.isBottomAlignment();
	}

	public void setBottomAlignment(boolean flag)
	{
		textComponent.setBottomAlignment(flag);
	}
	
	
}
