package jp.gr.java_conf.kgd.library.cool.jsfml.actor.animation;

public interface IConstSpriteAnimationController
{
	int getCurrentSpriteAnimationIndex();
	int getSpriteAnimationTime();
	
	boolean isSpriteAnimationRunning();	
	boolean isSpriteAnimationRepeated();
}
