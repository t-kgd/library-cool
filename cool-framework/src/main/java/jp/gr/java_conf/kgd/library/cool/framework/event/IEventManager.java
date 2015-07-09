package jp.gr.java_conf.kgd.library.cool.framework.event;

public interface IEventManager
extends ISealedEventManager
{
	boolean removeEventKey(Object key);

	void invokeEvents();
}
