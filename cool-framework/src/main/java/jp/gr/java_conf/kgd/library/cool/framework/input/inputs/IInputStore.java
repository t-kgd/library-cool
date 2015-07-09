package jp.gr.java_conf.kgd.library.cool.framework.input.inputs;

import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IInput;
import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IInputConfig;

public interface IInputStore
extends IConstInputStore, IInputConfig
{
	int addInput(int num);
	
	IInput getInput(int no);
}
