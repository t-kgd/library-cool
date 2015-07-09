package jp.gr.java_conf.kgd.library.cool.jsfml.actor.container;

import java.util.List;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;

/**
 * 実装にリストを利用するアクターコンテナ.
 * @author taisa
 *
 */
public interface IActorList
extends IActorContainer
{
	@Override
	List<IActor> getActors();
}
