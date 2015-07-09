package jp.gr.java_conf.kgd.library.cool.framework.factory;

public interface IConstFactory<T>
{
	T create(Object key, Object... args);
}
