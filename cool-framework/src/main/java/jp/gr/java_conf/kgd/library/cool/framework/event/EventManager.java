package jp.gr.java_conf.kgd.library.cool.framework.event;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventManager
implements IEventManager
{
	private Map<Object, EventTuple> eventTuples = new LinkedHashMap<>();
	
	//
	@Override
	public boolean registerEventKey(Object key)
	{
		if(eventTuples.containsKey(key))
		{
			return false;
		}
		
		eventTuples.put(key, new EventTuple());
		return true;
	}
	
	@Override
	public boolean removeEventKey(Object key)
	{
		return eventTuples.remove(key) != null;
	}
	
	@Override
	public boolean addEventListener(Object key, IEventListener listener)
	{
		EventTuple eventTuple = eventTuples.get(key);
		
		if(eventTuple == null)
		{
			return false;
		}
		
		return eventTuple.listeners.add(listener);
	}
	
	@Override
	public boolean removeEventListener(Object key, IEventListener listener)
	{
		EventTuple eventTuple = eventTuples.get(key);
		
		if(eventTuple == null)
		{
			return false;
		}
		
		return eventTuple.listeners.remove(listener);
	}
	
	@Override
	public boolean dispatchEvent(IEvent event)
	{
		EventTuple eventTuple = eventTuples.get(event.getEventKey());
		
		if(eventTuple == null)
		{
			return false;
		}
		
		return eventTuple.events.add(event);
	}
	
	
	
	//
	@Override
	public void invokeEvents()
	{
		for(Object key : eventTuples.keySet())
		{
			EventTuple eventTuple = eventTuples.get(key);

			//イベントの方が空である場合が多い。
			for(IEvent event : eventTuple.events)
			{
				for(IEventListener listener : eventTuple.listeners)
				{				
					listener.invokeEvent(event);
				}
			}
			
			eventTuple.events.clear();
		}
	}
	
	
	//
	private static final class EventTuple
	{
		public final Set<IEventListener> listeners = new HashSet<>();	
		public final List<IEvent> events = new LinkedList<>();
	}
}
