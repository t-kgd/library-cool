package jp.gr.java_conf.kgd.library.cool.jsfml.base;

import org.jsfml.system.Vector2f;

public interface IDirection
{
	float getNormalizeX();
	float getNormalizeY();
	Vector2f getNormalizeVector();
	
	int getDirectionSignX();
	int getDirectionSignY();
}
