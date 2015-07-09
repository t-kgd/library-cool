package jp.gr.java_conf.kgd.library.cool.framework.input.inputs;

public interface IInputStateStoreManager
extends IInputStore
{
	int removeInput(int num);
	
	void updateInputs(boolean hasFocus);
}
