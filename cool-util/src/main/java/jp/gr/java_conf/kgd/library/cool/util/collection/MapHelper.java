package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import jp.gr.java_conf.kgd.library.cool.util.function.IFunction;

public class MapHelper
{
	protected MapHelper()
	{
	}

	//
	public static <K, V> Map<K, V> createMap(Collection<? extends K> keys)
	{
		Map<K, V> map = new LinkedHashMap<>();
		
		for(K key : keys)
		{
			map.put(key, null);
		}
		
		return map;
	}

	public static <K, V, S extends V> Map<K, V> createMap(Collection<? extends K> keys, IFunction<? extends S> defaultValueCreator)
	{
		Map<K, V> map = new LinkedHashMap<>();

		for(K key : keys)
		{
			map.put(key, defaultValueCreator.apply());
		}

		return map;
	}
	
	//
	public static <K, V> Map<K, V> putAllKey(Map<K, V> map, Collection<? extends K> keys)
	{
		for(K key : keys)
		{
			map.put(key, null);
		}
		
		return map;
	}

	public static <K, V, S extends V> Map<K, V> putAllKey(Map<K, V> map, Collection<? extends K> keys, IFunction<? extends S> defaultValueCreator)
	{
		for(K key : keys)
		{
			map.put(key, defaultValueCreator.apply());
		}

		return map;
	}
}
