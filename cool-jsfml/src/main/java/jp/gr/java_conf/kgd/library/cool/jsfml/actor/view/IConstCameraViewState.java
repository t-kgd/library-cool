package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstDrawState;

public interface IConstCameraViewState
extends IConstViewState, IConstRectangleViewState
{
	IConstDrawState getCameraState();
	
	boolean isViewControllLimited();
}
