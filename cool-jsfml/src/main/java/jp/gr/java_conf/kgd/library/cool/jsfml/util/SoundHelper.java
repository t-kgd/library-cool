package jp.gr.java_conf.kgd.library.cool.jsfml.util;

import org.jsfml.audio.ConstSoundBuffer;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundSource.Status;

public class SoundHelper
{
	protected SoundHelper()
	{
	}
	
	
	//
	public static void setSoundBufferIfNeedsUpdate(Sound sound, ConstSoundBuffer buffer)
	{
		if(sound.getBuffer() != buffer)
		{
			sound.setBuffer(buffer);
		}
	}
	
	public static void playSoundIfIsNotPlaying(Sound sound)
	{
		Status status = sound.getStatus();
		if(status != Status.PLAYING)
		{
			sound.play();
		}
	}
	
	public static void setSoundBufferAndPlay(Sound sound, ConstSoundBuffer buffer)
	{
		if(sound.getBuffer() != buffer)
		{
			sound.setBuffer(buffer);
			sound.play();
			
			return;
		}
		
		playSoundIfIsNotPlaying(sound);
	}
}
