package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

public interface IConstShapeState extends IConstTextureState
{
	Color getFillColor();
	Color getOutlineColor();
	float getOutlineThickness();

	Vector2f getPoint(int i);
	int getPointCount();
	Vector2f[] getPoints();
}
