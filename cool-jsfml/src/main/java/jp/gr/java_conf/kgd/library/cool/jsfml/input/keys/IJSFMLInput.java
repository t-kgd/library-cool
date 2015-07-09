package jp.gr.java_conf.kgd.library.cool.jsfml.input.keys;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IInput;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.bind.IKeyBind;

public interface IJSFMLInput
extends IInput, IConstJSFMLInput
{
	Collection<? extends IKeyBind> getUnmodifiableKeyBinds();
	IKeyBind getKeyBind(Object keyID);
}
