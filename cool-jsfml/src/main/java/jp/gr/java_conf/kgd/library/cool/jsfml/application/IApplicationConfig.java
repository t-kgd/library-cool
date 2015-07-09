package jp.gr.java_conf.kgd.library.cool.jsfml.application;

public interface IApplicationConfig
{
	boolean isClosedEventIgnored();
	void setClosedEventIgnored(boolean flag);

	boolean isUpdateNeedsFocus();
	void setUpdateNeedsFocus(boolean flag);
	
	int getTargetFPS();
	void setTargetFPS(int fps);
	
	/***
	 * SFMLでは最高FPSが垂直同期に依存しているので、いくらFPSを高く設定しても
	 * 速くなりませんが、フレームスキップを設定しておけば速さの上限が上がります。
	 * これは、早送りの実装の助けになります。デフォルト値は0（スキップなし）です。
 	 * @return
	 */
	int getDrawSkipInterval();
	void setDrawSkipInterval(int interval);
}
