package jp.gr.java_conf.kgd.library.cool.jsfml.input.inputs;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.framework.input.inputs.IConstInputStore;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.keys.IConstJSFMLInput;

public interface IConstJSFMLInputStore
extends IConstInputStore
{
	int INPUT_NUM_MAX = 8;
	
	Collection<? extends IConstJSFMLInput> getUnmodifiableInputs();
	IConstJSFMLInput getInput(int no);
}
