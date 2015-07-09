package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.panel;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.IScrollbarConfig;

public interface IScrollPanelConfig
extends IConstScrollPanelConfig
{
	void setScrollbarSize(float size);
	IScrollbarConfig getScrollbarConfig();
}
