package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UnresizableMap<K, V>
implements Map<K, V>
{
	//
	private final Map<K, V> map;

	//
	UnresizableMap(Map<K, V> map)
	{
		this.map = Objects.requireNonNull(map);
	}

	//
	public int size()
	{
		return map.size();
	}

	public boolean isEmpty()
	{
		return map.isEmpty();
	}

	public boolean containsKey(Object key)
	{
		return map.containsKey(key);
	}

	public boolean containsValue(Object val)
	{
		return map.containsValue(val);
	}

	public V get(Object key)
	{
		return map.get(key);
	}

	public V put(K key, V value)
	{
		if(!map.containsKey(key))
		{
//			throw new IllegalArgumentException();
			//例外は困るかもしれない。
			//戻り値がnullだった場合、その意味をcontainsKeyで確認する方が安全。
			
			return null;
		}

		return map.put(key, value);
	}

	public V remove(Object key)
	{
		throw new UnsupportedOperationException();
	}

	public void putAll(Map<? extends K, ? extends V> m)
	{
		for(Entry<? extends K, ? extends V> entry : m.entrySet())
		{
			put(entry.getKey(), entry.getValue());
		}
	}

	public void clear()
	{
		throw new UnsupportedOperationException();
	}

	private transient Set<K> keySet = null;
	private transient Set<Map.Entry<K, V>> entrySet = null;
	private transient Collection<V> values = null;

	public Set<K> keySet()
	{
		if(keySet == null)
		{
			keySet = Collections.unmodifiableSet(map.keySet());
		}

		return keySet;
	}

	public Set<Map.Entry<K, V>> entrySet()
	{
		if(entrySet == null)
		{
			entrySet = Collections.unmodifiableSet(map.entrySet());
		}

		return entrySet;
	}

	public Collection<V> values()
	{
		if(values == null)
		{
			values = Collections.unmodifiableCollection(map.values());
		}

		return values;
	}

	public boolean equals(Object o)
	{
		return o == this || map.equals(o);
	}

	public int hashCode()
	{
		return map.hashCode();
	}

	public String toString()
	{
		return map.toString();
	}
}
