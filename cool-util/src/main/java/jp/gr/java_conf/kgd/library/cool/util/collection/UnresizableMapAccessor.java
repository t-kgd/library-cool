package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public class UnresizableMapAccessor<K, V>
implements IUnresizableMapAccessor<K, V>
{
	private Map<K, V> inner;

	//
	public UnresizableMapAccessor(Map<K, V> map)
	{
		this.inner = map;
	}
	
	//
	@Override
	public V getValue(K key)
	{
		return inner.get(key);
	}

	@Override
	public Collection<K> getUnmodifaiableKeys()
	{
		return Collections.unmodifiableCollection(inner.keySet());
	}

	@Override
	public Collection<V> getUnmodifiableValues()
	{
		return Collections.unmodifiableCollection(inner.values());
	}
	
	@Override
	public Collection<Entry<K, V>> getUnmodifiableEntries()
	{
		return Collections.unmodifiableCollection(inner.entrySet());
	}

	@Override
	public boolean update(K key, V value)
	{
		if(!inner.containsKey(key))
		{
			return false;
		}
		
		inner.put(key, value);
		return true;
	}
}
