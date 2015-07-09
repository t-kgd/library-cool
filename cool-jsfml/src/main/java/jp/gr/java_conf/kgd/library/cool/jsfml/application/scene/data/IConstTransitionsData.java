package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.ITransition;

public interface IConstTransitionsData
{
	ITransition getOutTransition();
	ITransition getInTransition();
	
	boolean isTransitionCrossed();
	int getTransitionInterval();
}
