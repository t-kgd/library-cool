package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import java.util.Collection;
import java.util.Collections;

import jp.gr.java_conf.kgd.library.cool.util.collection.ISharableKeySet;
import jp.gr.java_conf.kgd.library.cool.util.collection.ISharedKeyMapCreator;
import jp.gr.java_conf.kgd.library.cool.util.collection.SharableKeySet;

public class InputConfig
implements IInputConfig
{
	//
	public static final boolean DEFAULT_IS_WINDOW_FOCUS_IGNORED = false;
	
	//
	private boolean isWindowFocusIgnored = DEFAULT_IS_WINDOW_FOCUS_IGNORED;

	private ISharableKeySet<Object> keyIDs = new SharableKeySet<>();
	
	//
	@Override
	public boolean isWindowFocusIgnored()
	{
		return isWindowFocusIgnored;
	}

	@Override
	public void setWindowFocusIgnored(boolean flag)
	{
		this.isWindowFocusIgnored = flag;
	}
	
	//
	@Override
	public Collection<? extends Object> getUnmodifiableKeyIDs()
	{
		return Collections.unmodifiableCollection(keyIDs);
	}
	
	@Override
	public ISharedKeyMapCreator<Object> getKeyIDMapCreator()
	{
		return keyIDs;
	}
	
	@Override
	public Collection<Object> getKeyIDs()
	{
		return keyIDs;
	}
}
