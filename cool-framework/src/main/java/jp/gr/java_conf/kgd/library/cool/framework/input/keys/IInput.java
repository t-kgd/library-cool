package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.framework.input.key.IKeyState;

public interface IInput
extends IConstInput, IKeyStateStoreConfig
{
	Collection<? extends IKeyState> getUnmodifiableKeyStates();
	IKeyState getKeyState(Object keyID);
}
