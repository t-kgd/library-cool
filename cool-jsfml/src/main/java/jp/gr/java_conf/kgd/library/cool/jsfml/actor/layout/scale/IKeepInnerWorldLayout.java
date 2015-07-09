package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.scale;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.IHolderLayout;

import org.jsfml.system.Vector2f;

/**
 * ホルダー型レイアウトでり、最重要レイアウトの１つ.
 * 内部アクターのサイズを設定値通りになるように尊守しつつ、拡縮を駆使して
 * 外部アクターのサイズと合わせます。アクションゲームなどでは、
 * 基本的にこのレイアウトを使って中身の世界の比を保護する必要があります。
 * また、本来直接指定しなければならない位置・サイズに関する数値情報を仮想化する用途にも利用できます。
 * @author taisa
 *
 */
public interface IKeepInnerWorldLayout
extends IHolderLayout
{
	Vector2f getInnerSize();
	
	void setInnerSize(Vector2f size);
	void setInnerSize(float width, float height);
}
