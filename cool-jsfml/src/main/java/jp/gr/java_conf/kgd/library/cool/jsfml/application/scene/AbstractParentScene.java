package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.AbstractApplicationActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.IApplicationActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.IApplicationContext;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.IConstSceneMoveData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.IConstSceneResultData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.SceneResultData;

/***
 * さらなる抽象シーンクラスに派生させることを意識した抽象シーンクラスです。
 * 自由な更新・描画が可能なので継承して具象クラスを作るのにも向いていますが、
 * その際の注意点はAbstractParentActorと同じです。
 * さらに、シーンについて必要な機能や、より利便性を増すための制限などが少ないため、
 * このままではシーンクラスとして使うには難しいです。
 * @author taisa
 *
 */
public abstract class AbstractParentScene
extends AbstractApplicationActor
implements IScene
{
	private SceneMoveState sceneMoveState = SceneMoveState.CONTINUE;
	
	private IConstSceneMoveData moveData;
	private IConstSceneResultData resultData;


	//
	protected AbstractParentScene()
	{
	}
	
	protected AbstractParentScene(IApplicationActor other)
	{
		super(other);
	}

	protected AbstractParentScene(IApplicationContext context)
	{
		super(context);
	}
	
	
	//
	@Override
	public SceneMoveState getSceneMoveState()
	{
		return sceneMoveState;
	}

	@Override
	public IConstSceneMoveData getSceneMoveData()
	{
		return moveData;
	}

	@Override
	public IConstSceneResultData getSceneResultData()
	{
		return resultData;
	}
	
	@Override
	public void clearMoveSceneState()
	{
		setSceneState(SceneMoveState.CONTINUE);
		
		moveData = null;
		resultData = null;
	}
	
	
	
	
	//
	@Override
	final protected void onUpdate()
	{
		if(getSceneMoveState() == SceneMoveState.CONTINUE)
		{
			onUpdateScene();
		}
		//ここで弾くことでトランジション中にアニメーションだけをアップデートできる。
	}
	
	/**
	 * シーンの更新.
	 * ここでシーンを更新してください。このメソッドはトランジション中は呼び出されません。
	 */
	abstract protected void onUpdateScene();
	
	@Override
	final protected void onUpdateChildren()
	{
		if(getSceneMoveState() == SceneMoveState.CONTINUE)
		{
			onUpdateSceneChildren();
		}
	}
	
	/**
	 * シーンの子アクターの更新.
	 * 子アクターの更新はこちらで一括して行うと良いです。
	 */
	abstract protected void onUpdateSceneChildren();
	
	
	
	/**
	 * シーン遷移の予約.
	 * moveDataに遷移先シーンやトランジションを設定することによって、
	 * シーン遷移を実現できます。
	 * @param moveData
	 * @return
	 */
	protected boolean moveNextScene(IConstSceneMoveData moveData)
	{
		if(moveData == null)
		{
			return false;
		}
		
		if(moveData.getNextScene() == null && moveData.getNextSceneName() == null)
		{
			return false;
		}
		
		setMoveData(moveData);
		setSceneState(SceneMoveState.MOVE);
		
		return true;
	}
	
	
	/**
	 * シーン終了の予約.
	 * 簡易的にシーンを終了できます。空の戻り値データに成功コードを付与したものを戻します。
	 */
	final protected void finishScene()
	{
		finishScene(DEFAULT_SUCCEED_RESULT_DATA);
	}
	
	/**
	 *  シーン終了の予約.
	 *  引数に、結果コードや戻り値を格納して遷移元のシーンに戻すことができます。
	 *  そのままであれば、自動的に遷移時のトランジションアニメーションが使われますが、
	 *  任意のトランジションを上書きして設定することもできます。
	 * @param resultData
	 */
	protected void finishScene(IConstSceneResultData resultData)
	{
		if(resultData == null)
		{
			setResultData(DEFAULT_SUCCEED_RESULT_DATA);
		}
		else
		{
			setResultData(resultData);
		}

		setSceneState(SceneMoveState.FINISHED);
	}
	
	//
	private static final IConstSceneResultData DEFAULT_SUCCEED_RESULT_DATA = new SceneResultData();
	
	//
	private void setSceneState(SceneMoveState sceneState)
	{
		this.sceneMoveState = sceneState;
	}
	
	private void setMoveData(IConstSceneMoveData moveData)
	{
		this.moveData = moveData;
	}
	
	private void setResultData(IConstSceneResultData resultData)
	{
		this.resultData = resultData;
	}
}
