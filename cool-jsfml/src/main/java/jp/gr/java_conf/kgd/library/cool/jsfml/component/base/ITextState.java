package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstFont;

public interface ITextState extends IConstTextState
{
	void setCharacterSize(int characterSize);
	void setFont(ConstFont font);
	
	void setString(String string);
	void setStyle(int style);
	
	void setTextColor(Color color);
	
	
	//
	void setBottomAlignment(boolean flag);
}
