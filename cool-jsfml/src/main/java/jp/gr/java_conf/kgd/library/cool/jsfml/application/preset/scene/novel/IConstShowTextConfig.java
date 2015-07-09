package jp.gr.java_conf.kgd.library.cool.jsfml.application.preset.scene.novel;

public interface IConstShowTextConfig
{
	int getTextLineCharacterSize();
	int getTextLineNum();
	
	/**
	 * １文字表示するのに消費するフレーム数。0でページ全体を瞬時に表示する。
	 * @return
	 */
	float getTimePerCharacter();
}
