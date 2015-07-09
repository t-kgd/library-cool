package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.slide;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.IConstTransitionState;
import jp.gr.java_conf.kgd.library.cool.jsfml.base.IDirection;

public interface IConstSlideTransitionState
extends IConstTransitionState
{
	IDirection getSlideDirection();
}
