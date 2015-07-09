package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.linear;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.IConstScrollbarConfig;

public interface IConstLinearSelectMenuState
extends IConstLinearSelectMenuBaseState
{
	boolean isScrollbarVisible();	
	float getScrollbarSize();	
	IConstScrollbarConfig getScrollbarConfig();
}
