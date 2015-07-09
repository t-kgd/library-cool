package jp.gr.java_conf.kgd.library.cool.framework.event;

public interface IEventListenerRegister
{
	boolean addEventListener(Object key, IEventListener listener);
	boolean removeEventListener(Object key, IEventListener listener);
}
