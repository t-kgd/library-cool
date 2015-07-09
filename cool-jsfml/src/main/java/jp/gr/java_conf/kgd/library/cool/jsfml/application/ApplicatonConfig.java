package jp.gr.java_conf.kgd.library.cool.jsfml.application;

public class ApplicatonConfig
implements IApplicationConfig
{
	public static final int DEFAULT_TARGET_FPS = 60;
	
	//
	private boolean isClosedEventIgnored = false;
	private boolean isUpdateNeedsFocus = false;
	
	private int targetFPS = DEFAULT_TARGET_FPS;
	private int drawSkipInterval = 0;
	
	//
	@Override
	public boolean isClosedEventIgnored()
	{
		return isClosedEventIgnored;
	}

	@Override
	public void setClosedEventIgnored(boolean flag)
	{
		isClosedEventIgnored = flag;
	}

	@Override
	public boolean isUpdateNeedsFocus()
	{
		return isUpdateNeedsFocus;
	}

	@Override
	public void setUpdateNeedsFocus(boolean flag)
	{
		isUpdateNeedsFocus = flag;
	}

	@Override
	public int getDrawSkipInterval()
	{
		return drawSkipInterval;
	}

	@Override
	public void setDrawSkipInterval(int interval)
	{
		drawSkipInterval = Math.max(0, interval);
	}

	public int getTargetFPS()
	{
		return targetFPS;
	}
	
	public void setTargetFPS(int fps)
	{
		this.targetFPS = Math.max(1, fps);
	}
}
