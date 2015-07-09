package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActorState;

public class TransitionState
implements ITransitionState
{
	public static final int DEFAULT_TRANSITION_TIME = 30;
	
	private IActorState targetActorState;
	
	private int transitionTime = DEFAULT_TRANSITION_TIME;

	private boolean isOutTransition = true;
	
	private boolean isTransitionAdvancing = false;
	
	//
//	@Override
//	public Vector2f getParentSize()
//	{
//		return null;
//	}
//	
//	@Override
//	public void setParentSize(Vector2f size)
//	{
//	}
	
	@Override
	public IActorState getTargetActorState()
	{
		return targetActorState;
	}

	@Override
	public int getTransitionTime()
	{
		return transitionTime;
	}
	
	@Override
	public boolean isOutTransition()
	{
		return isOutTransition;
	}
	
	@Override
	public boolean isTransitionAdvancing()
	{
		return isTransitionAdvancing;
	}

	@Override
	public void setTargetActorState(IActorState actorState)
	{
		this.targetActorState = actorState;
	}

	@Override
	public void setTransitionTime(int time)
	{
		this.transitionTime = Math.max(1, time);
	}
	
	@Override
	public void setOutTransition(boolean flag)
	{
		this.isOutTransition = flag;
	}
	
	//
	protected void setTransitionAdvancing(boolean flag)
	{
		this.isTransitionAdvancing = flag;
	}
}
