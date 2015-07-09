package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition;

/**
 * シーン遷移におけるアニメーションを定義できます。
 * 退場と入場のアニメーションに対称性があることが望ましいです。
 * @author taisa
 *
 */
public interface ITransition
extends ITransitionState
{
	boolean startTransition();
	boolean startTransition(boolean isOutTransition);
	
	void stopTransition();
	void resumeTransition();
	
	/**
	 * 
	 * @return トランジションが継続している場合はtrue
	 */
	boolean advanceTransition();
	int getAdvancedTime();
}
