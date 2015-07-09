package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.line;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.IContainerLayout;

public interface IStraightLayout
extends IContainerLayout
{
	float getMargin();
	void setMargin(float margin);
	
	float getSideMargin();
	void setSideMargin(float margin);
}
