package jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IColorMaskState;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstTexture;

public class FrameState
implements IFrameState
{
	private boolean isFrameVisible = true;
	private float frameSize = 2;
	private ColorMaskState frameColorState = new ObservableMaskColorState();
	
	private boolean isBackgroundVisible = true;
	private ColorMaskState backgroundColorState = new ObservableMaskColorState();

	private ConstTexture frameTexture;
	
	//
	private boolean isFrameNeedsUpdate = true;
	
	//
	public FrameState()
	{
		backgroundColorState.setAlphaMaskRate(0.5f);
	}
	
	//
	@Override
	public boolean isFrameVisible()
	{
		return isFrameVisible;
	}
	@Override
	public float getFrameSize()
	{
		return frameSize;
	}

	@Override
	public boolean isBackgroundVisible()
	{
		return isBackgroundVisible;
	}

	
	@Override
	public void setFrameVisible(boolean flag)
	{
		if(isFrameVisible == flag)
		{
			return;
		}
		
		isFrameVisible = flag;
		
		isFrameNeedsUpdate = true;
	}
	@Override
	public void setFrameSize(float size)
	{
		if(MathHelper.equals(frameSize, size))
		{
			return;
		}
		
		frameSize = size;
		
		isFrameNeedsUpdate = true;
	}

	@Override
	public void setBackgroundVisible(boolean flag)
	{
		if(isBackgroundVisible == flag)
		{
			return;
		}
		
		isBackgroundVisible = flag;
		
		isFrameNeedsUpdate = true;
	}
	
	//
	@Override
	public IColorMaskState getFrameColorState()
	{
		return frameColorState;
	}
	
	@Override
	public IColorMaskState getBackgroundColorState()
	{
		return backgroundColorState;
	}

	
	@Override
	public ConstTexture getFrameTexture()
	{
		return frameTexture;
	}
	
	@Override
	public void setFrameTexture(ConstTexture texture)
	{
		if(this.frameTexture != null && this.frameTexture.equals(texture))
		{
			return;
		}
		
		this.frameTexture = texture;
		
		isFrameNeedsUpdate = true;
	}
	
	
	//
	public boolean isFrameNeedsUpdate()
	{
		return isFrameNeedsUpdate;
	}
	
	public void setFrameNeedsUpdate(boolean flag)
	{
		this.isFrameNeedsUpdate = flag;
	}
	
	
	private class ObservableMaskColorState
	extends ColorMaskState
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -6800461799912742965L;

		@Override
		public void setColorMask(Color color)
		{
			if(getColorMask().equals(color))
			{
				return;
			}
			
			super.setColorMask(color);
			
			isFrameNeedsUpdate = true;
		}
		
		@Override
		public void setAlphaMaskRate(float rate)
		{
			if(MathHelper.equals(getAlphaMaskRate(), rate))
			{
				return;
			}
			
			super.setAlphaMaskRate(rate);
			
			isFrameNeedsUpdate = true;
		}
	}
}
