package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.slide;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.ITransitionState;
import jp.gr.java_conf.kgd.library.cool.jsfml.base.IDirection;

public interface ISlideTransitionState
extends ITransitionState, IConstSlideTransitionState
{
	void setSlideDirection(IDirection direction);
}
