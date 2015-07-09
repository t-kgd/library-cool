package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.factory;

public interface ISceneRegister
{
	boolean addScene(String sceneName, ISceneCreator creator);	
	boolean removeScene(String sceneName);
}
