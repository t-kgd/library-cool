package jp.gr.java_conf.kgd.library.cool.jsfml.base;

import org.jsfml.system.Vector2f;

public enum Direction8Way implements IDirection
{
	LEFT_TOP(-1, -1),
	TOP(0, -1),
	RIGHT_TOP(1, -1), 
	
	LEFT(-1, 0),
	CENTER(0, 0),
	RIGHT(1, 0),
	
	LEFT_BOTTOM(-1, 1),
	BOTTOM(0, 1),
	RIGHT_BOTTOM(1, 1),
	
	;
	
	private Direction impl;
	
	private Direction8Way(float vectorX, float vectorY)
	{
		impl = new Direction(vectorX, vectorY);
	}
	
	@Override
	public float getNormalizeX()
	{
		return impl.getNormalizeX();
	}
	
	@Override
	public float getNormalizeY()
	{
		return impl.getNormalizeY();
	}
	
	@Override
	public Vector2f getNormalizeVector()
	{
		return impl.getNormalizeVector();
	}

	@Override
	public int getDirectionSignX()
	{
		return impl.getDirectionSignX();
	}
	
	@Override
	public int getDirectionSignY()
	{
		return impl.getDirectionSignY();
	}
}
