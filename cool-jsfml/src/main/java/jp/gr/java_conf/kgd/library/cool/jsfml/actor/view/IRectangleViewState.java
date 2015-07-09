package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import org.jsfml.system.Vector2f;

public interface IRectangleViewState
extends IConstRectangleViewState, IViewState
{
	void setInnerSize(Vector2f size);
	void setInnerSize(float width, float height);
}
