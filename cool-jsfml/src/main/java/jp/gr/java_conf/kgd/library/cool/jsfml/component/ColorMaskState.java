package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import java.io.Serializable;

import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

import org.jsfml.graphics.Color;

public class ColorMaskState
implements IColorMaskState, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8091108676411392065L;

	//
	private Color maskColor;
	private float maskAlphaRate;

	//
	public ColorMaskState()
	{
		this(Color.WHITE, 1);
	}
	
	public ColorMaskState(Color color, float alphaRate)
	{
		maskColor = color;
		maskAlphaRate = alphaRate;
	}
	
	public ColorMaskState(IConstColorMaskState source)
	{
		this.maskColor = source.getColorMask();
		this.maskAlphaRate = source.getAlphaMaskRate();
	}
	
	//
	@Override
	public Color getColorMask()
	{
		return maskColor;
	}

	@Override
	public float getAlphaMaskRate()
	{
		return maskAlphaRate;
	}
	
	//
	@Override
	public void setColorMask(Color color)
	{
		this.maskColor = color;
	}

	@Override
	public void setAlphaMaskRate(float rate)
	{
		this.maskAlphaRate = MathHelper.minMax(0, rate, 1);
	}
	
	//
//	@Override
//	public void resetMaskColorState()
//	{
//		this.setMaskColor(Color.WHITE);
//		this.setMaskAlphaRate(1);
//	}
//	
//	@Override
//	public void copyMaskColorState(IConstMaskColorState state)
//	{
//		this.setMaskColor(state.getMaskColor());
//		this.setMaskAlphaRate(state.getMaskAlphaRate());
//	}
//	
//	@Override
//	public void applyParentMaskColorState(IConstMaskColorState state)
//	{
//		this.setMaskColor(Color.mul(state.getMaskColor(), this.getMaskColor()));
//		this.setMaskAlphaRate(state.getMaskAlphaRate() * this.getMaskAlphaRate());
//	}
	
	//
//	@Override
//	public boolean isVisibled()
//	{
//		return this.isVisibled;
//	}
//	
//	@Override
//	public void setVisibled(boolean flag)
//	{
//		this.isVisibled = flag;
//	}
	
//	public static class ImmutableMaskColorState implements IConstMaskColorState
//	{
//		private final IConstMaskColorState inner;
//		
//		public ImmutableMaskColorState(Color color, float alphaRate)
//		{
//			this.inner = new MaskColorState(color, alphaRate);
//		}
//		
//		public ImmutableMaskColorState(IConstMaskColorState source)
//		{
//			this(source.getMaskColor(), source.getMaskAlphaRate());
//		}
//
//		public Color getMaskColor()
//		{
//			return inner.getMaskColor();
//		}
//
//		public float getMaskAlphaRate()
//		{
//			return inner.getMaskAlphaRate();
//		}
//	}
//	
//	public static ImmutableMaskColorState DEFAULT_MASK_COLOR_STATE =
//			new ImmutableMaskColorState(new MaskColorState());
}
