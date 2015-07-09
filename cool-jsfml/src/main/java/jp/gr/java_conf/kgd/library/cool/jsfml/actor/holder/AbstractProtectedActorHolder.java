package jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.ActorHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

/***
 * アクターホルダーとしての機能を外部から隠蔽したまま継承先クラスに提供する抽象クラスです。
 * インナーは基本的に状態操作やアニメーションが無効になります。
 * @author taisa
 *
 */
public abstract class AbstractProtectedActorHolder
extends AbstractParentActor
implements IActor
{
	private IActor innerActor;
	
	//
	protected AbstractProtectedActorHolder()
	{
	}
	
	//
	protected IActor getInnerActor()
	{		
		return innerActor;
	}

	protected void setInnerActor(IActor actor)
	{
		this.innerActor = actor;
	}
	
	//
	/***
	 * 内部アクターに対する基本的な更新操作を定義しています。
	 * このメソッドではアクターを一度アップデートするので、
	 * オーバーライドする場合は、２度アップデートしないように注意してください。
	 * つまりこのメソッド中では、親のメソッドを呼びださいことが基本となります。
	 */
	protected void onUpdateInnerActor()
	{	
		ActorHelper.updateStrictFixInnerActor(innerActor, this);
	}
	
	protected void onDrawInnerActor(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		innerActor.draw(target, states, mask);
	}
}
