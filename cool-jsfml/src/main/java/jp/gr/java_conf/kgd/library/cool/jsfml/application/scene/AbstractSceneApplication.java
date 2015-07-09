package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;

import org.jsfml.window.VideoMode;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.AbstractApplication;

/**
 * あらかじめルートアクターにシーンマネージャが設定された抽象アプリケーションクラスです。
 * コンストラクタ内で初期シーンと初期トランジションを設定してください。
 * @author taisa
 *
 */
public abstract class AbstractSceneApplication
extends AbstractApplication
{
	private RootSceneManager rootSceneManager;

	//
	public AbstractSceneApplication()
	{
		commonInit();
	}
	
	public AbstractSceneApplication(VideoMode videoMode)
	{
		super(videoMode);
		
		commonInit();
	}
	
	private final void commonInit()
	{
		rootSceneManager = new RootSceneManager(this);
	}
	
	
	//
	protected void setFirstScene(IScene firstScene)
	{
		rootSceneManager.setFirstScene(firstScene);
	}
	
//	protected ISceneMoveData getFirstSceneMoveData()
//	{
//		return rootSceneManager.getFirstSceneMoveData();
//	}
	
	//
	protected ISceneManagerConfig getRootSceneManagerConfig()
	{
		return rootSceneManager;
	}
	
	//
	@Override
	final protected IActor getRootActor()
	{
		return rootSceneManager;
	}
	
	@Override
	final protected void onUpdate()
	{
	}
}
