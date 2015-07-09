package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstFont;
import org.jsfml.system.Vector2f;

public interface IConstTextState
{
	Vector2f findCharacterPos(int i);
	int getCharacterSize();
	
	ConstFont getFont();
	
	String getString();
	int getStyle();
	
	Color getTextColor();
	
	//
	Vector2f getTextSize();
	
	
	//後期追加　不具合に注意
	boolean isBottomAlignment();
}
