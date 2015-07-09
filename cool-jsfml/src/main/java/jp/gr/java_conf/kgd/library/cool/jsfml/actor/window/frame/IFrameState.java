package jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IColorMaskState;

import org.jsfml.graphics.ConstTexture;

public interface IFrameState extends IConstFrameState
{
	void setFrameVisible(boolean flag);
	void setFrameSize(float size);
	IColorMaskState getFrameColorState();
	
	void setBackgroundVisible(boolean flag);
	IColorMaskState getBackgroundColorState();
	
	void setFrameTexture(ConstTexture texture);
}
