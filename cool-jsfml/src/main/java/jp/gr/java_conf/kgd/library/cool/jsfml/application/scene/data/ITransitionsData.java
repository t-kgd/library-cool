package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.ITransition;

public interface ITransitionsData
extends IConstTransitionsData
{
	void setOutTransition(ITransition transition);
	void setInTransition(ITransition transition);
	
	void setTransitionCrossed(boolean flag);
	void setTransitionInterval(int interval);
}
