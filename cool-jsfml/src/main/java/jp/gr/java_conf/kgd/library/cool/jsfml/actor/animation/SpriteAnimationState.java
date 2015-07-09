package jp.gr.java_conf.kgd.library.cool.jsfml.actor.animation;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2i;

public class SpriteAnimationState
implements ISpriteAnimationState
{
	private ConstTexture texture;
	
	private Vector2i dividedSize = Vector2i.ZERO;
	
	private int dividedNumX = 1;
	private int dividedNumY = 1;
	private int animationNum = 1;
	
	

	@Override
	public ConstTexture getSpriteAnimationTexture()
	{
		return texture;
	}

	@Override
	public Vector2i getSpriteDividedSize()
	{
		return dividedSize;
	}

	@Override
	public IntRect getSpriteDividedRect(int index)
	{		
//		if(texture == null || index < 0 || animationNum <= index)
//		{
//			return null;
////			return IntRect.EMPTY;
//		}
		
		int posX = (index % dividedNumX) * dividedSize.x;
		int posY = (index / dividedNumX) * dividedSize.y;
		
		return new IntRect(posX, posY, dividedSize.x, dividedSize.y);
	}

	@Override
	public int getSpriteDividedNumX()
	{
		return dividedNumX;
	}

	@Override
	public int getSpriteDividedNumY()
	{
		return dividedNumY;
	}

	@Override
	public int getSpriteAnimationNum()
	{
		return animationNum;
	}

	@Override
	public void setSpriteAnimationTexture(ConstTexture texture)
	{
		this.texture = texture;
		
		checkDivisionSize();
	}

	@Override
	final public void setSpriteAnimationTexture(ConstTexture texture, int dividedNumX, int dividedNumY, int animationNum)
	{
		this.texture = texture;
		
		this.dividedNumX = Math.max(1, dividedNumX);
		this.dividedNumY = Math.max(1, dividedNumY);
		this.animationNum = Math.min(animationNum, dividedNumX * dividedNumY);
	
		checkDivisionSize();
	}
	
	@Override
	public void setSpriteDivisionNum(int dividedNumX, int dividedNumY, int animationNum)
	{
		this.dividedNumX = Math.max(1, dividedNumX);
		this.dividedNumY = Math.max(1, dividedNumY);
		this.animationNum = Math.min(animationNum, dividedNumX * dividedNumY);
		
		checkDivisionSize();
	}
	
	
	//
	private void checkDivisionSize()
	{
		if(texture == null)
		{
			dividedSize = Vector2i.ZERO;
			return;
		}
		
		Vector2i textureSize = texture.getSize();
		
		int divideWidth = textureSize.x / dividedNumX;
		int divideHeight = textureSize.y / dividedNumY;
		
		dividedSize = new Vector2i(divideWidth, divideHeight);
	}
}
