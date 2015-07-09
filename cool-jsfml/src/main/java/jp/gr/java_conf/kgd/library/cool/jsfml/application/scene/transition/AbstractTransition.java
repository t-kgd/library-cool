package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition;

public abstract class AbstractTransition
extends TransitionState
implements ITransition
{
	private int advancedTime;

	private boolean isStopped = false;
	
	//
	protected AbstractTransition()
	{
	}
	
	//
	@Override
	final public boolean startTransition()
	{
		return startTransition(isOutTransition());
	}

	@Override
	public boolean startTransition(boolean isOutTransition)
	{
		setOutTransition(isOutTransition);
			
		if(!onStartTransition())
		{
			return false;
		}
				
		advancedTime = 0;
		setTransitionAdvancing(true);
		return true;
	}

	@Override
	public void stopTransition()
	{
		isStopped = true;
	}
	
	@Override
	public void resumeTransition()
	{
		isStopped = false;
	}

	@Override
	public boolean advanceTransition()
	{
		if(!isTransitionAdvancing() || isStopped)
		{
			return false;
		}
		
		if(getTargetActorState() == null)
		{
			return false;
		}
		
		//0フレーム目は存在しない。
		//stratTransitionした瞬間が0フレーム目。
		//そこから、即開始するかどうかは任意。
		advancedTime++;
		
		//
		if(!onAdvanceTransition())
		{
			return false;
		}
		
		if(advancedTime >= getTransitionTime())
		{
			setTransitionAdvancing(false);
		}
		
		return isTransitionAdvancing();
	}

	@Override
	public int getAdvancedTime()
	{
		return advancedTime;
	}
	
	
	//
	protected float getTransitionAdvancedRate()
	{
		int transitionTime = getTransitionTime();
		if(advancedTime == transitionTime)
		{
			return 1.0f;
		}
		
		return (float)advancedTime / getTransitionTime();
	}
	
	abstract protected boolean onAdvanceTransition();
	
	abstract protected boolean onStartTransition();
}
