package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import org.jsfml.system.Vector2f;

public interface IConstViewState
{
	Vector2f getViewPosition();
	
	Vector2f getViewOrigin();
	boolean isViewOriginKeepedCenter();
}
