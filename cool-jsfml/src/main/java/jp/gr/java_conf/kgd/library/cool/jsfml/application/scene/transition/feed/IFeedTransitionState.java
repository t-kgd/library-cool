package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.feed;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.ITransitionState;

public interface IFeedTransitionState
extends ITransitionState, IConstFeedTransitionState
{
	void setFeedOutAlphaRate(float rate);
}
