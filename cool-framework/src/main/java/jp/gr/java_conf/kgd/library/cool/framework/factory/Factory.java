package jp.gr.java_conf.kgd.library.cool.framework.factory;

import java.util.LinkedHashMap;

public class Factory<T> extends LinkedHashMap<Object, ICreator<T>> implements IFactory<T> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3209023081399091484L;

	@Override
	public T create(Object key, Object... args)
	{
		ICreator<T> creator = get(key);
		
		return creator != null ? creator.create(args) : null;
	}
}
