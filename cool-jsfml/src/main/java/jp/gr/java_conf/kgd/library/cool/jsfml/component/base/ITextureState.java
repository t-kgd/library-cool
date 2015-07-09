package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;

public interface ITextureState extends IConstTextureState
{
	void setTexture(ConstTexture texture, boolean resetRect);
	void setTexture(ConstTexture texture);	
	void setTextureRect(IntRect rect);
}
