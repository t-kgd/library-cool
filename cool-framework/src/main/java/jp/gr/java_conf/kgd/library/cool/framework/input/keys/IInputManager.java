package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

public interface IInputManager
extends IInput
{
	void bindInputConfig(IConstInputConfig config);
	
	void updateKeyStates(boolean hasFocus);
}
