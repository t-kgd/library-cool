package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.IScene;
import jp.gr.java_conf.kgd.library.cool.util.IConstObjectBox;

public interface IConstSceneMoveData
extends IConstObjectBox, IConstTransitionsData
{
	IScene getNextScene();
	String getNextSceneName();
	Class<? extends IScene> getNextSceneClass();
	
	boolean isCurrentSceneDestroyed();
	
	int getRegisterID();
	
	IConstObjectBox getMoveArgs();
}
