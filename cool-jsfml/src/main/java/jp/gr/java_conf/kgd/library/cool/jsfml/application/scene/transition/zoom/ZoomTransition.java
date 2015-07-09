package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.zoom;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActorState;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.AbstractTransition;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class ZoomTransition
extends AbstractTransition
{	
	@Override
	protected boolean onStartTransition()
	{
		return true;
	}
	
	@Override
	protected boolean onAdvanceTransition()
	{	
		IActorState targetState = getTargetActorState();

		targetState.setPosition(Vector2f.ZERO);
		targetState.setOrigin(Vector2f.ZERO);
		ComponentHelper.setOriginToCenterAndAdjustMove(targetState);
		
		
		//
		float advancedRate = getTransitionAdvancedRate();
		
		//幅を大げさにする（加速していく）
		float scaleAdvanceRate = (float)(Math.pow(advancedRate, 8));

//		float alphaAdvancerate = (float)(Math.pow(advancedRate, 3));
		float alphaAdvancerate = advancedRate;
		
		//			
		if(isOutTransition())
		{
			advancedRate = 1.0f - advancedRate;
			
			scaleAdvanceRate = 1.0f - scaleAdvanceRate;
			
//			alphaAdvancerate = 1.0f - alphaAdvancerate;
			alphaAdvancerate = 1.0f - (float)(Math.pow(alphaAdvancerate, 4));
		}
		else
		{
//			alphaAdvancerate = 1.0f - (float)(Math.pow(1.0f - alphaAdvancerate, 4));
			alphaAdvancerate = (float)(Math.pow(alphaAdvancerate, 2));
		}
		
		
		float scaleRate = 1.0f / (scaleAdvanceRate * 19 / 20 + 0.05f);	

		float alphaRate = alphaAdvancerate;			
//		float alphaRate = (float)(Math.pow(alphaAdvancerate, 4));
		
		//
		targetState.setScale(scaleRate, scaleRate);
		targetState.setAlphaMaskRate(alphaRate);	
		
		
		if(MathHelper.isZero(alphaRate, 1.0f / 100))
		{
			targetState.setVisible(false);
		}
		else
		{
			targetState.setVisible(true);
		}
		
		if(MathHelper.equals(scaleRate, 1))
		{
			targetState.setScale(ComponentHelper.VECTOR_IDENTITY);
		}

		return true;
	}


}
