package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.linear;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.ISelectMenuState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line.ILineLayoutState;

public interface ILinearSelectMenuBaseState
extends ISelectMenuState, ILineLayoutState, IConstLinearSelectMenuBaseState
{
	void setFocusSizeFitToSelectComponent(boolean flag);
}
