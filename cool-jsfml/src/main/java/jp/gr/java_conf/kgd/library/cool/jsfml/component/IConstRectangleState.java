package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

public interface IConstRectangleState
extends IConstDimensionState
{
	Vector2f getPosition();
	Vector2f getOrigin();
	
	FloatRect getLocalBounds();
	FloatRect getGlobalBounds();
}
