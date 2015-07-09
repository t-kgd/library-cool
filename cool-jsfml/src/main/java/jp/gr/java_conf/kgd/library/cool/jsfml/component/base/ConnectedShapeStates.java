package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;

public class ConnectedShapeStates
implements IShapeState
{
	private IShapeState[] shapeStates;

	public ConnectedShapeStates(IShapeState... shapeStates)
	{
		this.shapeStates = shapeStates;
	}
	
	public void setFillColor(Color color)
	{
		for(IShapeState shapeState : shapeStates)
		{
			shapeState.setFillColor(color);
		}
	}

	public ConstTexture getTexture()
	{
		return shapeStates[0].getTexture();
	}

	public Color getFillColor()
	{
		return shapeStates[0].getFillColor();
	}

	public void setTexture(ConstTexture texture, boolean resetRect)
	{
		for(IShapeState shapeState : shapeStates)
		{
			shapeState.setTexture(texture, resetRect);
		}
	}

	public void setOutlineColor(Color color)
	{
		for(IShapeState shapeState : shapeStates)
		{
			shapeState.setOutlineColor(color);
		}
	}

	public IntRect getTextureRect()
	{
		return shapeStates[0].getTextureRect();
	}

	public Color getOutlineColor()
	{
		return shapeStates[0].getOutlineColor();
	}

	public void setOutlineThickness(float thickness)
	{
		for(IShapeState shapeState : shapeStates)
		{
			shapeState.setOutlineThickness(thickness);
		}
	}

	public float getOutlineThickness()
	{
		return shapeStates[0].getOutlineThickness();
	}

	public void setTexture(ConstTexture texture)
	{
		for(IShapeState shapeState : shapeStates)
		{
			shapeState.setTexture(texture, true);
		}
	}

	public Vector2f getPoint(int i)
	{
		return shapeStates[0].getPoint(i);
	}

	public void setTextureRect(IntRect rect)
	{
		for(IShapeState shapeState : shapeStates)
		{
			shapeState.setTextureRect(rect);
		}
	}

	public int getPointCount()
	{
		return shapeStates[0].getPointCount();
	}

	public Vector2f[] getPoints()
	{
		return shapeStates[0].getPoints();
	}
}
