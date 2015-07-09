package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.ISceneMoveData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.factory.IConstSceneFactory;

public interface ISceneManagerConfig
{
	void setFirstScene(IScene firstScene);
	ISceneMoveData getFirstSceneMoveData();
	
	IConstSceneFactory getSceneFactory();
	void setSceneFactory(IConstSceneFactory sceneFactory);
}
