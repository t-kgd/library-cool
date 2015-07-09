package jp.gr.java_conf.kgd.library.cool.framework.input.key;

public class KeyStateConfig
	implements IKeyStateConfig
{
	public static final boolean DEFAULT_IS_KEY_REPEAT_ENABLED = true;
	public static final int DEFAULT_KEY_REPEAT_START_TIME = 30;
	public static final int DEFAULT_KEY_REPEAT_INTERVAL = 6;

	public static final int DEFAULT_TAP_TIME = 20;
	public static final int DEFAULT_TAP_INTERVAL = 20;

	//
	private boolean isKeyRepeatEnabled = DEFAULT_IS_KEY_REPEAT_ENABLED;
	private int keyRepeatStartTime = DEFAULT_KEY_REPEAT_START_TIME;
	private int keyRepeatInterval = DEFAULT_KEY_REPEAT_INTERVAL;

	private int tapTime = DEFAULT_TAP_TIME;
	private int doubleTapInterval = DEFAULT_TAP_INTERVAL;

	//
	public boolean isKeyRepeatEnabled()
	{
		return isKeyRepeatEnabled;
	}

	public void setKeyRepeatEnabled(boolean flag)
	{
		this.isKeyRepeatEnabled = flag;
	}

	public int getKeyRepeatStartTime()
	{
		return keyRepeatStartTime;
	}

	public void setKeyRepeatStartTime(int time)
	{
		this.keyRepeatStartTime = time;
	}

	public int getKeyRepeatInterval()
	{
		return keyRepeatInterval;
	}

	public void setKeyRepeatInterval(int interval)
	{
		this.keyRepeatInterval = interval;
	}

	public int getTapTime()
	{
		return tapTime;
	}

	public void setTapTime(int time)
	{
		this.tapTime = time;
	}

	public int getDoubleTapInterval()
	{
		return doubleTapInterval;
	}

	public void setDoubleTapInterval(int interval)
	{
		this.doubleTapInterval = interval;
	}
}
