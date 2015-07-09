package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.ratio;

import java.util.Map;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.IContainerLayout;

/**
 * コンテナ型レイアウトであり、最重要レイアウトの１つ.
 * 指定した「比」の値によってレイアウトすることができます。
 * これによってリサイズ時の処理を手動で行う必要がなくなります。
 * @author taisa
 *
 */
public interface IRatioLayout
extends IContainerLayout
{
	/**
	 * 比によるレイアウトを指定してアクターを追加する.
	 * ここで指定した比は、レイアウトのアップデート時に適用されます。
	 * また、比を指定しなかった場合のデフォルト値は空矩形なので、表示されません。
	 * その場合{@link #resetRatioRect(IActor, FloatRect)}で設定しなおしてください。
	 * @param actor
	 * @param ratioRect
	 * @return
	 */
	boolean addActor(IActor actor, FloatRect ratioRect);
	boolean addActor(IActor actor, Vector2f positionRatio, Vector2f sizeRatio);
	boolean addActor(IActor actor, float x, float y, float width, float height);
	
	/**
	 * 指定したキー値のアクターが既に登録されている場合、位置をそのアクターとの差分で登録します。
	 * サイズはそのまま登録されます。また指定値は登録時のみ有効であり、常にバインドされるわけではありません。
	 * @param actor
	 * @param key
	 * @param ratioRect
	 * @return
	 */
	boolean addActorStack(IActor actor, Object key, FloatRect ratioRect);
	boolean addActorStack(IActor actor, Object key, Vector2f positionRatio, Vector2f sizeRatio);
	boolean addActorStack(IActor actor, Object key, float x, float y, float width, float height);
	

	/**
	 * 指定したキー値のアクターが既に登録されている場合、位置をそのアクターとの差分で登録します。
	 * また差分としての開始位置を、上下左右の位置関係により指定することができます。
	 * サイズはそのまま登録されます。また指定値は登録時のみ有効であり、常にバインドされるわけではありません。
	 * @param actor
	 * @param key
	 * @param relativeSignX
	 * @param relativeSignY
	 * @param ratioRect
	 * @return
	 */
	boolean addActorRelative(IActor actor, Object key, int relativeSignX, int relativeSignY, FloatRect ratioRect);
	boolean addActorRelative(IActor actor, Object key, int relativeSignX, int relativeSignY, Vector2f positionRatio, Vector2f sizeRatio);
	boolean addActorRelative(IActor actor, Object key, int relativeSignX, int relativeSignY, float x, float y, float width, float height);
	
	
	FloatRect getRatioRect(Object key);
	
	/**
	 * 
	 * @param actor
	 * @param ratioRect
	 * @return actorが存在して値を更新した場合に真。存在しなかった場合は偽。
	 */
	boolean resetRatioRect(IActor actor, FloatRect ratioRect);
	boolean resetRatioRect(IActor actor, Vector2f positionRatio, Vector2f sizeRatio);
	boolean resetRatioRect(IActor actor, float x, float y, float width, float height);
	
	//
	/**
	 * 登録済みアクターのマップを取得.
	 * {@link UnresizableMap}は値の更新操作のみが可能なマップです。
	 * 要素数を変更する操作を行うことができないので注意してください。
	 * @return
	 */
	Map<IActor, FloatRect> getUnresizableRatioRectMap();
	
	//
	Vector2f getLayoutOriginRatio();
	
	/**
	 * 矩形の中心の設定.
	 * 比で指定する矩形の原点を、同じく比で指定します。
	 * この値はレイアウト中の全てのアクターに適用されます。
	 * ほとんどの場合、左上(0, 0)か中心(0.5f, 0.5f)を用いることになると思います。
	 * @param originRatio
	 */
	void setLayoutOriginRatio(Vector2f originRatio);
	void setLayoutOriginRatio(float x, float y);
}
