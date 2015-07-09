package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.free;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.container.IActorContainer;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu.ISelectMenu;

/**
 * アクターコンテナの内容物にフォーカスを付与する擬似メニュー.
 * あらゆるアクターコンテナをセットすることができますが、機能に乏しいです。
 * また、本ライブラリのアクターはノードのように親を保持していないので、ローカル座標を
 * 任意階層の空間座標に変換することができないため、メニュー間で
 * フォーカス移動を行うのが難しいです。
 * @author taisa
 *
 */
public interface IContainerSelectMenu
extends ISelectMenu
{
	IActorContainer getActorContainer();
	void setActorContainer(IActorContainer container);
}
