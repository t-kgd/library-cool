package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.ITransition;
import jp.gr.java_conf.kgd.library.cool.util.IObjectBox;
import jp.gr.java_conf.kgd.library.cool.util.ObjectBox;

public class SceneResultData
implements ISceneResultData
{
	//委譲
	private ObjectBox objectBox = new ObjectBox();
	
	private TransitionsData transitionsData = new TransitionsData();
	
	
	//
	private int resultCode;

	private boolean isTransitionsOverWrite = false;

	//
	public SceneResultData()
	{
		this(RESULT_SUCCEED);
	}
	
	public SceneResultData(int resultCode)
	{
		this.resultCode = resultCode;
	}
	
	//
	@Override
	public int getResultCode()
	{
		return resultCode;
	}

	@Override
	public void setResultCode(int resultCode)
	{
		this.resultCode = resultCode;
	}

	@Override
	public IObjectBox getResultValues()
	{
		return this;
	}
	
	@Override
	public boolean isTransitionsOverWrite()
	{
		return isTransitionsOverWrite;
	}
	
	@Override
	public void setTransitionsOverWrite(boolean flag)
	{
		this.isTransitionsOverWrite = flag;
	}

	public boolean getBoolean(String key, boolean defaultValue)
	{
		return objectBox.getBoolean(key, defaultValue);
	}

	public int getInteger(String key, int defaultValue)
	{
		return objectBox.getInteger(key, defaultValue);
	}

	public double getDouble(String key, double defaultValue)
	{
		return objectBox.getDouble(key, defaultValue);
	}

	public String getString(String key)
	{
		return objectBox.getString(key);
	}

	public Object getObject(String key)
	{
		return objectBox.getObject(key);
	}

	public Boolean put(String key, boolean value)
	{
		return objectBox.put(key, value);
	}

	public Integer put(String key, int value)
	{
		return objectBox.put(key, value);
	}

	public Double put(String key, double value)
	{
		return objectBox.put(key, value);
	}

	public String put(String key, String value)
	{
		return objectBox.put(key, value);
	}

	public Object putObject(String key, Object value)
	{
		return objectBox.putObject(key, value);
	}

	public IObjectBox putObjectBox(String key, IObjectBox value)
	{
		return objectBox.putObjectBox(key, value);
	}

	public IObjectBox getObjectBox(String key)
	{
		return objectBox.getObjectBox(key);
	}

	public ITransition getOutTransition()
	{
		return transitionsData.getOutTransition();
	}

	public ITransition getInTransition()
	{
		return transitionsData.getInTransition();
	}

	public boolean isTransitionCrossed()
	{
		return transitionsData.isTransitionCrossed();
	}

	public int getTransitionInterval()
	{
		return transitionsData.getTransitionInterval();
	}

	public void setOutTransition(ITransition transition)
	{
		transitionsData.setOutTransition(transition);
	}

	public void setInTransition(ITransition transition)
	{
		transitionsData.setInTransition(transition);
	}

	public void setTransitionCrossed(boolean flag)
	{
		transitionsData.setTransitionCrossed(flag);
	}

	public void setTransitionInterval(int interval)
	{
		transitionsData.setTransitionInterval(interval);
	}
	
	
}
