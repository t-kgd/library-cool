package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IDrawState;

public interface ICameraViewState
extends IConstCameraViewState, IViewState, IRectangleViewState
{
	IDrawState getCameraState();
	
	void setViewControllLimited(boolean flag);
}
