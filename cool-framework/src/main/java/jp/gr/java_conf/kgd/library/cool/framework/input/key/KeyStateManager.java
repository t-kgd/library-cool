package jp.gr.java_conf.kgd.library.cool.framework.input.key;

import java.util.Objects;

public class KeyStateManager
implements IKeyStateManager
{
	private IConstKeyStateConfig bindedKeyStateConfig;
	
	private int holdTime = 0;
	private int prevHoldTime = 0;
	
	//
	private int lastPressInterval = 0;
	private int lastReleasedElapsedTime = 0;
	
	private boolean isPrevReleaseTapped = false;
	private boolean isPrevPrevReleaseTapped = false;
	
	
	//
	public KeyStateManager(IConstKeyStateConfig config)
	{
		bindKeyStateConfig(config);
	}
	
	//
	@Override
	public IConstKeyStateConfig getBindedKeyStateConfig()
	{
		return bindedKeyStateConfig;
	}
	
	@Override
	public boolean isDown()
	{
		return holdTime >= STATE_PRESSED;
	}

	@Override
	public boolean isUp()
	{
		return holdTime <= STATE_RELEASED;
	}

	@Override
	public int getHoldTime()
	{
		return holdTime;
	}
	
	@Override
	public int getPrevHoldTime()
	{
		return prevHoldTime;
	}

	@Override
	public boolean isPressed()
	{
		return isPressed(bindedKeyStateConfig.isKeyRepeatEnabled());
	}

	@Override
	public boolean isPressed(boolean isKeyRepeatEnabled)
	{
		if(holdTime == STATE_PRESSED)
		{
			return true;
		}
		
		if(isKeyRepeatEnabled)
		{
			int keyRepeatStartFrame = bindedKeyStateConfig.getKeyRepeatStartTime();
			
			if(holdTime < keyRepeatStartFrame)
			{
				return false;
			}
			else
			{
				return ((holdTime - keyRepeatStartFrame) % bindedKeyStateConfig.getKeyRepeatInterval()) == 0;
			}
		}
		
		return false;
	}

	@Override
	public boolean isReleased()
	{
		return holdTime == STATE_RELEASED;
	}
	
	@Override
	public boolean isHold(int time)
	{
		return holdTime >= time;
	}
	
	@Override
	public boolean isLeave(int time)
	{
		return holdTime <= -time;
	}
	
	
	//
	@Override
	public boolean isPressedAgain()
	{
		return isPressedAgain(bindedKeyStateConfig.getDoubleTapInterval());
	}
	
	@Override
	public boolean isPressedAgain(int interval)
	{
		return isPressed(false) && lastReleasedElapsedTime < interval;
	}
	

	@Override
	final public boolean isTapped()
	{
		return isTapped(bindedKeyStateConfig.getTapTime());
	}
	
	@Override
	public boolean isTapped(int touchTime)
	{
		return isReleased() && prevHoldTime < touchTime;
	}
	
	@Override
	public boolean isDoubleTapStarted()
	{
		return isDoubleTapStarted(bindedKeyStateConfig.getDoubleTapInterval());
	}
	
	@Override
	public boolean isDoubleTapStarted(int tapInterval)
	{
		return isPrevReleaseTapped &&
				lastReleasedElapsedTime < tapInterval &&
				isPressed(false);
	}

	@Override
	public boolean isDoubleTapped()
	{
		return isDoubleTapped(bindedKeyStateConfig.getTapTime(), bindedKeyStateConfig.getDoubleTapInterval());
	}
	
	@Override
	public boolean isDoubleTapped(int tapTime, int tapInterval)
	{
		return isPrevPrevReleaseTapped &&
				lastPressInterval < tapInterval &&
				isTapped(tapTime);
	}
	
	//
	@Override
	public void bindKeyStateConfig(IConstKeyStateConfig config)
	{
		this.bindedKeyStateConfig = Objects.requireNonNull(config);
	}

	@Override
	public void clearDoubleTapFlag()
	{
		isPrevReleaseTapped = false;
		isPrevPrevReleaseTapped = false;
	}
	
	//
	@Override
	public void updateKeyState(boolean isKeyDown)
	{	
		prevHoldTime = holdTime;
		
		if(isKeyDown)
		{
			if(holdTime <= STATE_RELEASED)
			{
				holdTime = STATE_PRESSED;
			}
			else
			{
				holdTime = Math.min(holdTime + 1, HOLD_TIME_LIMIT);
			}
		}
		else
		{
			if(holdTime > STATE_RELEASED)
			{
				holdTime = STATE_RELEASED;
			}
			else
			{
				holdTime = Math.max(holdTime - 1, -HOLD_TIME_LIMIT);
			}
		}
		
		
		//

		if(isPressed())
		{
			lastPressInterval = lastReleasedElapsedTime;
		}
		
		lastReleasedElapsedTime = Math.min(lastReleasedElapsedTime + 1, HOLD_TIME_LIMIT);
		
		if(isReleased())
		{
			isPrevPrevReleaseTapped = isPrevReleaseTapped;
			isPrevReleaseTapped = isTapped();
			
			lastReleasedElapsedTime = 0;		
		}	
	}
}
