package jp.gr.java_conf.kgd.library.cool.jsfml.actor;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink.BlinkShapeComponent;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink.IBlinkShapeState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake.IShakeAnimationState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake.ShakeAnimationManager;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

/***
 * 「アクターを作るための抽象アクタークラス」を作っていくための基本となる抽象アクタークラスです。
 * 自由な更新と自由な描画が可能なので、継承先でそのまま具象クラスとして構いませんが、
 * 「自身は描画コンポーネントではなく、描画コンポーネント（またはアクター）を束ねる役」
 * という立場なので、onDrawはありません。drawChildren内で行なってください。
 * また、更新時に子アクターの操作をしていても、子アクターのアップデート自身を忘れがちなので注意してください。
 * （例えば、child.move(10, 10)したのに、その後のchild.update()を忘れる、など）
 * そのため、onUpdateで子アクターの操作をした後、updateChildren中で子アクター自身の更新を行うと良いです。
 * @author taisa
 *
 */
public abstract class AbstractParentActor
implements IActor
{
	private boolean isActive = true;
	
	private boolean isAnimationEnabled = true;
	
	//
//	private SnapAnimationManager snapAnimationManager;	
	private ShakeAnimationManager shakeAnimationManager;	
		
	private BlinkShapeComponent maskShapeComponent;
		
	//
	private ComponentState baseState = new ComponentState();
	private DrawState animatedState = new DrawState();
	//
	
	//
	protected AbstractParentActor()
	{
	}
	
	
	//
	@Override
	public boolean isActive()
	{
		return isActive;
	}
	
	@Override
	public void setActive(boolean flag)
	{
		this.isActive = flag;
	}
	
	@Override
	public boolean isAnimationEnabled()
	{
		return isAnimationEnabled;
	}
	
	@Override
	public void setAnimationEnabled(boolean flag)
	{
		this.isAnimationEnabled = flag;
	}
	
	
//	@Override
//	public ISnapAnimationState getSnapAnimationState()
//	{
//		if(snapAnimationManager == null)
//		{
//			snapAnimationManager = new SnapAnimationManager();
//		}
//		
//		return snapAnimationManager;
//	}
	
	@Override
	public IShakeAnimationState getShakeAnimationState()
	{
		if(shakeAnimationManager == null)
		{
			shakeAnimationManager = new ShakeAnimationManager();
		}
		
		return shakeAnimationManager;
	}
	
	
	@Override
	public IBlinkShapeState getMaskShapeState()
	{
		if(maskShapeComponent == null)
		{
			maskShapeComponent = new BlinkShapeComponent();
			maskShapeComponent.setVisible(false);
		}
		
		return maskShapeComponent;
	}
	
	@Override
	final public void update()
	{
		if(!isActive)
		{
			return;
		}
		
		onUpdate();
		onUpdateChildren();	
				
//		if(isAnimationEnabled)
//		{
//			if(snapAnimationManager != null)
//			{		
//				snapAnimationManager.applyAnimation(this);
//			}
//		}
		
		ComponentHelper.copyDrawState(animatedState, this);
		
		if(isAnimationEnabled)
		{
			if(shakeAnimationManager != null)
			{		
				shakeAnimationManager.applyAnimation(animatedState);
			}
		}
		
		if(maskShapeComponent != null)
		{
			ComponentHelper.setSizeIfNeedsUpdate(maskShapeComponent, getSize());

			if(maskShapeComponent.isBlinkEnabled())
			{	
				maskShapeComponent.advanceAnimation();
			}
		}
	}
	
	@Override
	final public void draw(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{	
		if(!isVisible())
		{
			return;
		}
		
		//
		RenderStates states2 = ComponentHelper.combineRenderStates(states, animatedState);
		IConstColorMaskState mask2 = ComponentHelper.combineColorMaskState(mask, animatedState);
		
		//
		onDrawChildren(target, states2, mask2);
	
		//
		if(maskShapeComponent != null)
		{
			maskShapeComponent.draw(target, states2, mask2);		
		}
	}
	
	
	//
	abstract protected void onUpdate();
	
//	abstract protected void onDraw(RenderTarget target, RenderStates states,
//			IConstColorMaskState mask);
	
	
	abstract protected void onUpdateChildren();	
	
	//
	abstract protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask);


	
	//
	public Color getColorMask()
	{
		return baseState.getColorMask();
	}


	public boolean isVisible()
	{
		return baseState.isVisible();
	}


	public void setVisible(boolean flag)
	{
		baseState.setVisible(flag);
	}


	public Vector2f getSize()
	{
		return baseState.getSize();
	}


	public FloatRect getLocalBounds()
	{
		return baseState.getLocalBounds();
	}


	public float getAlphaMaskRate()
	{
		return baseState.getAlphaMaskRate();
	}


	public FloatRect getGlobalBounds()
	{
		return baseState.getGlobalBounds();
	}


	public void setColorMask(Color color)
	{
		baseState.setColorMask(color);
	}


	public void setAlphaMaskRate(float rate)
	{
		baseState.setAlphaMaskRate(rate);
	}


	public void setSize(Vector2f size)
	{
		baseState.setSize(size);
	}


	public final void setSize(float width, float height)
	{
		setSize(new Vector2f(width, height));
	}


//	public final void setPosition(float x, float y)
//	{
//		setPosition(new Vector2f(x, y));
//	}


	public void setPosition(Vector2f v)
	{
		baseState.setPosition(v);
	}


	public void setRotation(float angle)
	{
		baseState.setRotation(angle);
	}


//	public final void setScale(float x, float y)
//	{
//		baseState.setScale(x, y);
//	}


	public void setScale(Vector2f factors)
	{
		baseState.setScale(factors);
	}
//
//
//	public final void setOrigin(float x, float y)
//	{
//		baseState.setOrigin(x, y);
//	}
//

	public void setOrigin(Vector2f v)
	{
		baseState.setOrigin(v);
	}


	public Vector2f getPosition()
	{
		return baseState.getPosition();
	}


	public float getRotation()
	{
		return baseState.getRotation();
	}


	public Vector2f getScale()
	{
		return baseState.getScale();
	}


	public Vector2f getOrigin()
	{
		return baseState.getOrigin();
	}

//
//	public final void move(float x, float y)
//	{
//		baseState.move(x, y);
//	}
//
//
//	public void move(Vector2f v)
//	{
//		baseState.move(v);
//	}
//
//
//	public void rotate(float angle)
//	{
//		baseState.rotate(angle);
//	}
//
//
//	public final void scale(float x, float y)
//	{
//		baseState.scale(x, y);
//	}
//
//
//	public void scale(Vector2f factors)
//	{
//		baseState.scale(factors);
//	}


	public Transform getTransform()
	{
		return baseState.getTransform();
	}


	public Transform getInverseTransform()
	{
		return baseState.getInverseTransform();
	}

	
	
	
	//要注意オーバーロード
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
		
		setScale(scale.x * x, scale.y * y);
	}

	public final void scale(Vector2f factors)
	{
		setScale(factors.x, factors.y);
	}
}
