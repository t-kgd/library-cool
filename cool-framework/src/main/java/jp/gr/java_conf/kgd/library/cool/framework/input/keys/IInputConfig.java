package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import java.util.Collection;

public interface IInputConfig
extends IConstInputConfig
{
	void setWindowFocusIgnored(boolean flag);
	
	Collection<Object> getKeyIDs();
}
