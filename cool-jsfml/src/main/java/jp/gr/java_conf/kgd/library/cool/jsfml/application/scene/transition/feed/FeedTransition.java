package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.feed;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActorState;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.AbstractTransition;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class FeedTransition
extends AbstractTransition
implements IFeedTransition
{	
	//
	private float feedOutAlphaRate = 0;

	@Override
	public float getFeedOutAlphaRate()
	{
		return feedOutAlphaRate;
	}

	@Override
	public void setFeedOutAlphaRate(float rate)
	{
		this.feedOutAlphaRate = MathHelper.minMax(0, rate, 1);
	}
	
	//
	@Override
	protected boolean onStartTransition()
	{
		return true;
	}

	@Override
	protected boolean onAdvanceTransition()
	{
		final int transitionTime = getTransitionTime();		
		final float feedOutAlphaRate = getFeedOutAlphaRate();
		
		float advancedRate = (float)getAdvancedTime() / transitionTime;
		
		
		if(isOutTransition())
		{
			advancedRate = 1.0f - advancedRate;
		}
		
		advancedRate = advancedRate * (1.0f - feedOutAlphaRate) + feedOutAlphaRate;
		
		IActorState targetState = getTargetActorState();
		targetState.setAlphaMaskRate(advancedRate);		

		if(MathHelper.isZero(advancedRate, 1.0f / 100))
		{
			targetState.setVisible(false);
		}
		else
		{
			targetState.setVisible(true);
		}
		
		return true;
	}

}
