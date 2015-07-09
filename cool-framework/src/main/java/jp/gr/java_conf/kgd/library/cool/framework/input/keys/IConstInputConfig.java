package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.util.collection.ISharedKeyMapCreator;

public interface IConstInputConfig
{
	boolean isWindowFocusIgnored();
	
	Collection<? extends Object> getUnmodifiableKeyIDs();
	ISharedKeyMapCreator<Object> getKeyIDMapCreator();
}
