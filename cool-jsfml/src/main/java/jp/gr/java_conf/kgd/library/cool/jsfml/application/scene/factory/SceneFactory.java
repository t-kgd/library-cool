package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.factory;

import java.util.HashMap;
import java.util.Map;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.IScene;

public class SceneFactory
implements ISceneFactory
{
	private Map<String, ISceneCreator> sceneCreatorMap = new HashMap<>();

	@Override
	public IScene createScene(String sceneName)
	{
		ISceneCreator creator = sceneCreatorMap.get(sceneName);
		
		return creator != null ? createScene(sceneName) : null;
	}

	@Override
	public boolean addScene(String sceneName, ISceneCreator creator)
	{
		if(sceneName == null || creator == null)
		{
			throw new IllegalArgumentException();
		}
		
		if(sceneCreatorMap.containsKey(sceneName))
		{
			return false;
		}
		
		sceneCreatorMap.put(sceneName, creator);
		return true;
	}

	@Override
	public boolean removeScene(String sceneName)
	{
		return sceneCreatorMap.remove(sceneName) != null;
	}
}
