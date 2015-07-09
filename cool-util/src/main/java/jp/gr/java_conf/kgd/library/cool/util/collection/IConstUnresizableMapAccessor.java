package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Collection;
import java.util.Map;

public interface IConstUnresizableMapAccessor<K, V>
{
	V getValue(K key);
	
	Collection<K> getUnmodifaiableKeys();
	Collection<V> getUnmodifiableValues();
	Collection<Map.Entry<K, V>> getUnmodifiableEntries();
}
