package jp.gr.java_conf.kgd.library.cool.jsfml.actor;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponent;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink.IBlinkShapeState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake.IShakeAnimationState;

/***
 * 基本コンポーネントクラスに委譲するための抽象クラスです。
 * 「自身が単一の描画コンポーネント」であるため、onDrawにて描画を行います。
 * @author taisa
 *
 */
public abstract class AbstractBaseComponentActor
extends AbstractParentActor
{
	private IComponent baseComponent;
	
	
	//
	protected AbstractBaseComponentActor()
	{
	}
	
	
	//
	protected void setBaseComponent(IComponent baseComponent)
	{
		this.baseComponent = baseComponent;
	}
	
	
	//
	@Override
	protected void onUpdate()
	{
	}
	
	@Override
	final protected void onUpdateChildren()
	{
		ComponentHelper.setSizeIfNeedsUpdate(baseComponent, getSize());
	}
	
	@Override
	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		onDraw(target, states, mask);
	}
	
	
	//
	protected void onDraw(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		baseComponent.draw(target, states, mask);
	}
	
	
	
	//
	@Override
	final public boolean isAnimationEnabled()
	{
		return super.isAnimationEnabled();
	}

	@Override
	final public void setAnimationEnabled(boolean flag)
	{
		super.setAnimationEnabled(flag);
	}

//	@Override
//	final public ISnapAnimationState getSnapAnimationState()
//	{
//		return super.getSnapAnimationState();
//	}

	@Override
	final public IShakeAnimationState getShakeAnimationState()
	{
		return super.getShakeAnimationState();
	}

	@Override
	final public IBlinkShapeState getMaskShapeState()
	{
		return super.getMaskShapeState();
	}

	@Override
	final public Color getColorMask()
	{
		return super.getColorMask();
	}

	@Override
	final public boolean isVisible()
	{
		return super.isVisible();
	}

	@Override
	final public void setVisible(boolean flag)
	{
		super.setVisible(flag);
	}

	@Override
	final public Vector2f getSize()
	{
		return super.getSize();
	}

	@Override
	final public FloatRect getLocalBounds()
	{
		return super.getLocalBounds();
	}

	@Override
	final public float getAlphaMaskRate()
	{
		return super.getAlphaMaskRate();
	}

	@Override
	final public FloatRect getGlobalBounds()
	{
		return super.getGlobalBounds();
	}

	@Override
	final public void setColorMask(Color color)
	{
		super.setColorMask(color);
	}

	@Override
	final public void setAlphaMaskRate(float rate)
	{
		super.setAlphaMaskRate(rate);
	}

	@Override
	final public void setSize(Vector2f size)
	{
		super.setSize(size);
	}

	@Override
	final public void setPosition(Vector2f v)
	{
		super.setPosition(v);
	}

	@Override
	final public void setRotation(float angle)
	{
		super.setRotation(angle);
	}

	@Override
	final public void setScale(Vector2f factors)
	{
		super.setScale(factors);
	}

	@Override
	final public void setOrigin(Vector2f v)
	{
		super.setOrigin(v);
	}

	@Override
	final public Vector2f getPosition()
	{
		return super.getPosition();
	}

	@Override
	final public float getRotation()
	{
		return super.getRotation();
	}

	@Override
	final public Vector2f getScale()
	{
		return super.getScale();
	}

	@Override
	final public Vector2f getOrigin()
	{
		return super.getOrigin();
	}

//	@Override
//	final public void move(Vector2f v)
//	{
//		super.move(v);
//	}
//
//	@Override
//	final public void rotate(float angle)
//	{
//		super.rotate(angle);
//	}
//
//	@Override
//	final public void scale(Vector2f factors)
//	{
//		super.scale(factors);
//	}

	@Override
	final public Transform getTransform()
	{
		return super.getTransform();
	}

	@Override
	final public Transform getInverseTransform()
	{
		return super.getInverseTransform();
	}
}
