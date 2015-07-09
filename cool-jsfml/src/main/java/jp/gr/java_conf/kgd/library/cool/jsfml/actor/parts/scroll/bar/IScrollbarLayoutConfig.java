package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.IShapeState;

public interface IScrollbarLayoutConfig
extends IConstScrollbarLayoutConfig
{
	void setSliderWidthRate(float rate);
	IShapeState getSliderShapeState();
}
