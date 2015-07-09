package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponent;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

/**
 *　委譲関係にあるオーバーロードは、クラス自体を委譲で実装する場合に注意が必要です。
 * @author taisa
 *
 */
public abstract class AbstractBaseComponent
implements IComponent
{
	//
	private ColorMaskState colorMaskState = new ColorMaskState();
	
	//
	private boolean isVisible = true;
	
	//
	protected AbstractBaseComponent()
	{
	}
	
	//
	@Override
	public boolean isVisible()
	{
		return this.isVisible;
	}
	
	@Override
	public void setVisible(boolean flag)
	{
		this.isVisible = flag;
	}
	
	//
	@Override
	final public void setRotation(float angle)
	{
		onSetRotation(MathHelper.modToPlus(angle, MathHelper.PI2_DEGREE));
	}
	
	//
	abstract protected void onSetRotation(float angle);

	public Color getColorMask()
	{
		return colorMaskState.getColorMask();
	}

	public float getAlphaMaskRate()
	{
		return colorMaskState.getAlphaMaskRate();
	}

	public void setColorMask(Color color)
	{
		colorMaskState.setColorMask(color);
	}

	public void setAlphaMaskRate(float rate)
	{
		colorMaskState.setAlphaMaskRate(rate);
	}
	
	
	
	
	//委譲時に要注意な委譲関係のあるオーバーロード
	public final void setPosition(float x, float y)
	{
		setPosition(new Vector2f(x, y));
	}

	public final void setScale(float x, float y)
	{
		setScale(new Vector2f(x, y));
	}

	public final void setOrigin(float x, float y)
	{
		setOrigin(new Vector2f(x, y));
	}

	public final void move(float x, float y)
	{
		move(new Vector2f(x, y));
	}

	public final void move(Vector2f v)
	{
		setPosition(Vector2f.add(getPosition(), v));
	}

	public final void rotate(float angle)
	{
		setRotation(getRotation() + angle);
	}

	public final void scale(float x, float y)
	{
		Vector2f scale = getScale();
		
		scale(scale.x * x, scale.y * y);
	}

	public final void scale(Vector2f factors)
	{
		setScale(factors.x, factors.y);
	}
}
