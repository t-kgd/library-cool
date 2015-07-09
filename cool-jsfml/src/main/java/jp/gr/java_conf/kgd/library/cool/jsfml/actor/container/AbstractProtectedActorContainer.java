package jp.gr.java_conf.kgd.library.cool.jsfml.actor.container;

import java.util.Collection;
import java.util.LinkedHashSet;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/***
 * アクターコンテナの機能を外部から隠蔽したまま継承先のクラスに提供する抽象クラスです。
 * コンテナに加えたアクターの更新・描画は、コンテナの更新・描画時に連動して行われます。
 * なので２度以上更新が行われないように、階層関係には注意してください。
 * また、頻繁にaddとremoveを繰り返すような操作をしなくてはならない場合は、
 * アクター側のsetActiveとsetVisibleでコントロールする方が良いと思われます。
 * @author taisa
 *
 */
public abstract class AbstractProtectedActorContainer
extends AbstractParentActor
{
	private Collection<IActor> children;

	//
	protected AbstractProtectedActorContainer()
	{
	}
		
	//
	/**
	 * 実装コレクションの生成.
	 * このメソッドをオーバーライドすることで、実装に使うコンテナを指定できます。
	 * ただし、アクターは原則的に１フレームに２度アップデートしてはいけないため、
	 * 他のコレクションの機構が特別必要ない状況ならば基本的に重複禁止のSetが無難です。
	 * それでも、１つのアクターが複数の別々のコンテナに格納されてしまうことは十分に注意しなくてはなりません。
	 * @return
	 */
	protected Collection<IActor> createActualContainer()
	{
		return new LinkedHashSet<>();
	}
	
	//
	protected Collection<IActor> getChildren()
	{
		if(children == null)
		{
			children = createActualContainer();
		}
		
		return children;
	}
	
	protected boolean addChild(IActor actor)
	{
		if(children == null)
		{
			children = createActualContainer();
		}
		
		return children.add(actor);
	}
	
	protected boolean removeChild(Object key)
	{
		if(children == null)
		{
			children = createActualContainer();
		}
		
		return children.remove(key);
	}
	
	
	//
	@Override
	final protected void onUpdateChildren()
	{
		if(children != null)
		{
			for(IActor actor : children)
			{
				onUpdateChild(actor);
			}
		}
	}
	
	/**
	 * 子アクターの更新方法を定義します。
	 * 基本的にはそのまま更新されますが、アニメーションを無効にしたい場合などは
	 * オーバーライドしてください。その際、２度更新が行われないように、
	 * オーバーライド前の親のメソッドは呼び出さないでください。
	 * @param child
	 */
	protected void onUpdateChild(IActor child)
	{
		child.update();
	}

	
	@Override
	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		if(children != null)
		{
			for(IActor actor : children)
			{
				actor.draw(target, states, mask);
			}
		}
	}
}
