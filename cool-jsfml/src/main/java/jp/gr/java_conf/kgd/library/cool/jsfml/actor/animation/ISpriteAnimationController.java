package jp.gr.java_conf.kgd.library.cool.jsfml.actor.animation;

public interface ISpriteAnimationController
extends IConstSpriteAnimationController
{
	void setCurrentSpriteAnimationIndex(int index);
	void setSpriteAnimationTime(int time);
	
	void setSpriteAnimationRunning(boolean flag);
	void setSpriteAnimationRepeated(boolean flag);
}
