package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import java.util.Objects;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.ITransition;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.feed.FeedTransition;

public class TransitionsData
implements ITransitionsData
{
	private ITransition outTransition = new FeedTransition();;
	private ITransition inTransition = new FeedTransition();
	
	private boolean isTransitionCrossed = false;
	private int transitionIntervalTime = 30;
	
	//
	public TransitionsData()
	{
//		this(true);
	}
	
//	public TransitionsData(boolean needsBaseInitialized)
//	{
//		if(true)
//		{
//			outTransition = 
//			inTransition = new FeedTransition();
//		}
//	}
	
	//
	@Override
	public ITransition getOutTransition()
	{
		return outTransition;
	}

	@Override
	public ITransition getInTransition()
	{
		return inTransition;
	}

	@Override
	public boolean isTransitionCrossed()
	{
		return isTransitionCrossed;
	}

	@Override
	public int getTransitionInterval()
	{
		return transitionIntervalTime;
	}
	
	
	
	/***
	 * inとoutで同一のオブジェクト（インスタンス）を指定しないでください。
	 */
	@Override
	public void setOutTransition(ITransition transition)
	{
		if(transition == inTransition)
		{
			throw new IllegalArgumentException();
		}
		
		this.outTransition = Objects.requireNonNull(transition);
//		this.outTransition = transition;
	}

	@Override
	public void setInTransition(ITransition transition)
	{
		if(transition == outTransition)
		{
			throw new IllegalArgumentException();
		}
		
		this.inTransition = Objects.requireNonNull(transition);
//		this.inTransition = transition;
	}

	@Override
	public void setTransitionCrossed(boolean flag)
	{
		this.isTransitionCrossed = flag;
	}	
	
	@Override
	public void setTransitionInterval(int interval)
	{
		this.transitionIntervalTime = Math.max(0, interval);
	}
}
