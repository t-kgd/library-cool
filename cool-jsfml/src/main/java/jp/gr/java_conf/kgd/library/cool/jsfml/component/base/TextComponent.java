package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.util.StringHelper;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstFont;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public class TextComponent
extends AbstractBaseComponent
implements ITextComponent
{
	private Text base = new Text();
	
	private Vector2f size = Vector2f.ZERO;
	
	private boolean isBottomAlignment = false;
	
	//baseに無いやつ
	@Override
	public Vector2f getSize()
	{
		return size;
	}
	
	@Override
	public void setSize(Vector2f size)
	{
		this.size = size;
	}
	
	@Override
	final public void setSize(float width, float height)
	{
		this.setSize(new Vector2f(width, height));
	}
	
	@Override
	public Color getTextColor()
	{
		return base.getColor();
	}
	
	@Override
	public void setTextColor(Color color)
	{
		base.setColor(color);
	}
	
	@Override
	public Vector2f getTextSize()
	{
		FloatRect rect = base.getLocalBounds();
		return new Vector2f(rect.width, rect.height);
	}
	
	//追加
	@Override
	public boolean isBottomAlignment()
	{
		return isBottomAlignment;
	}
	
	@Override
	public void setBottomAlignment(boolean flag)
	{
		this.isBottomAlignment = flag;
	}
	
	//オーバーライド
	@Override
	protected void onSetRotation(float angle)
	{
		base.setRotation(angle);
	}
	
	@Override
	public FloatRect getLocalBounds()
	{
		return new FloatRect(Vector2f.ZERO, size);
	}

	@Override
	public FloatRect getGlobalBounds()
	{
		return new FloatRect(getPosition(), size);
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
		
		String textString = getString();
		if(getFont() == null || StringHelper.EMPTY_STRING.equals(textString))
		{
			return;
		}
		
		if(size.equals(Vector2f.ZERO))
		{
			return;
		}
		
		Color blendColor = ComponentHelper.getBlendColor(mask, this);
		if(blendColor.a <= 0)
		{
			return;
		}
		
		//サイズが無いのでスケールで調整する。
		Vector2f scale = this.getScale();
		
		//この矩形はbaseから取得するので注意。
		FloatRect rect = base.getLocalBounds();		
		if(MathHelper.isZero(rect.width) || MathHelper.isZero(rect.height))
		{
			return;
		}
		
		
//		boolean isScaleModified = false;
//		int characterSize = getCharacterSize();
//		int halfCaracterNum = StringHelper.getHalfCharacterSize(textString);
//		int lineNum = StringHelper.getLineNum(textString);
//		if(!MathHelper.equals(rect.width, characterSize * halfCaracterNum / 2) ||
//				!MathHelper.equals(rect.height, characterSize * lineNum))
//		{
//			isScaleModified = true;
//			
//			base.setScale(scale.x * size.x / rect.width, scale.y * size.y / (rect.top * 2 + rect.height));
//		}
		
		
//		float width = rect.left * 2 + rect.width;
//		float height = rect.top * 2 + rect.height;
		
		
//		setScale(scale.x * size.x / rect.width, scale.y * size.y / (rect.top * 2 + rect.height));

		if(isBottomAlignment)
		{
			base.setScale(scale.x * size.x / rect.width, scale.y * size.y / (rect.top * 1 + rect.height));
		}
		else
		{
			base.setScale(scale.x * size.x / rect.width, scale.y * size.y / (rect.top * 2 + rect.height));
		}
		//高さ調節は×１の方が下にぴったりくっつく。位置文字ずつ表示するノベルを意識するなら×１
		
		//
		
		//
		if(blendColor.equals(Color.WHITE))
		{
			base.draw(target, states);
		}
		else
		{
			Color color = getTextColor();
			setTextColor(Color.mul(color, blendColor));
			
			base.draw(target, states);
			
			this.setTextColor(color);
		}
		
//		if(isVisible())
//		{
//			
//
//			if(blendColor.equals(Color.WHITE))
//			{
//				base.draw(target, states);
//			}
//			else
//			{
//				Color color = getTextColor();
//				setTextColor(Color.mul(color, blendColor));
//
//				base.draw(target, states);
//
//				this.setTextColor(color);
//			}
//		}

		//戻す
		this.setScale(scale);	
//		if(isScaleModified)
//		{
//			this.setScale(scale);	
//		}
	}
	
	
	//
	public void setString(String string)
	{
		base.setString(string);
	}

	public Vector2f getPosition()
	{
		return base.getPosition();
	}

	public float getRotation()
	{
		return base.getRotation();
	}

	public void setFont(ConstFont font)
	{
		base.setFont(font);
	}

	public Vector2f getScale()
	{
		return base.getScale();
	}

	public Vector2f getOrigin()
	{
		return base.getOrigin();
	}

	public void setCharacterSize(int characterSize)
	{
		base.setCharacterSize(characterSize);
	}

	public void setStyle(int style)
	{
		base.setStyle(style);
	}

	public Transform getTransform()
	{
		return base.getTransform();
	}

	public Transform getInverseTransform()
	{
		return base.getInverseTransform();
	}

	public String getString()
	{
		return base.getString();
	}

	public ConstFont getFont()
	{
		return base.getFont();
	}

	public int getCharacterSize()
	{
		return base.getCharacterSize();
	}

	public int getStyle()
	{
		return base.getStyle();
	}

	public Vector2f findCharacterPos(int i)
	{
		return base.findCharacterPos(i);
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
