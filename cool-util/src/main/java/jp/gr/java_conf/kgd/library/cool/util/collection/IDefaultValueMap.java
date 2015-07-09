package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Map;

import jp.gr.java_conf.kgd.library.cool.util.function.IFunction;

public interface IDefaultValueMap<K, V>
extends Map<K, V>
{
	IFunction<? extends V> getDefaultValueCreator();
	void setDefaultValueCreator(IFunction<? extends V> creator);
	
	V put(K key);
}
