package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.ActorHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

/***
 * シーンとしての機能を果たす、抽象シーンクラス.
 * ※シーンマネージャから実行されることが大前提の抽象クラスです。
 * なので、重要なこととして、このクラスのシーン遷移の機能はシーンマネージャの管理下でなければ
 * 全く有効に働かないことを忘れてはいけません。※<br>
 * <br>
 * フロントエンドの利用者が基本的なシーンクラスを作るために使うことができる抽象クラスです。
 * セットしたルートアクターは自動で更新・描画されます。
 * シーン開始時にコンテナをルートとしてセットしておいて、その内容物を
 * アップデート時に操作することを想定しています。
 * 作成したシーンを再生したい場合は、アプリケーションのルートに登録されたシーンマネージャの
 * 初期シーンに設定するか、または既にそのように設定されたシーン上の更新メソッド内から
 * 遷移するようにしてください。<br>
 * <br>
 * 基本的なレイアウト方法としては、まずルートアクターに{@link IKeepInnerWorldLayout}をセットして、
 * 内部世界の大きさを定義します。さらにそのインナーとして{@link IRatioLayout}を用いてレイアウトすることで
 * それぞれのサイズ変更（シーンクラス自体のサイズ変更と、シーン内部の世界のサイズ変更）に自動的に対応できます。
 * @author taisa
 *
 */
public abstract class AbstractScene
extends AbstractParentScene
{
	private IActor rootActor;
	
	/**
	 * コンストラクタ.
	 * コンテキストはこのコンストラクタが呼ばれた後にシーンマネージャがセットしてくれます。
	 * なのでコンストラクタ中ではまだコンテキストを使用することはできません。
	 * コンテキストが必要になる初期化は
	 * {@link #startScene(IConstObjectBox)}中で
	 * 行なってください。
	 */
	protected AbstractScene()
	{
	}
	
	//
	protected IActor getRootActor()
	{
		return rootActor;
	}
	
	/**
	 * ルートアクターのセット.
	 * シーンのルートアクターを設定します.
	 * セットしたルートアクターは{@link #onUpdateScene()}後に自動的に更新・描画されます。
	 * 更新前にはアフィン変換やカラーマスクなどの描画ステートがリセットされる他、
	 * シーンのサイズとルートアクターのサイズが違う場合は強制的にシーンと同じサイズに設定されます。
	 * また、アニメーションも無効になりますので、アニメーションを使いたい場合は、
	 * 内部アクターのアニメーションを許容するコンテナ、またはホルダーをルートとして設定すると良いです。
	 * @param rootActor
	 */
	protected void setRootActor(IActor rootActor)
	{
		this.rootActor = rootActor;
	}
	
	//
	@Override
	final protected void onUpdateSceneChildren()
	{
		if(rootActor != null)
		{		
			ActorHelper.updateStrictFixInnerActor(rootActor, this);
		}
	}

	@Override
	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		if(rootActor != null)
		{
			rootActor.draw(target, states, mask);
		}
	}
	//
}
