package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import org.jsfml.system.Vector2f;

public interface IViewState
extends IConstViewState
{
	//
	void setViewPosition(Vector2f position);
	void setViewPosition(float x, float y);
	
	void moveViewPosition(Vector2f diff);
	void moveViewPosition(float x, float y);
	
	//
	void setViewOrigin(Vector2f origin);
	void setViewOrigin(float x, float y);
	
	void setViewOriginKeepedCenter(boolean flag);
}
