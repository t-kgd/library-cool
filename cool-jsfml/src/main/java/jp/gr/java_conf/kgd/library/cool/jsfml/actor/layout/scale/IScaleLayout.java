package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.scale;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.IHolderLayout;

/**
 * ホルダー型レイアウト.
 * 中身の拡縮とその原点を設定できるだけのレイアウトです。
 * @author taisa
 *
 */
public interface IScaleLayout
extends IHolderLayout
{
	Vector2f getInnerScale();
	
	void setInnerScale(Vector2f scale);
	void setInnerScale(float x, float y);
	
	
	Vector2f getInnerScaleOrigin();
	
	void setInnerScaleOrigin(Vector2f origin);
	void setInnerScaleOrigin(float x, float y);
}
