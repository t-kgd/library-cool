package jp.gr.java_conf.kgd.library.cool.jsfml.resource.font;

import jp.gr.java_conf.kgd.library.cool.jsfml.resource.ResourceMap;

import org.jsfml.graphics.ConstFont;

public class FontResourceMap
extends ResourceMap<ConstFont>
implements IFontResourceMap
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7677144443739248655L;

	@Override
	public ConstFont getSystemFont()
	{
		return get(SYSTEM_FONT);
	}

	@Override
	public void setSystemFont(ConstFont font)
	{
		put(SYSTEM_FONT, font);
	}

	@Override
	public ConstFont getNumericalFont()
	{
		return get(NUMERICAL_FONT);
	}

	@Override
	public void setNumericalFont(ConstFont font)
	{
		put(NUMERICAL_FONT, font);
	}

}
