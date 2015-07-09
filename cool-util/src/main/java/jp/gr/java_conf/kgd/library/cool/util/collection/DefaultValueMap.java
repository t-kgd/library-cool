package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.LinkedHashMap;
import java.util.Map;

import jp.gr.java_conf.kgd.library.cool.util.function.IFunction;

public class DefaultValueMap<K, V>
extends LinkedHashMap<K, V>
implements IDefaultValueMap<K, V>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4747713408631969968L;
	
	//
	private IFunction<? extends V> defaultValueCreator;
	
	//
	public DefaultValueMap(IFunction<? extends V> creator)
	{
		this.defaultValueCreator = creator;
	}
	
	public DefaultValueMap()
	{
	}

	public DefaultValueMap(int initialCapacity, float loadFactor,
			boolean accessOrder)
	{
		super(initialCapacity, loadFactor, accessOrder);
	}

	public DefaultValueMap(int initialCapacity, float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}

	public DefaultValueMap(int initialCapacity)
	{
		super(initialCapacity);
	}

	public DefaultValueMap(Map<? extends K, ? extends V> m)
	{
		super(m);
	}

	//
	@Override
	public IFunction<? extends V> getDefaultValueCreator()
	{
		return defaultValueCreator;
	}
	
	@Override
	public void setDefaultValueCreator(IFunction<? extends V> creator)
	{
		this.defaultValueCreator = creator;
	}
	
	//
	@Override
	final public V put(K key)
	{
		return this.put(key, null);
	}
	
	@Override
	public V put(K key, V value)
	{
		if(value == null)
		{
			if(defaultValueCreator != null)
			{
				return super.put(key, defaultValueCreator.apply());
			}
		}
		
		return super.put(key, value);
	}
}
