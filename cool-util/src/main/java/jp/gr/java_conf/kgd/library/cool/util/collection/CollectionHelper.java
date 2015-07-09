package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Map;

public class CollectionHelper
{
	protected CollectionHelper()
	{
	}

	//
	public static <K, V> Map<K, V> unresizableMap(Map<K, V> map)
	{
		return new UnresizableMap<>(map);
	}
}
