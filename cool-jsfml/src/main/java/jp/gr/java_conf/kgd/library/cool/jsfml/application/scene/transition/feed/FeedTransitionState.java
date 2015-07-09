package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.feed;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.TransitionState;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class FeedTransitionState
extends TransitionState
implements IFeedTransitionState
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
}
