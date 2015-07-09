package jp.gr.java_conf.kgd.library.cool.jsfml.actor.animation;

import org.jsfml.graphics.ConstTexture;

public interface ISpriteAnimationState
extends IConstSpriteAnimationState
{
	void setSpriteAnimationTexture(ConstTexture texture);
	void setSpriteAnimationTexture(ConstTexture texture, int dividedNumX, int dividedNumY, int animationNum);
	
	void setSpriteDivisionNum(int dividedNumX, int dividedNumY, int animationNum);
}
