package jp.gr.java_conf.kgd.library.cool.util.collection;

public interface IUnresizableMapAccessor<K, V>
extends IConstUnresizableMapAccessor<K, V>
{
	boolean update(K key, V value);
}
