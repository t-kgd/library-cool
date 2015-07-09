package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.system.Vector2f;

public interface IRectangleState
extends IConstRectangleState, IDimensionState
{
	void setPosition(Vector2f position);	
	void setPosition(float x, float y);
	
	void setOrigin(Vector2f origin);	
	void setOrigin(float x, float y);
}
