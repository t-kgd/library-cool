package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import jp.gr.java_conf.kgd.library.cool.util.function.IFunction;

public class SharableKeySet<E>
extends LinkedHashSet<E>
implements ISharableKeySet<E>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4859023356532069913L;
	
	//通知先を保存するためのMap
//	private WeakHashMap<Map<E, ?>, Object> sharedKeyMaps = new WeakHashMap<>();
	private WeakHashMap<Object, WeakReference<Map<E, ?>>> sharedKeyMaps = new WeakHashMap<>();
	
	//
	public SharableKeySet()
	{
		super();
	}

	public SharableKeySet(Collection<? extends E> c)
	{
		super(c);
	}

	public SharableKeySet(int initialCapacity, float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}

	public SharableKeySet(int initialCapacity)
	{
		super(initialCapacity);
	}

	//
	@Override
	final public <V> Map<E, V> createSharedKeyMap()
	{
		return this.createSharedKeyMap(null);
	}
	
	@Override
	public <V, S extends V> Map<E, V> createSharedKeyMap(
			IFunction<? extends S> defaultValueCreator)
	{
		IdentityDefaultValueMap<E, V> map = new IdentityDefaultValueMap<E, V>(defaultValueCreator);

		for(E e : this)
		{
			map.put(e, null);
		}
		
//		sharedKeyMaps.put(map, null);
		
		sharedKeyMaps.put(map.identity, new WeakReference<Map<E, ?>>(map));

		return CollectionHelper.unresizableMap(map);
	}
	
//	@Override
//	public <V> Map<E, V> createSharedKeyMap(IFunction<? extends V> defaultValueCreator)
//	{
//		Map<E, V> map = new DefaultValueMap<>(defaultValueCreator);
//
//		for(E e : this)
//		{
//			map.put(e, null);
//		}
//		
//		sharedKeyMaps.put(map, null);
//
//		return CollectionHelper.unvariableMap(map);
//	}

	@Override
	public boolean add(E e)
	{
		if(!super.add(e))
		{
			return false;
		}
		
//		for(Map<E, ?> map : sharedKeyMaps.keySet())
//		{
//			map.put(e, null);
//		}
		
//		List<Map<E, ?>> list = new LinkedList<>();
//		
//		for(Map<E, ?> map : sharedKeyMaps.keySet())
//		{
//			list.add(map);
//		}
//		
//		for(Map<E, ?> map : list)
//		{			
//			map.put(e, null);
//		}
		
		List<Map<E, ?>> list = new LinkedList<>();
		
		for(Object key : sharedKeyMaps.keySet())
		{
			list.add(sharedKeyMaps.get(key).get());
		}
		
		for(Map<E, ?> map : list)
		{
			if(map.containsKey(e))
			{
				continue;
			}
			
			map.put(e, null);
		}
		
		return true;
	}
	
	@Override
	public boolean remove(Object o)
	{
		if(!super.remove(o))
		{
			return false;
		}
		
//		for(Map<E, ?> map : sharedKeyMaps.keySet())
//		{
//			map.remove(o);
//		}
		
//		List<Map<E, ?>> list = new LinkedList<>();
//		
//		for(Map<E, ?> map : sharedKeyMaps.keySet())
//		{
//			list.add(map);
//		}
//		
//		for(Map<E, ?> map : list)
//		{			
//			map.remove(o);
//		}
		
		List<Map<E, ?>> list = new LinkedList<>();
		
		for(Object key : sharedKeyMaps.keySet())
		{
			list.add(sharedKeyMaps.get(key).get());
		}
		
		for(Map<E, ?> map : list)
		{			
			map.remove(o);
		}
		
		return true;
	}
	
	//
	private static final class IdentityDefaultValueMap<K, V>
	extends DefaultValueMap<K, V>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7254106910506031619L;
		
		//
		public final Object identity = new Object();
		
		//
		public IdentityDefaultValueMap(IFunction<? extends V> creator)
		{
			super(creator);
		}

//		@Override
//		public boolean equals(Object o)
//		{
//			return this == o;
//		}
	}
}
