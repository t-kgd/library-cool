package jp.gr.java_conf.kgd.library.cool.jsfml.resource;

import jp.gr.java_conf.kgd.library.cool.jsfml.resource.font.FontResourceMap;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.font.IFontResourceMap;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.sound.ISoundResourceMap;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.sound.SoundResourceMap;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.texture.ITextureResourceMap;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.texture.TextureResourceMap;

public class ResourceRegister
implements IResourceRegister
{
	private FontResourceMap fontResourceMap = new FontResourceMap();
	private TextureResourceMap textureResourceMap = new TextureResourceMap();
	private SoundResourceMap soundResourceMap = new SoundResourceMap();

	@Override
	public IFontResourceMap getFontMap()
	{
		return fontResourceMap;
	}

	@Override
	public ITextureResourceMap getTextureMap()
	{
		return textureResourceMap;
	}

	@Override
	public ISoundResourceMap getSoundBufferMap()
	{
		return soundResourceMap;
	}

}
