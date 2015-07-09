package jp.gr.java_conf.kgd.library.cool.jsfml.actor.animation;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;

public interface ISpriteAnimationActor
extends IActor, ISpriteAnimationState, ISpriteAnimationController
{
	boolean isSpriteAnimationEnabled();
	void setSpriteAnimationEnabled(boolean flag);
	
	void startSpriteAnimation();
	void startSpriteAnimation(boolean isResetAdvance);
	
	void stopSpriteAnimation();
	void stopSpriteAnimation(boolean isResetAdvance);
}
