package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstDrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.IConstAnimationState;

public interface IConstSnapAnimationState
extends IConstAnimationState, IConstDrawState
{
	float DEFAULT_SNAP_SPEED_RATE = 0.1f;
	
	IConstDrawState getSnapTarget();
}
