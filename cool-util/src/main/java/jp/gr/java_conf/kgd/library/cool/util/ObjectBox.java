package jp.gr.java_conf.kgd.library.cool.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectBox
implements IObjectBox, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6927821211232489198L;
	
	//
	private Map<String, Boolean> booleans;
	private Map<String, Integer> integers;
	private Map<String, Double> doubles;
	private Map<String, String> strings;
	
	private Map<String, Object> objects;

	private Map<String, IObjectBox> objectBoxes;
	
	//
	public boolean getBoolean(String key, boolean defaultValue)
	{
		if(booleans == null)
		{
			return defaultValue;
		}
		
		Boolean result = booleans.get(key);		
		return result != null ? result : defaultValue;
	}
	
	public int getInteger(String key, int defaultValue)
	{
		if(integers == null)
		{
			return defaultValue;
		}
		
		Integer result = integers.get(key);		
		return result != null ? result : defaultValue;
	}
	
	public double getDouble(String key, double defaultValue)
	{
		if(doubles == null)
		{
			return defaultValue;
		}
		
		Double result = doubles.get(key);		
		return result != null ? result : defaultValue;
	}
	
	public String getString(String key)
	{
		if(strings == null)
		{
			return null;
		}
		
		return strings.get(key);
	}
	
	public Object getObject(String key)
	{
		if(objects == null)
		{
			return null;
		}
		
		return objects.get(key);
	}

	@Override
	public Boolean put(String key, boolean value)
	{
		if(booleans == null)
		{
			booleans = new LinkedHashMap<>();
		}
	
		return booleans.put(key, value);
	}

	@Override
	public Integer put(String key, int value)
	{
		if(integers == null)
		{
			integers = new LinkedHashMap<>();
		}
	
		return integers.put(key, value);
	}

	@Override
	public Double put(String key, double value)
	{
		if(doubles == null)
		{
			doubles = new LinkedHashMap<>();
		}
	
		return doubles.put(key, value);
	}

	@Override
	public String put(String key, String value)
	{
		if(strings == null)
		{
			strings = new LinkedHashMap<>();
		}
		
		return strings.put(key, value);
	}

	@Override
	public Object putObject(String key, Object value)
	{
		if(objects == null)
		{
			objects = new LinkedHashMap<>();
		}
		
		return objects.put(key, value);
	}

	@Override
	public IObjectBox putObjectBox(String key, IObjectBox value)
	{
		if(objectBoxes == null)
		{
			objectBoxes = new LinkedHashMap<>();
		}
		
		return objectBoxes.put(key, value);
	}

	@Override
	public IObjectBox getObjectBox(String key)
	{
		if(objectBoxes == null)
		{
			return null;
		}
		
		return objectBoxes.get(key);
	}

}
