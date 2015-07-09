package jp.gr.java_conf.kgd.library.cool.jsfml.input;


import jp.gr.java_conf.kgd.library.cool.jsfml.base.Direction8Way;
import jp.gr.java_conf.kgd.library.cool.jsfml.base.IDirection;

import org.jsfml.window.Joystick;
import org.jsfml.window.Joystick.Axis;

public enum KeyDirection4Way implements IKeyDirection
{
	KEY_UP(Joystick.Axis.POV_Y, -1, Direction8Way.TOP),
	KEY_DOWN(Joystick.Axis.POV_Y, 1, Direction8Way.BOTTOM),
	KEY_LEFT(Joystick.Axis.POV_X, -1, Direction8Way.LEFT),
	KEY_RIGHT(Joystick.Axis.POV_X, 1, Direction8Way.RIGHT),
	
	STICK_UP(Joystick.Axis.Y, -1, Direction8Way.TOP),
	STICK_DOWN(Joystick.Axis.Y, 1, Direction8Way.BOTTOM),
	STICK_LEFT(Joystick.Axis.X, -1, Direction8Way.LEFT),
	STICK_RIGHT(Joystick.Axis.X, 1, Direction8Way.RIGHT),
	
	;
	
	private KeyDirection impl;
	
	private KeyDirection4Way(Joystick.Axis axis, int directionSign, IDirection direction)
	{
		this.impl = new KeyDirection(axis, directionSign, direction);
	}
	
	@Override
	public Axis getAxis()
	{
		return impl.getAxis();
	}
	
	@Override
	public int getSign()
	{
		return impl.getSign();
	}
	
	@Override
	public IDirection getDirection()
	{
		return impl.getDirection();
	}
}
