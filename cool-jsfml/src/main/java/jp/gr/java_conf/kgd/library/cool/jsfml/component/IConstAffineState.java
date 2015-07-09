package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public interface IConstAffineState
{
	//
	Vector2f getOrigin();
	
	//
	float getRotation();
	Vector2f getScale();
	Vector2f getPosition();

	//
	Transform getTransform();
}
