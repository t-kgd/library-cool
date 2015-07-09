package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import jp.gr.java_conf.kgd.library.cool.framework.input.key.KeyStateConfig;

public class KeyStateStoreConfig
extends KeyStateConfig
implements IKeyStateStoreConfig
{
	public static final int DEFAULT_JOYSTICK_ID = 0;
	public static final float DEFAULT_JOYSTICK_THRESHOLD_RATE = 0.5f;
	
	//
	private int joystickID;
	private float joystickThresholdRate = DEFAULT_JOYSTICK_THRESHOLD_RATE;

	
	//
	public KeyStateStoreConfig()
	{
		this(DEFAULT_JOYSTICK_ID);
	}
	
	public KeyStateStoreConfig(int joystickID)
	{
		this.joystickID = joystickID;
	}
	
	
	//
	@Override
	public int getJoystickID()
	{
		return joystickID;
	}

	@Override
	public float getJoystickThresholdRate()
	{
		return joystickThresholdRate;
	}

	@Override
	public void setJoystickID(int joystickID)
	{
		this.joystickID = joystickID;
	}

	@Override
	public void setJoystickThresholdRate(float rate)
	{
		this.joystickThresholdRate = rate;
	}
}
