package jp.gr.java_conf.kgd.library.cool.jsfml.base;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class Direction implements IDirection
{
	private boolean isZeroVector;
	
	private Vector2f normalizeVector;
	
	private int signX;
	private int signY;
	
	public Direction(float vectorX, float vectorY)
	{
		setVector(vectorX, vectorY);
	}
	
	public Direction(Vector2f vector)
	{
		setVector(vector.x, vector.y);
	}
	
	protected void setVector(float vectorX, float vectorY)
	{
		if(MathHelper.isZero(vectorX) && MathHelper.isZero(vectorY))
		{
			isZeroVector = true;
			
			normalizeVector = Vector2f.ZERO;
			
			signX = 0;
			signY = 0;
		}
		else
		{
			isZeroVector = false;

			double length = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
			normalizeVector = new Vector2f((float) (vectorX / length), (float) (vectorY / length));
			
			signX = MathHelper.getSign(vectorX);
			signY = MathHelper.getSign(vectorY);
		}
	}
	
	public boolean isZeroVector()
	{
		return isZeroVector;
	}
	
	public float getNormalizeX()
	{
		return normalizeVector.x;
	}
	
	public float getNormalizeY()
	{
		return normalizeVector.y;
	}
	
	@Override
	public Vector2f getNormalizeVector()
	{
		return normalizeVector;
	}

	@Override
	public int getDirectionSignX()
	{
		return signX;
	}
	
	@Override
	public int getDirectionSignY()
	{
		return signY;
	}
}
