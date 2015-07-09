package jp.gr.java_conf.kgd.library.cool.jsfml.input.keys;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IConstInput;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.bind.IConstKeyBind;

public interface IConstJSFMLInput
extends IConstInput
{
	Collection<? extends IConstKeyBind> getUnmodifiableKeyBinds();
	IConstKeyBind getKeyBind(Object keyID);
}
