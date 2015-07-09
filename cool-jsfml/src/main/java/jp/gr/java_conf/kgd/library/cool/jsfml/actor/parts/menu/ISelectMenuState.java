package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.IAnimationState;

public interface ISelectMenuState
extends IConstSelectMenuState
{
	void setSelectFocusActor(IActor actor);
	
	IAnimationState getSelectFocusAnimationState();
}
