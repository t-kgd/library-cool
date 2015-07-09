package jp.gr.java_conf.kgd.library.cool.jsfml.application;

import java.util.Collection;
import java.util.Map;

import jp.gr.java_conf.kgd.library.cool.jsfml.input.inputs.IJSFMLInputStore;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.IResourceRegister;

import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.event.Event;

public interface IApplicationContext
extends IApplicationConfig
{
	//取得のみ
	boolean isRunning();	
	boolean hasFocus();
	
	float getAverageFPS();
	long getElapsedFrame();
	
	//
	void endApplication();
	//getApplicationWindow().close()と同じ。
	
	
	
	//BGMは統一的に扱える方が良い。ただし、これを使わなくて自由にSoundを生成して流しても良い。
	Sound getBackgroundMusic();
	
	
	//入力
	IJSFMLInputStore getInputStore();
	
	
	//リソース
	IResourceRegister getResourceRegister();
	
	IApplicationDataBox getApplicationDataBox();
	//共通データなど。
	
	
	//拡張用
	RenderWindow getApplicationWindow();
	Collection<? extends Event> getCurrentEvents();
	
	Map<Object, RenderTexture> getRenderTextureMap();
}
