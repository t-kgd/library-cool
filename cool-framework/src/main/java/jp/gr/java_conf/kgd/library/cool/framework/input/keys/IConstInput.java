package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.framework.input.key.IConstKeyState;

public interface IConstInput
extends IConstKeyStateStoreConfig
{
	IConstInputConfig getBindedInputConfig();
	
	//
	Collection<? extends IConstKeyState> getUnmodifiableKeyStates();
	IConstKeyState getKeyState(Object keyID);
	
	Object isAnyKeyPressed();
	Object isAnyKeyPressed(Collection<? extends Object> filterKeyIDs);
}
