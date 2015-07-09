package jp.gr.java_conf.kgd.library.cool.jsfml.actor;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponent;

/**
 * アクターはアニメーションが可能ですが、実際にアニメーションできるかどうかは
 * そのアクターを管理しているクラスに委ねられます。なので保証できるアニメーションは
 * 自身のアニメーションではなく、自身が管理するアクターのアニメーションだけになります。
 * @author taisa
 *
 */
public interface IActor
extends IComponent, IUpdatable, IActorState
{
}
