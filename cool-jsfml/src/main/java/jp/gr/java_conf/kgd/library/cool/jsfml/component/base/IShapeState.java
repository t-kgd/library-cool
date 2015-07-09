package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import org.jsfml.graphics.Color;

public interface IShapeState
extends IConstShapeState, ITextureState
{
	void setFillColor(Color color);
	void setOutlineColor(Color color);
	void setOutlineThickness(float thickness);
}
