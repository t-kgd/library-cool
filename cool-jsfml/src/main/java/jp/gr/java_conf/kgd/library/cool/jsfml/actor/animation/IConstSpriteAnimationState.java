package jp.gr.java_conf.kgd.library.cool.jsfml.actor.animation;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2i;

public interface IConstSpriteAnimationState
{
	ConstTexture getSpriteAnimationTexture();
	
	Vector2i getSpriteDividedSize();
	IntRect getSpriteDividedRect(int index);
	
	int getSpriteDividedNumX();
	int getSpriteDividedNumY();
	int getSpriteAnimationNum();
}
