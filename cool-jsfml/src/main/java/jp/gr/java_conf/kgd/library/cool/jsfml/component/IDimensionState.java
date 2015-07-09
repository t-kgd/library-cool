package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.system.Vector2f;

public interface IDimensionState
extends IConstDimensionState
{
	void setSize(Vector2f size);
	void setSize(float width, float height);
}
