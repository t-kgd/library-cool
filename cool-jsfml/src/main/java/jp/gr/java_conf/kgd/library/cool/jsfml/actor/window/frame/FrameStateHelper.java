package jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame;

import org.jsfml.graphics.ConstTexture;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;

public class FrameStateHelper
{
	protected FrameStateHelper()
	{
	}
	
	//
	public static void copyFrameState(IFrameState destination, IConstFrameState source)
	{
		destination.setFrameSize(source.getFrameSize());
		
		destination.setFrameVisible(source.isFrameVisible());
		destination.setBackgroundVisible(source.isBackgroundVisible());
		
		ConstTexture texture = source.getFrameTexture();
		if(texture != null)
		{
			destination.setFrameTexture(texture);
		}
		
		ComponentHelper.copyColorMaskState(destination.getFrameColorState(),
				source.getFrameColorState());
		
		ComponentHelper.copyColorMaskState(destination.getBackgroundColorState(), 
				source.getBackgroundColorState());
	}
}
