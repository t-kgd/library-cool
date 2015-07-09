package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IDrawState;

public class BlinkAnimationManager
extends BlinkAnimationController
implements IBlinkAnimationManager
{
	@Override
	public void applyAnimation(IDrawState state)
	{
		if(!isBlinkEnabled())
		{
			return;
		}
		
		state.setAlphaMaskRate(state.getAlphaMaskRate() * getCurrentBlinkFactor());
		
		advanceAnimation();
	}
}
