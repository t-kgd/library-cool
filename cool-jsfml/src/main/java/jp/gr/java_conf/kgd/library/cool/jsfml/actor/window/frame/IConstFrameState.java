package jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

import org.jsfml.graphics.ConstTexture;

public interface IConstFrameState
{
	boolean isFrameVisible();
	float getFrameSize();
	IConstColorMaskState getFrameColorState();
	
	boolean isBackgroundVisible();
	IConstColorMaskState getBackgroundColorState();
		
	ConstTexture getFrameTexture();
}
