package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.linear;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.IScrollbarConfig;

public interface ILinearSelectMenuState
extends ILinearSelectMenuBaseState, IConstLinearSelectMenuState
{
	void setScrollbarVisible(boolean flag);
	
	void setScrollbarSize(float size);
	
	IScrollbarConfig getScrollbarConfig();
}
