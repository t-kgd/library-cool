package jp.gr.java_conf.kgd.library.cool.jsfml.actor.animation;

public class SpriteAnimationController
implements ISpriteAnimationController
{
	private int currentAnimationIndex;
	private int animationTime = 60;

	private boolean isAnimationRunning = false;
	private boolean isAnimationRepeated = false;
	
	

	@Override
	public int getCurrentSpriteAnimationIndex()
	{
		return currentAnimationIndex;
	}

	@Override
	public int getSpriteAnimationTime()
	{
		return animationTime;
	}

	@Override
	public boolean isSpriteAnimationRunning()
	{
		return isAnimationRunning;
	}

	@Override
	public boolean isSpriteAnimationRepeated()
	{
		return isAnimationRepeated;
	}

	@Override
	public void setCurrentSpriteAnimationIndex(int index)
	{
		this.currentAnimationIndex = Math.max(0, index);
	}

	@Override
	public void setSpriteAnimationTime(int time)
	{
		this.animationTime = Math.max(0, time);
	}

	@Override
	public void setSpriteAnimationRunning(boolean flag)
	{
		this.isAnimationRunning = flag;
	}

	@Override
	public void setSpriteAnimationRepeated(boolean flag)
	{
		this.isAnimationRepeated = flag;
	}
}
