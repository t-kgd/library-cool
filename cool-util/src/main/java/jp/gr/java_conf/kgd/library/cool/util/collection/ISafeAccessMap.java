package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Map;

public interface ISafeAccessMap<K, V>
extends Map<K, V>
{
	boolean putIfAbsent(K key, V value);
	
	V putIfPresent(K key, V value);
}
