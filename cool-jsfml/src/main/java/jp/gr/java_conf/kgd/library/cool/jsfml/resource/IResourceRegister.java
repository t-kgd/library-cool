package jp.gr.java_conf.kgd.library.cool.jsfml.resource;

import jp.gr.java_conf.kgd.library.cool.jsfml.resource.font.IFontResourceMap;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.sound.ISoundResourceMap;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.texture.ITextureResourceMap;

public interface IResourceRegister
{
	IFontResourceMap getFontMap();
	ITextureResourceMap getTextureMap();
	ISoundResourceMap getSoundBufferMap();
}
