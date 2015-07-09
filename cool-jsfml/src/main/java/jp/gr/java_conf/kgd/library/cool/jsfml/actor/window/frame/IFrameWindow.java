package jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.IWindow;

public interface IFrameWindow
extends IWindow, IFrameActor
{
	Vector2f getOuterSize();
}
