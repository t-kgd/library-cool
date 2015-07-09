package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.Color;

public class ImmutableColorMaskState
implements IConstColorMaskState
{
	private final IConstColorMaskState inner;
	
	public ImmutableColorMaskState(Color color, float alphaRate)
	{
		this.inner = new ColorMaskState(color, alphaRate);
	}
	
	public ImmutableColorMaskState(IConstColorMaskState source)
	{
		this(source.getColorMask(), source.getAlphaMaskRate());
	}

	public Color getColorMask()
	{
		return inner.getColorMask();
	}

	public float getAlphaMaskRate()
	{
		return inner.getAlphaMaskRate();
	}

//	public boolean isVisibled()
//	{
//		return inner.isVisibled();
//	}
	
//	public static ImmutableColorMaskState DEFAULT_MASK_COLOR_STATE =
//			new ImmutableColorMaskState(new ColorMaskState());
}
