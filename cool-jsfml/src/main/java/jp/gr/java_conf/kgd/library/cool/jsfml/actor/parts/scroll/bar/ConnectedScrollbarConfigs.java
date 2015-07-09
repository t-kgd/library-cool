package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar;

import org.jsfml.graphics.ConstTexture;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ConnectedColorMaskStates;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.ConnectedShapeStates;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.IShapeState;

public class ConnectedScrollbarConfigs
implements IScrollbarConfig
{
	private IScrollbarConfig[] scrollbarConfigs;
	
	
	private ConnectedShapeStates shapeStates;
	
	private ConnectedColorMaskStates frameColorMaskStates;
	private ConnectedColorMaskStates backgroundColorMaskStates;
	
	public ConnectedScrollbarConfigs(IScrollbarConfig... scrollbarConfigs)
	{
		this.scrollbarConfigs = scrollbarConfigs;
		
		final int length = scrollbarConfigs.length;
		
		IShapeState[] shapeStateArray = new IShapeState[length];
		IColorMaskState[] frameStateArray = new IColorMaskState[length];
		IColorMaskState[] backgroundStateArray = new IColorMaskState[length];
		
		for(int i = 0; i < length; ++i)
		{
			shapeStateArray[i] = scrollbarConfigs[i].getSliderShapeState();
			frameStateArray[i] = scrollbarConfigs[i].getFrameColorState();
			backgroundStateArray[i] = scrollbarConfigs[i].getBackgroundColorState();
		}
		
		this.shapeStates = new ConnectedShapeStates(shapeStateArray);
		this.frameColorMaskStates = new ConnectedColorMaskStates(frameStateArray);
		this.backgroundColorMaskStates = new ConnectedColorMaskStates(backgroundStateArray);
	}
	
	
	
	
	public float getSliderWidthRate()
	{
		return scrollbarConfigs[0].getSliderWidthRate();
	}

	public void setSliderWidthRate(float rate)
	{
		for(IScrollbarConfig scrollbarConfig : scrollbarConfigs)
		{
			scrollbarConfig.setSliderWidthRate(rate);
		}
	}

	public boolean isFrameVisible()
	{
		return scrollbarConfigs[0].isFrameVisible();
	}

	public void setFrameVisible(boolean flag)
	{
		for(IScrollbarConfig scrollbarConfig : scrollbarConfigs)
		{
			scrollbarConfig.setFrameVisible(flag);
		}
	}

	public float getFrameSize()
	{
		return scrollbarConfigs[0].getFrameSize();
	}

	public IShapeState getSliderShapeState()
	{
		return shapeStates;
	}

	public void setFrameSize(float size)
	{
		for(IScrollbarConfig scrollbarConfig : scrollbarConfigs)
		{
			scrollbarConfig.setFrameSize(size);
		}
	}

	public IColorMaskState getFrameColorState()
	{
		return frameColorMaskStates;
	}

	public boolean isBackgroundVisible()
	{
		return scrollbarConfigs[0].isBackgroundVisible();
	}

	public void setBackgroundVisible(boolean flag)
	{
		for(IScrollbarConfig scrollbarConfig : scrollbarConfigs)
		{
			scrollbarConfig.setBackgroundVisible(flag);
		}
	}

	public IColorMaskState getBackgroundColorState()
	{
		return backgroundColorMaskStates;
	}

	public ConstTexture getFrameTexture()
	{
		return scrollbarConfigs[0].getFrameTexture();
	}

	public void setFrameTexture(ConstTexture texture)
	{
		for(IScrollbarConfig scrollbarConfig : scrollbarConfigs)
		{
			scrollbarConfig.setFrameTexture(texture);
		}
	}
}
