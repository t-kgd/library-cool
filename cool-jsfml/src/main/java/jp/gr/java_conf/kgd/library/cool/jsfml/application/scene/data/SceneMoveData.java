package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.IScene;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.ITransition;
import jp.gr.java_conf.kgd.library.cool.util.IObjectBox;
import jp.gr.java_conf.kgd.library.cool.util.ObjectBox;

public class SceneMoveData
implements ISceneMoveData
{
	private ObjectBox objectBox = new ObjectBox();
	
	//
	private IScene nextScene;
	private String nextSceneName;
	private Class<? extends IScene> nextSceneClass;
	
	private boolean isCurrentSceneDestroyed = false;
	
	private int registerID;
	
	private TransitionsData transitionsData = new TransitionsData();
	
	//
	public SceneMoveData()
	{
		this(false);
	}
	
	public SceneMoveData(IScene nextScene)
	{
		this(nextScene, false);
	}
	
	public SceneMoveData(String nextSceneName)
	{
		this(nextSceneName, false);
	}
	
	public SceneMoveData(boolean isCurrentSceneDestroyed)
	{
		this.isCurrentSceneDestroyed = isCurrentSceneDestroyed;
	}
	
	public SceneMoveData(IScene nextScene, boolean isCurrentSceneDestroyed)
	{
		this.nextScene = nextScene;
		this.isCurrentSceneDestroyed = isCurrentSceneDestroyed;
	}
	
	public SceneMoveData(String nextSceneName, boolean isCurrentSceneDestroyed)
	{
		this.nextSceneName = nextSceneName;
		this.isCurrentSceneDestroyed = isCurrentSceneDestroyed;
	}
	
	public SceneMoveData(String nextSceneName, int registerID, boolean isCurrentSceneDestroyed)
	{
		this.nextSceneName = nextSceneName;
		this.registerID = registerID;
		this.isCurrentSceneDestroyed = isCurrentSceneDestroyed;
	}
	
	public SceneMoveData(IScene nextScene, int registerID, boolean isCurrentSceneDestroyed)
	{
		this.nextScene = nextScene;
		this.registerID = registerID;
		this.isCurrentSceneDestroyed = isCurrentSceneDestroyed;
	}
	
	//
	@Override
	public IScene getNextScene()
	{
		return nextScene;
	}

	@Override
	public String getNextSceneName()
	{
		return nextSceneName;
	}
	
	public Class<? extends IScene> getNextSceneClass()
	{
		return nextSceneClass;
	}

	@Override
	public boolean isCurrentSceneDestroyed()
	{
		return isCurrentSceneDestroyed;
	}

	@Override
	public int getRegisterID()
	{
		return registerID;
	}


	@Override
	public void setNextScene(IScene scene)
	{
		this.nextScene = scene;
	}
	
	@Override
	public void setNextSceneName(String name)
	{
		this.nextSceneName = name;
	}
	
	public void setNextSceneClass(Class<? extends IScene> clazz)
	{
		this.nextSceneClass = clazz;
	}
	
	@Override
	public void setCurrentSceneDestroyed(boolean flag)
	{
		this.isCurrentSceneDestroyed = flag;
	}

	@Override
	public void setRegisterID(int id)
	{
		this.registerID = id;
	}

	@Override
	public IObjectBox getMoveArgs()
	{
		return objectBox;
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

	public void setOutTransition(ITransition transition)
	{
		transitionsData.setOutTransition(transition);
	}

	public ITransition getInTransition()
	{
		return transitionsData.getInTransition();
	}

	public boolean isTransitionCrossed()
	{
		return transitionsData.isTransitionCrossed();
	}

	public void setInTransition(ITransition transition)
	{
		transitionsData.setInTransition(transition);
	}

	public int getTransitionInterval()
	{
		return transitionsData.getTransitionInterval();
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
