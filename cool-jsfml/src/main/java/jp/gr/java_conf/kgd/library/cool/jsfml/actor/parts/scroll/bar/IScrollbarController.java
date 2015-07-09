package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar;

public interface IScrollbarController
extends IConstScrollbarController
{
	void setVertical(boolean flag);
	
	void setSliderPositionRate(float rate);
	void setSliderLengthRate(float rate);
}
