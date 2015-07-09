package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.IConstAnimationState;

public interface IConstSelectMenuState
{
	//不変でない。
	IActor getSelectFocusActor();
	
	IConstAnimationState getSelectFocusAnimationState();
}
