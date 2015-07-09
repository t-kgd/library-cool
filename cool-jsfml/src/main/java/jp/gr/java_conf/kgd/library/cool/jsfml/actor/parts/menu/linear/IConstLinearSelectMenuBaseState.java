package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.linear;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.IConstSelectMenuState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line.IConstLineLayoutState;

public interface IConstLinearSelectMenuBaseState
extends IConstSelectMenuState, IConstLineLayoutState
{
	int getDisplayPageMax();
	
	boolean isFocusSizeFitToSelectComponent();
}
