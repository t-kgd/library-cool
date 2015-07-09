package jp.gr.java_conf.kgd.library.cool.jsfml.input;

import java.util.Objects;

import jp.gr.java_conf.kgd.library.cool.jsfml.base.IDirection;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

import org.jsfml.window.Joystick;
import org.jsfml.window.Joystick.Axis;

public class KeyDirection implements IKeyDirection
{
	private final Joystick.Axis axis;
	private final int sign;
	
	private final IDirection direction;
	
	//
	public KeyDirection(Joystick.Axis axis, int sign, IDirection direction)
	{
		this.axis = Objects.requireNonNull(axis);
		
		this.sign = MathHelper.getSign(sign >= 0);
		
		this.direction = Objects.requireNonNull(direction);
	}
	
	//
	@Override
	public Axis getAxis()
	{
		return axis;
	}
	
	@Override
	public int getSign()
	{
		return sign;
	}
	
	
	@Override
	public IDirection getDirection()
	{
		return direction;
	}
}
