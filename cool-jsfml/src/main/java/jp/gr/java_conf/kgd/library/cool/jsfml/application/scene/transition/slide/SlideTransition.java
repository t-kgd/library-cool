package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.slide;

import java.util.Objects;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActorState;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.AbstractTransition;
import jp.gr.java_conf.kgd.library.cool.jsfml.base.Direction8Way;
import jp.gr.java_conf.kgd.library.cool.jsfml.base.IDirection;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class SlideTransition
extends AbstractTransition
implements ISlideTransition
{
	private IDirection direction = Direction8Way.LEFT;

	@Override
	public IDirection getSlideDirection()
	{
		return this.direction;
	}
	
	@Override
	public void setSlideDirection(IDirection direction)
	{
		this.direction = Objects.requireNonNull(direction);
	}

	

	@Override
	protected boolean onStartTransition()
	{
		return true;
	}

	@Override
	protected boolean onAdvanceTransition()
	{
//		final int transitionTime = getTransitionTime();		
//		final float feedOutAlphaRate = getFeedOutAlphaRate();
	
		IActorState targetState = getTargetActorState();
		
		float advancedRate = getTransitionAdvancedRate();
		
		//
		Vector2f size = targetState.getSize();
		
		int directionX = direction.getDirectionSignX();
		int directionY = direction.getDirectionSignY();
			
		float alphaRate = advancedRate;
		
		
		//
		targetState.setOrigin(Vector2f.ZERO);
		
		if(isOutTransition())
		{
			targetState.setPosition(Vector2f.ZERO);
		}
		else
		{
			targetState.setPosition(size.x * directionX, size.y * directionY);
			
			directionX *= (-1);
			directionY *= (-1);

			alphaRate = 1.0f - alphaRate;
		}
		
		ComponentHelper.setOriginToCenterAndAdjustMove(targetState);
		
		targetState.move(size.x * directionX * advancedRate, size.y * directionY * advancedRate);				
//		targetState.setAlphaMaskRate(advancedRate);	
		targetState.setAlphaMaskRate(alphaRate);	
		
		
		
		if(MathHelper.isZero(alphaRate, 1.0f / 100))
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
