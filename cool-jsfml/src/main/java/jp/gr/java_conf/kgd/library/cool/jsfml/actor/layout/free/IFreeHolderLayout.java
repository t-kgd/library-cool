package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.free;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.IHolderLayout;

/**
 * ホルダー型のレイアウト。
 * 更新時に内部への干渉が全くないホルダーです。
 * アフィン変換やカラーマスクは反映されるので、入れ子にするとノードを表現できます。
 * @author taisa
 *
 */
public interface IFreeHolderLayout
extends IHolderLayout
{

}
