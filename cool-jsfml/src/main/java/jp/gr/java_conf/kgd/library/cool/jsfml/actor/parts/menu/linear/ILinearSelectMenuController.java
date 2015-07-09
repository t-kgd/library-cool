package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.linear;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.ISelectMenuState;

public interface ILinearSelectMenuController
extends ISelectMenuState, IConstLinearSelectMenuController
{
//	void setDisplayPageIndex(int index);
	void addDisplayPageIndex(int diff);
}
