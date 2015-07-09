package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.IApplicationContext;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.ISceneMoveData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.factory.IConstSceneFactory;
import jp.gr.java_conf.kgd.library.cool.util.IConstObjectBox;

/**
 * アプリケーションのルートアクターとして扱われることを想定したシーンマネージャです。
 * 自身のシーンとしての機能は削られ、シーンが枯渇するとアプリケーションを閉じる設定になっています。
 * @author taisa
 *
 */
public class RootSceneManager
extends AbstractSceneManager
implements IRootSceneManager
{
	public RootSceneManager(IApplicationContext context)
	{
		super(context);
	}
	
	@Override
	public void setFirstScene(IScene firstScene)
	{
		super.setFirstScene(firstScene);
	}
	
	@Override
	public ISceneMoveData getFirstSceneMoveData()
	{
		return super.getFirstSceneMoveData();
	}
	
	@Override
	public IConstSceneFactory getSceneFactory()
	{
		return super.getSceneFactory();
	}
	
	@Override
	public void setSceneFactory(IConstSceneFactory sceneFactory)
	{
		super.setSceneFactory(sceneFactory);
	}
	
	//
	@Override
	final protected void onFinishScene()
	{
		getApplicationContext().endApplication();
	}

	@Override
	final public void startScene(IConstObjectBox args)
	{
	}

	@Override
	final public void resumeScene(int registerdID, int resultCode,
			IConstObjectBox resultValues)
	{
	}
}
