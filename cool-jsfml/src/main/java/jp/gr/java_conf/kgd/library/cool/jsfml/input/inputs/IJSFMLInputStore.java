package jp.gr.java_conf.kgd.library.cool.jsfml.input.inputs;

import jp.gr.java_conf.kgd.library.cool.framework.input.inputs.IInputStore;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.keys.IJSFMLInput;

public interface IJSFMLInputStore
extends IInputStore, IConstJSFMLInputStore
{
	IJSFMLInput getInput(int no);
}
