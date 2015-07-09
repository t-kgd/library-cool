package jp.gr.java_conf.kgd.library.cool.framework.input.key;

public interface IKeyStateManager
extends IKeyState
{
	void bindKeyStateConfig(IConstKeyStateConfig config);
	
	void clearDoubleTapFlag();
	
	void updateKeyState(boolean isKeyDown);
}
