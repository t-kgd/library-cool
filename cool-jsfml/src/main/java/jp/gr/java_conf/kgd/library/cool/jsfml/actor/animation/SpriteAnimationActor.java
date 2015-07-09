package jp.gr.java_conf.kgd.library.cool.jsfml.actor.animation;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2i;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.ActorHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.RectangleActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

public class SpriteAnimationActor
extends AbstractParentActor
implements ISpriteAnimationActor
{
	private SpriteAnimationState state = new SpriteAnimationState();
	private SpriteAnimationController controller = new SpriteAnimationController();
		
	//
	private boolean isSpriteAnimationEnabled = true;
	
	private RectangleActor drawActor = new RectangleActor();

	//
//	private int singleCount;
	private int allCount;
	
	
	
	//
	@Override
	public boolean isSpriteAnimationEnabled()
	{
		return isSpriteAnimationEnabled;
	}
	
	@Override
	public void setSpriteAnimationEnabled(boolean flag)
	{
		this.isSpriteAnimationEnabled = flag;
	}
	
	
	//
	@Override
	protected void onUpdate()
	{
		if(!isSpriteAnimationEnabled)
		{
			return;
		}
		
		boolean isRunning = isSpriteAnimationRunning();
		
		ConstTexture texture = getSpriteAnimationTexture();
		
		drawActor.setTexture(texture, true);
		if(texture == null || !isRunning)
		{
			drawActor.setVisible(false);
			setSpriteAnimationRunning(false);
			return;
		}
		else
		{
			drawActor.setVisible(true);
		}
		
		int currentIndex = getCurrentSpriteAnimationIndex();
		IntRect rect = getSpriteDividedRect(currentIndex);
		drawActor.setTextureRect(rect);
		
		
		//
		int allLimit = getSpriteAnimationTime();
		int animationNum = getSpriteAnimationNum();
		float singleLimit = (float)allLimit / animationNum;
		
		
//		singleCount++;
//		if(singleCount < singleLimit)
//		{
//			//そのまま続行。
//		}
//		else
//		{
//			int nextIndex = currentIndex + 1;
//			
//			if(nextIndex >= animationNum)
//			{
//				//端数を考慮する。
//				if(allCount >= allLimit)
//				{
//					resetSpriteAnimationState();
//					
//					if(isSpriteAnimationRepeated())
//					{
//						//リピートなので終了しない。
//					}
//					else
//					{
//						//終了される。
//						setSpriteAnimationRunning(false);				
//					}
//				}
//			}
//			else
//			{
//				singleCount = 0;
//				setCurrentSpriteAnimationIndex(nextIndex);
//			}		
//		}
		//下手な事をしていた。
		
		int nextIndex = currentIndex + 1;
		if(allCount < singleLimit * nextIndex)
		{
			//そのまま続行。
		}
		else
		{
			while(allCount >= singleLimit * (nextIndex + 1))
			{
				nextIndex++;
			}			
			
			if(nextIndex >= animationNum || allCount >= allLimit)
			{
				//端数を考慮する。
				if(allCount >= allLimit)
				{
					resetSpriteAnimationState();
					
					if(isSpriteAnimationRepeated())
					{
						//リピートなので終了しない。
					}
					else
					{
						//終了される。
						setSpriteAnimationRunning(false);				
					}
				}
			}
			else
			{
//				singleCount = 0;
				setCurrentSpriteAnimationIndex(nextIndex);
			}		
		}
		
		allCount++;
	}
	
	@Override
	final protected void onUpdateChildren()
	{
		ActorHelper.updateStrictFixInnerActor(drawActor, this);
		
//		ComponentHelper.setFitSizeAndOriginToCenter(drawActor, getSize());
	}
	
	@Override
	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		drawActor.draw(target, states, mask);
	}
	
	
	@Override
	final public void startSpriteAnimation()
	{
		startSpriteAnimation(false);
	}
	
	@Override
	public void startSpriteAnimation(boolean isResetAdvance)
	{
		setSpriteAnimationRunning(true);
		
		if(isResetAdvance)
		{
			resetSpriteAnimationState();
		}
	}
	
	@Override
	final public void stopSpriteAnimation()
	{
		stopSpriteAnimation(false);
	}
	
	@Override
	public void stopSpriteAnimation(boolean isResetAdvance)
	{
		setSpriteAnimationRunning(false);
		
		if(isResetAdvance)
		{
			resetSpriteAnimationState();
		}
	}
	
	//
	private final void resetSpriteAnimationState()
	{
//		singleCount = 0;
		allCount = 0;
		
		setCurrentSpriteAnimationIndex(0);
	}
	

	public ConstTexture getSpriteAnimationTexture()
	{
		return state.getSpriteAnimationTexture();
	}

	public Vector2i getSpriteDividedSize()
	{
		return state.getSpriteDividedSize();
	}

	public IntRect getSpriteDividedRect(int index)
	{
		return state.getSpriteDividedRect(index);
	}

	public int getSpriteDividedNumX()
	{
		return state.getSpriteDividedNumX();
	}

	public int getSpriteDividedNumY()
	{
		return state.getSpriteDividedNumY();
	}

	public int getSpriteAnimationNum()
	{
		return state.getSpriteAnimationNum();
	}

	public void setSpriteAnimationTexture(ConstTexture texture)
	{
		state.setSpriteAnimationTexture(texture);
	}

	public final void setSpriteAnimationTexture(ConstTexture texture,
			int dividedNumX, int dividedNumY, int animationNum)
	{
		state.setSpriteAnimationTexture(texture, dividedNumX, dividedNumY,
				animationNum);
	}

	public void setSpriteDivisionNum(int dividedNumX, int dividedNumY,
			int animationNum)
	{
		state.setSpriteDivisionNum(dividedNumX, dividedNumY, animationNum);
	}

	public int getCurrentSpriteAnimationIndex()
	{
		return controller.getCurrentSpriteAnimationIndex();
	}

	public int getSpriteAnimationTime()
	{
		return controller.getSpriteAnimationTime();
	}

	public boolean isSpriteAnimationRunning()
	{
		return controller.isSpriteAnimationRunning();
	}

	public boolean isSpriteAnimationRepeated()
	{
		return controller.isSpriteAnimationRepeated();
	}

	public void setCurrentSpriteAnimationIndex(int index)
	{
		controller.setCurrentSpriteAnimationIndex(Math.min(index, getSpriteAnimationNum()- 1));
	}

	public void setSpriteAnimationTime(int time)
	{
		controller.setSpriteAnimationTime(time);
	}

	public void setSpriteAnimationRunning(boolean flag)
	{
		controller.setSpriteAnimationRunning(flag);
	}

	public void setSpriteAnimationRepeated(boolean flag)
	{
		controller.setSpriteAnimationRepeated(flag);
	}
}
