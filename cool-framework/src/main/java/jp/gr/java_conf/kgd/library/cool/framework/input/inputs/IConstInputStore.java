package jp.gr.java_conf.kgd.library.cool.framework.input.inputs;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IConstInput;
import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IConstInputConfig;

public interface IConstInputStore
extends IConstInputConfig
{
	int getInputNum();
	
	Collection<? extends IConstInput> getUnmodifiableInputs();
	IConstInput getInput(int no);
}
