package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.LinkedHashMap;

public class SafeAccessMap<K, V>
extends LinkedHashMap<K, V>
implements ISafeAccessMap<K, V>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1063230070487071891L;

	@Override
	public boolean putIfAbsent(K key, V value)
	{
		if(super.containsKey(key))
		{
			return false;
		}
		
		super.put(key, value);
		return true;
	}

	@Override
	public V putIfPresent(K key, V value)
	{
		if(!super.containsKey(key))
		{
			return null;
		}
			
		return super.put(key, value);
	}

}
