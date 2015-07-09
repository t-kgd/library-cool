package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.IScene;
import jp.gr.java_conf.kgd.library.cool.util.IObjectBox;

public interface ISceneMoveData
extends IObjectBox, ITransitionsData, IConstSceneMoveData
{
	void setNextScene(IScene scene);	
	void setNextSceneName(String name);
	void setNextSceneClass(Class<? extends IScene> clazz);
	
	void setCurrentSceneDestroyed(boolean flag);
	
	void setRegisterID(int id);
	
	IObjectBox getMoveArgs();
	
}
