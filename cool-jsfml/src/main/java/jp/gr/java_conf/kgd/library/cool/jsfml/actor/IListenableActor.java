package jp.gr.java_conf.kgd.library.cool.jsfml.actor;


/***
 * 更新操作を外部から設定できるアクターのインターフェースです。
 * つまり、更新時のアルゴリズムを差し替えが可能なことによって状態遷移などを表現できます。
 * また、実装としてはアクターのupdate時にリスナーのonUpdateを呼び出すことが期待されます。
 * @author taisa
 *
 */
public interface IListenableActor
{
	IOnUpdateListener getOnUpdateListener();
	void setOnUpdateListener(IOnUpdateListener listener);
}
