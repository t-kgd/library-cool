package jp.gr.java_conf.kgd.library.cool.framework.factory;

public interface ICreator<T>
{
	T create(Object... args);
}
