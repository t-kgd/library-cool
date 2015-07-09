package jp.gr.java_conf.kgd.library.cool.jsfml.resource;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.IApplicationContext;

import org.jsfml.audio.ConstSoundBuffer;
import org.jsfml.graphics.ConstFont;
import org.jsfml.graphics.ConstTexture;

public class ResourceHelper
{
	protected ResourceHelper()
	{
	}
	
	//
	public static final String FONT_BASE = "font_base";
	
	public static ConstFont getFontBase(IApplicationContext context)
	{
		return context.getResourceRegister().getFontMap().get(FONT_BASE);
	}
	
	public static void setFontBase(IApplicationContext context, ConstFont font)
	{
		context.getResourceRegister().getFontMap().put(FONT_BASE, font);
	}
	
	
	//
	public static final String TEXTURE_BASE_WINDOW = "texture_base_window";
	
	public static ConstTexture getTextureBaseWindow(IApplicationContext context)
	{
		return context.getResourceRegister().getTextureMap().get(TEXTURE_BASE_WINDOW);
	}
	
	public static void setTextureBaseWindow(IApplicationContext context, ConstTexture texture)
	{
		context.getResourceRegister().getTextureMap().put(TEXTURE_BASE_WINDOW, texture);
	}
	
	
	//
	public static final String SOUND_BUFFER_OK = "sound_buffer_ok";
	
	public static ConstSoundBuffer getSoundBufferOK(IApplicationContext context)
	{
		return context.getResourceRegister().getSoundBufferMap().get(SOUND_BUFFER_OK);
	}
	
	public static void setSoundBufferOK(IApplicationContext context, ConstSoundBuffer soundBuffer)
	{
		context.getResourceRegister().getSoundBufferMap().put(SOUND_BUFFER_OK, soundBuffer);
	}
	
	
	public static final String SOUND_BUFFER_CANCEL = "sound_buffer_cancel";
	
	public static ConstSoundBuffer getSoundBufferCancel(IApplicationContext context)
	{
		return context.getResourceRegister().getSoundBufferMap().get(SOUND_BUFFER_CANCEL);
	}
	
	public static void setSoundBufferCancel(IApplicationContext context, ConstSoundBuffer soundBuffer)
	{
		context.getResourceRegister().getSoundBufferMap().put(SOUND_BUFFER_CANCEL, soundBuffer);
	}
	
	
	public static final String SOUND_BUFFER_CURSOR = "sound_buffer_cursor";
	
	public static ConstSoundBuffer getSoundBufferCursor(IApplicationContext context)
	{
		return context.getResourceRegister().getSoundBufferMap().get(SOUND_BUFFER_CURSOR);
	}
	
	public static void setSoundBufferCursor(IApplicationContext context, ConstSoundBuffer soundBuffer)
	{
		context.getResourceRegister().getSoundBufferMap().put(SOUND_BUFFER_CURSOR, soundBuffer);
	}
	
	
	public static final String SOUND_BUFFER_ERROR = "sound_buffer_error";
	
	public static ConstSoundBuffer getSoundBufferError(IApplicationContext context)
	{
		return context.getResourceRegister().getSoundBufferMap().get(SOUND_BUFFER_ERROR);
	}
	
	public static void setSoundBufferError(IApplicationContext context, ConstSoundBuffer soundBuffer)
	{
		context.getResourceRegister().getSoundBufferMap().put(SOUND_BUFFER_ERROR, soundBuffer);
	}
}
