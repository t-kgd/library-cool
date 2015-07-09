package jp.gr.java_conf.kgd.library.cool.framework.input.key;

public interface IConstKeyStateConfig
{
	//
	boolean isKeyRepeatEnabled();	
	int getKeyRepeatStartTime();
	int getKeyRepeatInterval();
	
	//
	int getTapTime();
	int getDoubleTapInterval();	
}
