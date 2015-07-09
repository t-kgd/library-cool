package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;



interface IChainStackScene
extends IScene
{
	IChainStackScene getChainPrevScene();
	void setChainPrevScene(IChainStackScene prevScene);
	
	IScene getCurrentScene();
	void setCurrentScene(IScene scene);
}
