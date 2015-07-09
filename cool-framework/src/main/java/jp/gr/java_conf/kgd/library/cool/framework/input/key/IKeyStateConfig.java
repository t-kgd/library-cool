package jp.gr.java_conf.kgd.library.cool.framework.input.key;

public interface IKeyStateConfig
extends IConstKeyStateConfig
{
	//
	void setKeyRepeatEnabled(boolean flag);	
	void setKeyRepeatStartTime(int time);	
	void setKeyRepeatInterval(int time);
	
	//
	void setTapTime(int time);
	void setDoubleTapInterval(int time);
}
