package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public class RectangleComponent
extends AbstractBaseComponent
implements IRectangleComponent
{
	private RectangleShape base = new RectangleShape();

	//baseに無いやつ
	@Override
	final public void setSize(float width, float height)
	{
		base.setSize(new Vector2f(width, height));
	}
	
	//オーバーライド
	@Override
	protected void onSetRotation(float angle)
	{
		base.setRotation(angle);
	}
	
	//
	@Override
	public void draw(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		if(!isVisible())
		{
			return;
		}
		
		Color blendColor = ComponentHelper.getBlendColor(mask, this);
		
		if(blendColor.a <= 0)
		{
			return;
		}
		else if(blendColor.equals(Color.WHITE))
		{
			base.draw(target, states);
			return;
		}
		
		//
		Color fillColor = getFillColor();
		setFillColor(Color.mul(fillColor, blendColor));
		
		Color outlineColor = getOutlineColor();
		boolean isOutlineVisibled = (getOutlineThickness() > 0) && (outlineColor.a > 0);
		if(isOutlineVisibled)
		{
			setOutlineColor(Color.mul(outlineColor, blendColor));
		}
			
		//
		base.draw(target, states);
			
		//
		setFillColor(fillColor);
		if(isOutlineVisibled)
		{
			setOutlineColor(outlineColor);
		}
	}
	
	//
	public void setSize(Vector2f size)
	{
		base.setSize(size);
	}

	public void setTexture(ConstTexture texture, boolean resetRect)
	{
		base.setTexture(texture, resetRect);
	}

	public Vector2f getSize()
	{
		return base.getSize();
	}

	public final void setTexture(ConstTexture texture)
	{
		base.setTexture(texture);
	}

	public void setTextureRect(IntRect rect)
	{
		base.setTextureRect(rect);
	}

	public Vector2f getPosition()
	{
		return base.getPosition();
	}

	public float getRotation()
	{
		return base.getRotation();
	}

	public Vector2f getScale()
	{
		return base.getScale();
	}

	public Vector2f getOrigin()
	{
		return base.getOrigin();
	}

	public void setFillColor(Color color)
	{
		base.setFillColor(color);
	}

	public void setOutlineColor(Color color)
	{
		base.setOutlineColor(color);
	}

	public Transform getTransform()
	{
		return base.getTransform();
	}

	public Transform getInverseTransform()
	{
		return base.getInverseTransform();
	}

	public void setOutlineThickness(float thickness)
	{
		base.setOutlineThickness(thickness);
	}

	public ConstTexture getTexture()
	{
		return base.getTexture();
	}

	public IntRect getTextureRect()
	{
		return base.getTextureRect();
	}

	public Color getFillColor()
	{
		return base.getFillColor();
	}

	public Color getOutlineColor()
	{
		return base.getOutlineColor();
	}

	public float getOutlineThickness()
	{
		return base.getOutlineThickness();
	}

	public int getPointCount()
	{
		return base.getPointCount();
	}

	public Vector2f getPoint(int i)
	{
		return base.getPoint(i);
	}

	public Vector2f[] getPoints()
	{
		return base.getPoints();
	}

	public FloatRect getLocalBounds()
	{
		return base.getLocalBounds();
	}

	public FloatRect getGlobalBounds()
	{
		return base.getGlobalBounds();
	}

	public void setPosition(Vector2f v)
	{
		base.setPosition(v);
	}

	public void setScale(Vector2f v)
	{
		base.setScale(v);
	}

	public void setOrigin(Vector2f v)
	{
		base.setOrigin(v);
	}
}
