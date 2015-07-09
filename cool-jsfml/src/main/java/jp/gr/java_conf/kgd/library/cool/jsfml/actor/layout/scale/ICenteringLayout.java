package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.scale;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.IHolderLayout;

/**
 * ホルダー型レイアウト.
 * 中央揃えを可能にするレイアウトです。
 * 内部アクターを指定されたサイズに設定して自身の中央に配置します。
 * 拡縮を行わないので内部アクターがはみ出すことがあります。
 * @author taisa
 *
 */
public interface ICenteringLayout
extends IHolderLayout
{
	Vector2f getInnerSize();
	
	void setInnerSize(Vector2f size);
	void setInnerSize(float width, float height);
}
