package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.IApplicationActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.IConstSceneMoveData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.IConstSceneResultData;
import jp.gr.java_conf.kgd.library.cool.util.IConstObjectBox;

/**
 * シーン遷移の１単位となるシーンのインターフェースです。
 * インターフェースですが、メソッドの機能は基本実装への依存が強いので、
 * まず、抽象シーンクラスを継承してから、各メソッドを実装してください。
 * @author taisa
 *
 */
public interface IScene
extends IApplicationActor
{
	void startScene(IConstObjectBox args);
	
	/**
	 * シーンの再開.
	 * 遷移先のシーンから戻って来るときに呼び出されます。
	 * このメソッド中で再度シーンを終了させることによって、このシーンの
	 * トランジションアニメーションを飛ばして更に前のシーンに戻ることができます。
	 * @param registerdID
	 * @param resultCode
	 * @param resultValues
	 */
	void resumeScene(int registerdID, int resultCode, IConstObjectBox resultValues);

	//
	SceneMoveState getSceneMoveState();
	IConstSceneMoveData getSceneMoveData();
	IConstSceneResultData getSceneResultData();
	
	/**
	 * 遷移状態・予約の解除.
	 * シーンマネージャにとっては遷移状態のリセット。
	 * シーン中で使う場合には遷移予約の解除ができます。
	 */
	void clearMoveSceneState();
}
