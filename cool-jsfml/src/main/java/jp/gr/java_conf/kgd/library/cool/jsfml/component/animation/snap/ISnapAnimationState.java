package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstDrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.IAnimationState;

public interface ISnapAnimationState
extends IConstSnapAnimationState, IAnimationState
{
	void setSnapTarget(IConstDrawState target);
}
