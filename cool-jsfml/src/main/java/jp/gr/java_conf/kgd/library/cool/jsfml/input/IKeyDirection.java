package jp.gr.java_conf.kgd.library.cool.jsfml.input;

import jp.gr.java_conf.kgd.library.cool.jsfml.base.IDirection;

import org.jsfml.window.Joystick;

public interface IKeyDirection
{
	Joystick.Axis getAxis();
	int getSign();
	
	IDirection getDirection();
}
