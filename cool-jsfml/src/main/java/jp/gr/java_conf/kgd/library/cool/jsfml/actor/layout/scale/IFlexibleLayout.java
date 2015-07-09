package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.scale;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.IHolderLayout;

import org.jsfml.system.Vector2f;

/**
 * ホルダー型レイアウト.
 * 自身のサイズと拡縮の設定値から内部のサイズを計算して
 * インナーアクターのサイズを設定します。
 * アニメーションが可能です。
 * @author taisa
 *
 */
public interface IFlexibleLayout
extends IHolderLayout
{
	Vector2f getInnerScale();
	
	void setInnerScale(Vector2f scale);
	void setInnerScale(float x, float y);
	
	
//	Vector2f getInnerOriginRatio();
//	
//	void setInnerOriginRatio(Vector2f originRatio);
//	void setInnerOriginRatio(float x, float y);
}
